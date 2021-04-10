package com.sanvalero.greenroute.domain;

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
 * Creado por @author: Javier
 * el 06/04/2021
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "road")
public class Road {

    @Schema(description = "Road identifier", example = "1", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Schema(description = "Road name", example = "232", required = true)
    @NotBlank
    @Column
    private String name;

    @Schema(description = "Road extension in kilometres", example = "820.550")
    @Column
    private float length;

    @Schema(description = "¿Has it got toll?", example = "true")
    @NotBlank
    @Column
    private boolean toll;

    @Schema(description = "URL", example = "https://....")
    @NotBlank
    @Column
    private String image;

    @Schema(description = "Road build date", example = "11/06/1985")
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column
    private LocalDate buildDate;

    @Schema(description = "Sections list")
    @OneToMany(mappedBy = "road")
    private List<Section> sectionList;

}
