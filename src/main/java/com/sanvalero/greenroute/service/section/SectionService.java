package com.sanvalero.greenroute.service.section;

import com.sanvalero.greenroute.domain.Section;
import com.sanvalero.greenroute.domain.dto.SectionDTO;

import java.util.Optional;
import java.util.Set;

/**
 * Creado por @author: Javier
 * el 07/04/2021
 */
public interface SectionService {

    Optional<Section> findById(long id);
    Set<Section> findAll();
    Section addSection(long idRoad, long idMaintenance, SectionDTO sectionDTO);
    Section modifySection(long id, SectionDTO sectionDTO);
    Section modifySectionByName(long id, String name);
    void deleteSection(long id);

}
