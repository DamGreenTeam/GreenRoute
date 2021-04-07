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
import java.util.List;

/**
 * Creado por @ author: Pedro Orós
 * el 06/04/2021
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "maintenance")
public class Maintenance {

    @Schema(description = "Maintenance ID", example = "1", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Schema(description = "Maintenance name", example = "Autovía de Aragón", required = true)
    @NotBlank
    @Column(name = "maintenance")
    private String name;

    @Schema(description = "Benefits company", example = "124522.52")
    @Column
    private float benefits;

    @Schema(description = "Number of vehicles available", example = "12", required = true)
    @Column
    private int vehicles;

    @Schema(description = "Private Management", example = "true")
    @Column(name = "private_management")
    private boolean privateManagement;

    @Schema(description = "Foundation Date", example = "15/07/1998")
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column
    private LocalDate foundation;

    @Schema(description = "Sections list")
    @OneToMany(mappedBy = "maintenance")
    private List<Section> sectionList;

    @Schema(description = "Employees list")
    @OneToMany(mappedBy = "maintenance")
    private List<Staff> staffList;

    @Schema(description = "Location identifier")
    @ManyToOne
    @JoinColumn(name = "location_id")
    @JsonBackReference
    private Location location;
}
