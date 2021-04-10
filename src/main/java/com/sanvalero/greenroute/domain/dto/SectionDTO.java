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
public class SectionDTO {

    @Schema(description = "Section name", example = "", required = true)
    private String name;

    @Schema(description = "Section length in kilometres", example = "358.5")
    private float length;

    @Schema(description = "¿Is this road paved?", example = "true")
    private boolean paved;

    @Schema(description = "Paving date", example = "25/10/1984")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate pavingDate;

}
