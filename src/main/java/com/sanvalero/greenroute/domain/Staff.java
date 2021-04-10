package com.sanvalero.greenroute.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

/**
 * Creado por @author: Javier
 * el 06/04/2021
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "staff")
public class Staff {

    @Schema(description = "Staff ID", example = "1", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Schema(description = "employee name", example = "Juan Gonzalez", required = true)
    @NotBlank
    @Column(name = "staff")
    private String name;

    @Schema(description = "salary", example = "1544.25")
    @Column
    private float salary;

    @Schema(description = "permanent", example = "true")
    @Column
    private boolean permanent;

    @Schema(description = "entry date", example = "12/05/2001")
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "entry_date")
    private LocalDate entryDate;

    @Schema(description = "Maintenance identifier")
    @ManyToOne
    @JoinColumn(name = "maintenance_id")
    @JsonBackReference
    private Maintenance maintenance;

}
