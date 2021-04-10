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
@Entity(name = "section")
public class Section {

    @Schema(description = "Section identifier", example = "1", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Schema(description = "Section name", example = "A2-T4", required = true)
    @NotBlank
    @Column
    private String name;

    @Schema(description = "Section length in kilometres", example = "358.5")
    @Column
    private float length;

    @Schema(description = "¿Is this road paved?", example = "true")
    @NotBlank
    @Column
    private boolean paved;

    @Schema(description = "Paving date", example = "25/10/1984")
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column
    private LocalDate pavingDate;

    @Schema(description = "Road identifier")
    @ManyToOne
    @JoinColumn(name = "road_id")
    @JsonBackReference(value = "user-movement")
    private Road road;

    @Schema(description = "Maintenance identifier")
    @ManyToOne
    @JoinColumn(name = "maintenance_id")
    @JsonBackReference
    private Maintenance maintenance;

}

