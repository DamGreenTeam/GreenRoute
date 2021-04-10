package com.sanvalero.greenroute.controller.restcontroller;

import com.sanvalero.greenroute.domain.Response;
import com.sanvalero.greenroute.domain.Road;
import com.sanvalero.greenroute.domain.dto.RoadDTO;
import com.sanvalero.greenroute.exception.RoadNotFoundException;
import com.sanvalero.greenroute.service.road.RoadService;
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
@Tag(name = "Roads", description = "Roads Information")
public class RoadController {

    private final Logger logger = LoggerFactory.getLogger(RoadController.class);

    @Autowired
    private RoadService roadService;

    /********************************** FIND ALL **********************************/
    @Operation(summary = "Get all roads")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get information of all roads", content = @Content(schema = @Schema(implementation = Road.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema= @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/greenroute/roads", produces = "application/json")
    public ResponseEntity<Set<Road>> getRoads(){
        logger.info("[init getRoads]");
        Set<Road> roads = roadService.findAll();
        logger.info("[end getRoads]");
        return ResponseEntity.status(HttpStatus.OK).body(roads);
    }

    /********************************** FIND ROAD BY ID **********************************/
    @Operation(summary = "Get road by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get information of one road", content = @Content(schema = @Schema(implementation = Road.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema= @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/greenroute/roads/{id}", produces = "application/json")
    public ResponseEntity<Road> getRoadsById(@PathVariable long id){
        logger.info("[init getRoadsById]");
        Road road = roadService.findById(id)
                .orElseThrow(() -> new RoadNotFoundException(id));
        logger.info("[end getRoadsById]");
        return ResponseEntity.status(HttpStatus.OK).body(road);
    }

    /********************************** ADD **********************************/
    @Operation(summary = "Insert new road")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Road registered", content = @Content(schema = @Schema(implementation = Road.class))),
            @ApiResponse(responseCode = "404", description = "Fail insert road", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PostMapping(value = "/greenroute/roads", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Road> addRoad(@RequestBody Road road){
        logger.info("[init addRoad]");
        Road addedRoad = roadService.addRoad(road);
        logger.info("[end addRoad]");
        return ResponseEntity.status(HttpStatus.CREATED).body(addedRoad);
    }

    /********************************** MODIFY **********************************/
    @Operation(summary = "Modify road")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Modify all fields of a road", content = @Content(schema = @Schema(implementation = Road.class))),
            @ApiResponse(responseCode = "404", description = "Fail modify road", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PutMapping(value = "/greenroute/roads/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Road> modifyRoad(@PathVariable long id, @RequestBody RoadDTO roadDTO){
        logger.info("[init modifyRoad]");
        Road road = roadService.modifyRoad(id, roadDTO);
        logger.info("[end modifyRoad]");
        return ResponseEntity.status(HttpStatus.OK).body(road);
    }

    /********************************** DELETE **********************************/
    @Operation(summary = "Delete road")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Delete road", content = @Content(schema = @Schema(implementation = Road.class))),
            @ApiResponse(responseCode = "404", description = "Fail deleting road", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @DeleteMapping(value = "/greenroute/roads/{id}")
    public ResponseEntity<Response> deleteRoad(@PathVariable long id){
        logger.info("[init deleteRoad]");
        roadService.deleteRoad(id);
        logger.info("[end deleteRoad]");
        return ResponseEntity.status(HttpStatus.OK).body(Response.notErrorResponse());
    }

    /********************************** MODIFY ROAD BY LENGTH **********************************/
    @Operation(summary = "Modify length road")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Modify length of road", content = @Content(schema = @Schema(implementation = Road.class))),
            @ApiResponse(responseCode = "404", description = "Fail modify length", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PatchMapping(value = "/greenroute/roads/{id}/length", produces = "application/json")
    public ResponseEntity<Road> modifyRoadByLength(@PathVariable long id,
                                                   @RequestParam(value = "length", defaultValue = "") float length){
        logger.info("[init modifyRoadByLength]");
        Road road = roadService.modifyRoadByLength(id, length);
        logger.info("[end modifyRoadByLength]");
        return ResponseEntity.status(HttpStatus.OK).body(road);
    }

    /**********************************  EXCEPTION **********************************/
    @ExceptionHandler(RoadNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Response> handleException(RoadNotFoundException rnfe){
        Response response = Response.errorResponse(NOT_FOUND, rnfe.getMessage());
        logger.error(rnfe.getMessage(), rnfe);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }



}
