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
@AllArgsConstructor
@NoArgsConstructor
public class StaffDTO {

    @Schema(description = "employee name", example = "Juan Gonzalez", required = true)
    private String name;

    @Schema(description = "salary", example = "1544.25")
    private float salary;

    @Schema(description = "permanent", example = "true")
    private boolean permanent;

    @Schema(description = "entry date", example = "12/05/2001")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate entryDate;

}
