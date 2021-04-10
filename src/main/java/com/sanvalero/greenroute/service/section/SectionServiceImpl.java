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
import com.sanvalero.greenroute.repository.SectionRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

/**
 * Creado por @author: Javier
 * el 07/04/2021
 */
@Service
public class SectionServiceImpl implements SectionService{

    @Autowired
    private SectionRespository sectionRespository;

    @Autowired
    private RoadRepository roadRepository;

    @Autowired
    private MaintenanceRepository maintenanceRepository;

    @Override
    public Optional<Section> findById(long id) {
        return sectionRespository.findById(id);
    }

    @Override
    public Set<Section> findAll() {
        return sectionRespository.findAll();
    }

    @Override
    public Section addSection(long idRoad, long idMaintenance, SectionDTO sectionDTO) {
        Road road = roadRepository.findById(idRoad)
                .orElseThrow(() -> new RoadNotFoundException(idRoad));
        Maintenance maintenance = maintenanceRepository.findById(idMaintenance)
                .orElseThrow(() -> new MaintenanceNotFoundException(idMaintenance));

        Section section = new Section();
        setSection(section, sectionDTO);
        //section = sectionRespository.save(section);
        section.setRoad(road);
        section.setMaintenance(maintenance);
        return sectionRespository.save(section);
    }

    @Override
    public Section modifySection(long id, SectionDTO sectionDTO) {
        Section section = sectionRespository.findById(id)
                .orElseThrow(() -> new SectionNotFoundException(id));
        setSection(section, sectionDTO);
        return sectionRespository.save(section);
    }

    @Override
    public Section modifySectionByName(long id, String name) {
        Section section = sectionRespository.findById(id)
                .orElseThrow(() -> new SectionNotFoundException(id));
        section.setName(name);
        return sectionRespository.save(section);
    }

    @Override
    public void deleteSection(long id) {
        Section section = sectionRespository.findById(id)
                .orElseThrow(() -> new SectionNotFoundException(id));
        sectionRespository.delete(section);
    }

    public void setSection(Section section, SectionDTO sectionDTO){
        section.setName(sectionDTO.getName());
        section.setLength(sectionDTO.getLength());
        section.setPaved(sectionDTO.isPaved());
        section.setPavingDate(sectionDTO.getPavingDate());
    }
}
