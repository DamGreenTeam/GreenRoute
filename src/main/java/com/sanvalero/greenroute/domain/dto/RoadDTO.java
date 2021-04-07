package com.sanvalero.greenroute.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Creado por @ author: Pedro Orós
 * el 06/04/2021
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoadDTO {

    @Schema(description = "Road name", example = "232", required = true)
    private String name;

    @Schema(description = "Road extension in kilometres", example = "820")
    private float length;

    @Schema(description = "¿Has it got toll?", example = "true")
    private boolean toll;

    @Schema(description = "Road build date", example = "11/06/1985")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate buildDate;
}
