package com.sanvalero.greenroute.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Creado por @author: Javier
 * el 06/04/2021
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocationDTO {

    @Schema(description = "Location name", example = "Zaragoza", required = true)
    private String name;

    @Schema(description = "Location extension in square kilometres", example = "161.5")
    private float extension;

    @Schema(description = "¿Has it got assistance service?", example = "true")
    private boolean assistance;

    @Schema(description = "Service creation date", example = "14/09/1985")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate startingDate;

}
