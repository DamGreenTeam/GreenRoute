package com.sanvalero.greenroute.controller.restcontroller;

import com.sanvalero.greenroute.controller.Response;
import com.sanvalero.greenroute.domain.Location;
import com.sanvalero.greenroute.domain.dto.LocationDTO;
import com.sanvalero.greenroute.exception.LocationNotFoundException;
import com.sanvalero.greenroute.service.location.LocationService;
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

import static com.sanvalero.greenroute.controller.Response.NOT_FOUND;

/**
 * Creado por @ author: Pedro Orós
 * el 06/04/2021
 */

@RestController
@Tag(name = "Locations", description = "Locations Information")
public class LocationController {

    private final Logger logger = LoggerFactory.getLogger(LocationController.class);

    @Autowired
    private LocationService locationService;

    /********************************* FIND_ALL *********************************/
    @Operation(summary = "Get all locations")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Locations list",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Location.class)))),
            @ApiResponse(responseCode = "404", description = "Could not list",
                    content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/GreenRoute/locations", produces = "application/json")
    public ResponseEntity<Set<Location>> getLocations() {

        logger.info("init getLocations");

        Set<Location> locations = locationService.findAll();

        logger.info("end getLocations");

        return ResponseEntity.status(HttpStatus.OK).body(locations);
    }

    /********************************* FIND_BY_ID *********************************/

    @Operation(summary = "Get location by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Location exist", content = @Content(schema = @Schema(implementation = Location.class))),
            @ApiResponse(responseCode = "404", description = "location doesn't exist", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping("/GreenRoute/locations/{id}")
    public ResponseEntity<Location> getLocationById(@PathVariable long id) {

        logger.info("init getLocationById");

        Location location = locationService.findById(id)
                .orElseThrow(() -> new LocationNotFoundException(id));

        logger.info("end getLocationById");

        return new ResponseEntity<>(location, HttpStatus.OK);
    }

    /********************************* ADD *********************************/
    @Operation(summary = "Insert new location")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Location added", content = @Content(schema = @Schema(implementation = Location.class))),
            @ApiResponse(responseCode = "404", description = "Could not be added", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PostMapping(value= "/GreenRoute/locations", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Location> addLocation(@RequestBody Location location) {

        logger.info("init addLocation");

        Location addedLocation = locationService.addLocation(location);

        logger.info("end addLocation");

        return new ResponseEntity<>(addedLocation, HttpStatus.CREATED);
    }

    /********************************* MODIFY *********************************/

    @Operation(summary = "Modify location")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Modify one location", content = @Content(schema = @Schema(implementation = Location.class))),
            @ApiResponse(responseCode = "404", description = "location doesn't exist", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PutMapping(value = "/GreenRoute/locations/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Location> modifyLocation(@PathVariable long id, @RequestBody LocationDTO locationDTO) {

        logger.info("init modifyLocation");

        Location location = locationService.modifyLocation(id, locationDTO);

        logger.info("end modifyLocation");

        return new ResponseEntity<>(location, HttpStatus.OK);
    }

    /********************************* MODIFY_BY_ASSISTANCE *********************************/
    @Operation(summary = "Modify  location by assistance")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Modify location assistance", content = @Content(schema = @Schema(implementation = Location.class))),
            @ApiResponse(responseCode = "404", description = "location doesn't exist", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PatchMapping("/GreenRoute/locations/{id}/assistance")
    public ResponseEntity<Location> modifyLocationByAssistance(@PathVariable long id,
                                                               @RequestParam(value = "assistance", defaultValue = "") boolean assistance) {

        logger.info("init modifyLocationByAssistance");

        Location location = locationService.modifyLocationByAssistance(id, assistance);

        logger.info("end modifyLocationByAssistance");

        return new ResponseEntity<>(location, HttpStatus.OK);
    }

    /********************************* DELETE *********************************/
    @Operation(summary = "Delete location")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Delete one location", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "location doesn't exist", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @DeleteMapping("/GreenRoute/locations/{id}")
    public ResponseEntity<Response> deleteLocation(@PathVariable long id) {

        logger.info("init deleteLocation");

        locationService.deleteLocation(id);

        logger.info("end deleteLocation");

        return new ResponseEntity<>(Response.notErrorResponse(), HttpStatus.OK);
    }

    /********************************* EXCEPTION *********************************/
    @ExceptionHandler(LocationNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Response> handleException(LocationNotFoundException lnfe){
        Response response = Response.errorResponse(NOT_FOUND, lnfe.getMessage());
        logger.error(lnfe.getMessage(), lnfe);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
