package com.sanvalero.greenroute.controller.restcontroller;

import com.sanvalero.greenroute.controller.Response;
import com.sanvalero.greenroute.domain.Section;
import com.sanvalero.greenroute.domain.dto.SectionDTO;
import com.sanvalero.greenroute.exception.SectionNotFoundException;
import com.sanvalero.greenroute.service.section.SectionService;
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
 * el 07/04/2021
 */

@RestController
@Tag(name = "Sections", description = "Sections Information")
public class SectionController {

    private final Logger logger = LoggerFactory.getLogger(SectionController.class);

    @Autowired
    private SectionService sectionService;

    /********************************* FIND_ALL *********************************/
    @Operation(summary = "Get all sections")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sections list",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Section.class)))),
            @ApiResponse(responseCode = "404", description = "Could not list",
                    content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/GreenRoute/sections", produces = "application/json")
    public ResponseEntity<Set<Section>> getSections() {

        logger.info("init getSections");

        Set<Section> sections = sectionService.findAll();

        logger.info("end getSections");

        return ResponseEntity.status(HttpStatus.OK).body(sections);
    }

    /********************************* FIND_BY_ID *********************************/

    @Operation(summary = "Get section by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Section exist", content = @Content(schema = @Schema(implementation = Section.class))),
            @ApiResponse(responseCode = "404", description = "Section doesn't exist", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping("/GreenRoute/sections/{id}")
    public ResponseEntity<Section> getSectionById(@PathVariable long id) {

        logger.info("init getSectionById");

        Section section = sectionService.findById(id)
                .orElseThrow(() -> new SectionNotFoundException(id));

        logger.info("end getSectionById");

        return new ResponseEntity<>(section, HttpStatus.OK);
    }

    /********************************** ADD **********************************/
    @Operation(summary = "Insert new section")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Section registered", content = @Content(schema = @Schema(implementation = Section.class))),
            @ApiResponse(responseCode = "404", description = "Fail insert section", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PostMapping(value = "/GreenRoute/road/{idRoad}/maintenance/{idMaintenance}/sections", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Section> addSection(@PathVariable long idRoad,
                                              @PathVariable long idMaintenance,
                                              @RequestBody SectionDTO sectionDTO){

        logger.info("[init addSection]");

        Section section = sectionService.addSection(idRoad, idMaintenance, sectionDTO);

        logger.info("[end addSection]");

        return ResponseEntity.status(HttpStatus.CREATED).body(section);
    }

    /********************************** MODIFY **********************************/
    @Operation(summary = "Modify section")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Modify all fields of a section", content = @Content(schema = @Schema(implementation = Section.class))),
            @ApiResponse(responseCode = "404", description = "Fail modify section", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PutMapping(value = "/GreenRoute/sections/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Section> modifyRoad(@PathVariable long id, @RequestBody SectionDTO sectionDTO){

        logger.info("[init modifyRoad]");

        Section section = sectionService.modifySection(id, sectionDTO);

        logger.info("[end modifyRoad]");

        return ResponseEntity.status(HttpStatus.OK).body(section);
    }

    /********************************** MODIFY SECTION BY NAME **********************************/
    @Operation(summary = "Modify name section")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Modify name of section", content = @Content(schema = @Schema(implementation = Section.class))),
            @ApiResponse(responseCode = "404", description = "Fail modify name", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PatchMapping(value = "/GreenRoute/sections/{id}/name", produces = "application/json")
    public ResponseEntity<Section> modifySectionByName(@PathVariable long id,
                                                       @RequestParam(value = "name", defaultValue = "") String name){

        logger.info("[init modifySectionByName]");

        Section section = sectionService.modifySectionByName(id, name);

        logger.info("[end modifySectionByName]");

        return ResponseEntity.status(HttpStatus.OK).body(section);
    }

    /********************************** DELETE **********************************/
    @Operation(summary = "Delete section")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Delete section", content = @Content(schema = @Schema(implementation = Section.class))),
            @ApiResponse(responseCode = "404", description = "Fail deleting section", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @DeleteMapping(value = "/GreenRoute/sections/{id}")
    public ResponseEntity<Response> deleteSection(@PathVariable long id){

        logger.info("[init deleteSection]");

        sectionService.deleteSection(id);

        logger.info("[end deleteSection]");

        return ResponseEntity.status(HttpStatus.OK).body(Response.notErrorResponse());
    }

    /********************************* EXCEPTION *********************************/
    @ExceptionHandler(SectionNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Response> handleException(SectionNotFoundException snfe){
        Response response = Response.errorResponse(NOT_FOUND, snfe.getMessage());
        logger.error(snfe.getMessage(), snfe);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
