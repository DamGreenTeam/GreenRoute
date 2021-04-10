package com.sanvalero.greenroute.controller.restcontroller;

import com.sanvalero.greenroute.domain.Response;
import com.sanvalero.greenroute.domain.Staff;
import com.sanvalero.greenroute.domain.dto.StaffDTO;
import com.sanvalero.greenroute.exception.StaffNotFoundException;
import com.sanvalero.greenroute.service.staff.StaffService;
import io.swagger.v3.oas.annotations.Operation;
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
 * el 06/04/2021
 */
@RestController
@Tag(name = "staffs", description = "Staff Information")
public class StaffController {

    private final Logger logger = LoggerFactory.getLogger(StaffController.class);

    @Autowired
    private StaffService staffService;

    /********************************** FIND ALL **********************************/
    @Operation(summary = "Get all staff")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get information of all staff", content = @Content(schema = @Schema(implementation = Staff.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema= @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/greenroute/staffs", produces = "application/json")
    public ResponseEntity<Set<Staff>> getStaff(){
        logger.info("[init getStaff]");
        Set<Staff> staffs = staffService.findAll();
        logger.info("[end getStaff]");
        return ResponseEntity.status(HttpStatus.OK).body(staffs);
    }

    /********************************** FIND STAFF BY ID **********************************/
    @Operation(summary = "Get staff by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get information of one staff", content = @Content(schema = @Schema(implementation = Staff.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema= @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/greenroute/staffs/{id}", produces = "application/json")
    public ResponseEntity<Staff> getStaffById(@PathVariable long id){
        logger.info("[init getStaffById]");
        Staff staff = staffService.findById(id)
                .orElseThrow(() -> new StaffNotFoundException(id));
        logger.info("[end getStaffById]");
        return ResponseEntity.status(HttpStatus.OK).body(staff);
    }

    /********************************** ADD **********************************/
    @Operation(summary = "Insert new staff")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Staff registered", content = @Content(schema = @Schema(implementation = Staff.class))),
            @ApiResponse(responseCode = "404", description = "Fail insert staff", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PostMapping(value = "/greenroute/maintenances/{id}/staffs", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Staff> addStaff(@PathVariable long id,
                                          @RequestBody StaffDTO staffDTO){
        logger.info("[init addStaff]");
        Staff addedStaff = staffService.addStaff(id, staffDTO);
        logger.info("[end addStaff]");
        return ResponseEntity.status(HttpStatus.CREATED).body(addedStaff);
    }

    /********************************** MODIFY **********************************/
    @Operation(summary = "Modify staff")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Modify all fields of a staff", content = @Content(schema = @Schema(implementation = Staff.class))),
            @ApiResponse(responseCode = "404", description = "Fail modify road", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PutMapping(value = "/greenroute/staffs/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Staff> modifyStaff(@PathVariable long id, @RequestBody StaffDTO staffDTO){
        logger.info("[init modifyStaff]");
        Staff staff = staffService.modifyStaff(id, staffDTO);
        logger.info("[end modifyStaff]");
        return ResponseEntity.status(HttpStatus.OK).body(staff);
    }

    /****************************** MODIGFY STAFF BY SALARY ********************************/
    @Operation(summary = "Modify salary staff")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Modify field salary of a staff", content = @Content(schema = @Schema(implementation = Staff.class))),
            @ApiResponse(responseCode = "404", description = "Fail modify salary", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PatchMapping(value = "/greenroute/staffs/{id}/salary", produces = "application/json")
    public ResponseEntity<Staff> modifyStaffBySalary(@PathVariable long id,
                                                     @RequestParam(value = "salary", defaultValue = "") float salary){
        logger.info("[init modifyStaffBySalary]");
        Staff staff = staffService.modifyStaffBySalary(id, salary);
        logger.info("[end modifyStaffBySalary]");
        return ResponseEntity.status(HttpStatus.OK).body(staff);
    }

    /********************************** DELETE **********************************/
    @Operation(summary = "Delete staff")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Delete staff", content = @Content(schema = @Schema(implementation = Staff.class))),
            @ApiResponse(responseCode = "404", description = "Fail deleting staff", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @DeleteMapping(value = "/greenroute/staffs/{id}")
    public ResponseEntity<Response> deleteStaff(@PathVariable long id){
        logger.info("[init deleteStaff]");
        staffService.deleteStaff(id);
        logger.info("[end deleteStaff]");
        return ResponseEntity.status(HttpStatus.OK).body(Response.notErrorResponse());
    }

    /**********************************  EXCEPTION **********************************/
    @ExceptionHandler(StaffNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Response> handleException(StaffNotFoundException snfe){
        Response response = Response.errorResponse(NOT_FOUND, snfe.getMessage());
        logger.error(snfe.getMessage(), snfe);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

}
