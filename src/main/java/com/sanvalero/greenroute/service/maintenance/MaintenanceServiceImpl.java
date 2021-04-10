package com.sanvalero.greenroute.service.maintenance;

import com.sanvalero.greenroute.domain.Location;
import com.sanvalero.greenroute.domain.Maintenance;
import com.sanvalero.greenroute.domain.dto.MaintenanceDTO;
import com.sanvalero.greenroute.exception.LocationNotFoundException;
import com.sanvalero.greenroute.exception.MaintenanceNotFoundException;
import com.sanvalero.greenroute.repository.LocationRepository;
import com.sanvalero.greenroute.repository.MaintenanceRepository;
import org.jboss.jandex.Main;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

/**
 * Creado por @author: Javier
 * el 07/04/2021
 */
@Service
public class MaintenanceServiceImpl implements MaintenanceService {

    @Autowired
    private MaintenanceRepository maintenanceRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Override
    public Set<Maintenance> findAll() {
        return maintenanceRepository.findAll();
    }

    @Override
    public Optional<Maintenance> findById(long id) {
        return maintenanceRepository.findById(id);
    }

    @Override
    public Maintenance addMaintenanceToLocation(long id, MaintenanceDTO maintenanceDTO) {
        Maintenance newMaintenance = new Maintenance();
        setMaintenance(newMaintenance, maintenanceDTO);
        newMaintenance = maintenanceRepository.save(newMaintenance);

        Location location = locationRepository.findById(id)
                .orElseThrow(() -> new LocationNotFoundException(id));

        location.includeMaintenance(newMaintenance);

        maintenanceRepository.save(newMaintenance);

        return  newMaintenance;
    }

    @Override
    public Maintenance modifyMaintenance(long id, MaintenanceDTO maintenanceDTO) {
        Maintenance maintenance = maintenanceRepository.findById(id)
                .orElseThrow(() -> new MaintenanceNotFoundException(id));
        setMaintenance(maintenance, maintenanceDTO);
        return maintenanceRepository.save(maintenance);
    }

    @Override
    public Maintenance modifyMaintenanceByBenefits(long id, float benefits) {
        Maintenance maintenance = maintenanceRepository.findById(id)
                .orElseThrow(() -> new MaintenanceNotFoundException(id));
        maintenance.setBenefits(benefits);
        return maintenanceRepository.save(maintenance);
    }

    @Override
    public void deleteMaintenance(long id) {
        Maintenance maintenance = maintenanceRepository.findById(id)
                .orElseThrow(() -> new MaintenanceNotFoundException(id));
        maintenanceRepository.delete(maintenance);

        Location location = maintenance.getLocation();
        location.removeMaintenance(maintenance);
    }

    public void setMaintenance(Maintenance maintenance, MaintenanceDTO maintenanceDTO) {
        maintenance.setName(maintenanceDTO.getName());
        maintenance.setBenefits(maintenanceDTO.getBenefits());
        maintenance.setVehicles(maintenanceDTO.getVehicles());
        maintenance.setPrivateManagement(maintenanceDTO.isPrivateManagement());
        maintenance.setFoundation(maintenanceDTO.getFoundation());
    }
}
