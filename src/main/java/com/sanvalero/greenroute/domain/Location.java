package com.sanvalero.greenroute.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Creado por @author: Javier
 * el 06/04/2021
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "location")
public class Location {

    @Schema(description = "Location identifier", example = "1", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Schema(description = "Location name", example = "Zaragoza", required = true)
    @NotBlank
    @Column
    private String name;

    @Schema(description = "Location extension in square kilometres", example = "161.5")
    @Column
    private float extension;

    @Schema(description = "¿Has it got assistance service?", example = "true")
    @NotBlank
    @Column
    private boolean assistance;

    @Schema(description = "Service creation date", example = "14/09/1985")
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column
    private LocalDate startingDate;

    @Schema(description = "Maintenance list")
    @OneToMany(mappedBy = "location")
    private List<Maintenance> maintenanceList;

    public void includeMaintenance(Maintenance maintenance) {
        if(maintenanceList == null) maintenanceList = new ArrayList<>();
        maintenanceList.add(maintenance);
        maintenance.setLocation(this);
    }

    public void removeMaintenance(Maintenance maintenance) {
        maintenanceList.remove(maintenance);
    }

}
