package com.sanvalero.greenroute.service.section;

import com.sanvalero.greenroute.domain.Maintenance;
import com.sanvalero.greenroute.domain.Road;
import com.sanvalero.greenroute.domain.Section;
import com.sanvalero.greenroute.domain.dto.SectionDTO;
import com.sanvalero.greenroute.exception.MaintenanceNotFoundException;
import com.sanvalero.greenroute.exception.RoadNotFoundException;
import com.sanvalero.greenroute.exception.SectionNotFoundException;
import com.sanvalero.greenroute.repository.MaintenanceRepository;
import com.sanvalero.greenroute.repository.RoadRepository;
import com.sanvalero.greenroute.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

/**
 * Creado por @ author: Pedro Orós
 * el 07/04/2021
 */

@Service
public class SectionServiceImp implements SectionService {

    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private RoadRepository roadRepository;

    @Autowired
    private MaintenanceRepository maintenanceRepository;

    @Override
    public Set<Section> findAll() {
        return sectionRepository.findAll();
    }

    @Override
    public Optional<Section> findById(long id) {
        return sectionRepository.findById(id);
    }

    @Override
    public Section addSection(long idRoad, long idMaintenance, SectionDTO sectionDTO) {
        Section newSection = new Section();
        setSection(newSection, sectionDTO);
        newSection = sectionRepository.save(newSection);

        Road road = roadRepository.findById(idRoad)
                .orElseThrow(() -> new RoadNotFoundException(idRoad));
        newSection.setRoad(road);

        Maintenance maintenance = maintenanceRepository.findById(idMaintenance)
                .orElseThrow(() -> new MaintenanceNotFoundException(idMaintenance));
        newSection.setMaintenance(maintenance);

        sectionRepository.save(newSection);
        return newSection;
    }

    @Override
    public Section modifySection(long id, SectionDTO sectionDTO) {
        Section section = sectionRepository.findById(id)
                .orElseThrow(() -> new SectionNotFoundException(id));
        setSection(section, sectionDTO);
        return sectionRepository.save(section);
    }

    @Override
    public Section modifySectionByName(long id, String name) {
        Section section = sectionRepository.findById(id)
                .orElseThrow(() -> new SectionNotFoundException(id));
        section.setName(name);
        return sectionRepository.save(section);
    }

    @Override
    public void deleteSection(long id) {
        Section section = sectionRepository.findById(id)
                .orElseThrow(() -> new SectionNotFoundException(id));
        sectionRepository.delete(section);
    }

    public void setSection(Section section, SectionDTO sectionDTO) {
        section.setName(sectionDTO.getName());
        section.setLength(sectionDTO.getLength());
        section.setPaved(sectionDTO.isPaved());
        section.setPavingDate(sectionDTO.getPavingDate());
    }
}
