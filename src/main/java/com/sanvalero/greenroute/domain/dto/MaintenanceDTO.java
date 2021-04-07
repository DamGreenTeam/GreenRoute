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
@AllArgsConstructor
@NoArgsConstructor
public class MaintenanceDTO {

    @Schema(description = "Maintenance name", example = "Autovía de Aragón", required = true)
    private String name;

    @Schema(description = "Benefits company", example = "124522.52")
    private float benefits;

    @Schema(description = "Number of vehicles available", example = "12", required = true)
    private int vehicles;

    @Schema(description = "Private Management", example = "true")
    private boolean privateManagement;

    @Schema(description = "Foundation Date", example = "15/07/1998")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate foundation;
}
