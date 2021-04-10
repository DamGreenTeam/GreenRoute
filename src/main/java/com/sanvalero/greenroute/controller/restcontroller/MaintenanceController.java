package com.sanvalero.greenroute.controller.restcontroller;

import com.sanvalero.greenroute.domain.Maintenance;
import com.sanvalero.greenroute.domain.Response;
import com.sanvalero.greenroute.domain.dto.MaintenanceDTO;
import com.sanvalero.greenroute.exception.MaintenanceNotFoundException;
import com.sanvalero.greenroute.service.maintenance.MaintenanceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

import static com.sanvalero.greenroute.domain.Response.NOT_FOUND;

/**
 * Creado por @author: Javier
 * el 07/04/2021
 */
@RestController
@Tag(name = "maintenances", description = "Maintenance Information")
public class MaintenanceController {

    private final Logger logger = LoggerFactory.getLogger(MaintenanceController.class);

    @Autowired
    private MaintenanceService maintenanceService;

    /********************************* FIND_ALL *********************************/
    @Operation(summary = "Get all maintenances")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Maintenance list",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Maintenance.class)))),
            @ApiResponse(responseCode = "404", description = "Could not list",
                    content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/greenroute/maintenances", produces = "application/json")
    public ResponseEntity<Set<Maintenance>> getMaintenances() {

        logger.info("init getMaintenances");

        Set<Maintenance> maintenances = maintenanceService.findAll();

        logger.info("end getMaintenance");

        return ResponseEntity.status(HttpStatus.OK).body(maintenances);
    }

    /********************************* FIND_BY_ID *********************************/

    @Operation(summary = "Get Maintenance by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Maintenance exist", content = @Content(schema = @Schema(implementation = Maintenance.class))),
            @ApiResponse(responseCode = "404", description = "maintenance doesn't exist", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping("/greenroute/maintenances/{id}")
    public ResponseEntity<Maintenance> getMaintenanceById(@PathVariable long id) {

        logger.info("init getMaintenanceById");

        Maintenance maintenance = maintenanceService.findById(id)
                .orElseThrow(() -> new MaintenanceNotFoundException(id));

        logger.info("end getMaintenanceById");

        return new ResponseEntity<>(maintenance, HttpStatus.OK);
    }

    /********************************* ADD *********************************/
    @Operation(summary = "Insert new maintenance")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "maintenance added", content = @Content(schema = @Schema(implementation = Maintenance.class))),
            @ApiResponse(responseCode = "404", description = "Could not be added", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PostMapping(value= "/greenroute/locations/{id}/maintenance", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Maintenance> addMaintenance(@PathVariable long id, @RequestBody MaintenanceDTO maintenanceDTO) {

        logger.info("init addMaintenance");

        Maintenance addedMaintenance = maintenanceService.addMaintenanceToLocation(id,maintenanceDTO);

        logger.info("end addMaintenance");

        return new ResponseEntity<>(addedMaintenance, HttpStatus.CREATED);
    }

    /********************************* MODIFY *********************************/

    @Operation(summary = "Modify maintenance")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Modify one maintenance", content = @Content(schema = @Schema(implementation = Maintenance.class))),
            @ApiResponse(responseCode = "404", description = "Maintenance doesn't exist", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PutMapping(value = "/greenroute/maintenances/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Maintenance> modifyMaintenance(@PathVariable long id, @RequestBody MaintenanceDTO maintenanceDTO) {

        logger.info("init modifyMaintenance");

        Maintenance maintenance = maintenanceService.modifyMaintenance(id, maintenanceDTO);

        logger.info("end modifyMaintenance");

        return new ResponseEntity<>(maintenance, HttpStatus.OK);
    }

    /********************************* MODIFY_BY_BENEFITS *********************************/
    @Operation(summary = "Modify  maintenance by benefits")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Modify one maintenance", content = @Content(schema = @Schema(implementation = Maintenance.class))),
            @ApiResponse(responseCode = "404", description = "maintenance doesn't exist", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PatchMapping("/greenroute/maintenances/{id}/benefits")
    public ResponseEntity<Maintenance> modifyMaintenanceByBenefits(@PathVariable long id,
                                                                   @RequestParam(value = "benefits", defaultValue = "") float benefits) {
        logger.info("init modifyMaintenanceByBenefits");
        Maintenance maintenance = maintenanceService.modifyMaintenanceByBenefits(id, benefits);

        logger.info("end modifyMaintenanceByBenefits");

        return new ResponseEntity<>(maintenance, HttpStatus.OK);
    }

    /********************************* DELETE *********************************/
    @Operation(summary = "Delete maintenance")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Delete one maintenance", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "maintenance doesn't exist", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @DeleteMapping("/greenroute/maintenances/{id}")
    public ResponseEntity<Response> deleteMaintenance(@PathVariable long id) {
        logger.info("init deleteMaintenance");
        maintenanceService.deleteMaintenance(id);
        logger.info("end deleteMaintenance");
        return new ResponseEntity<>(Response.notErrorResponse(), HttpStatus.OK);
    }

    /********************************* EXCEPTION *********************************/
    @ExceptionHandler(MaintenanceNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Response> handleException(MaintenanceNotFoundException mnfe){
        Response response = Response.errorResponse(NOT_FOUND, mnfe.getMessage());
        logger.error(mnfe.getMessage(), mnfe);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

}
