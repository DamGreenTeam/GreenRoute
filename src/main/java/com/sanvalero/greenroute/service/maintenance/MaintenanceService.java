package com.sanvalero.greenroute.service.maintenance;

import com.sanvalero.greenroute.domain.Maintenance;
import com.sanvalero.greenroute.domain.dto.MaintenanceDTO;

import java.util.Optional;
import java.util.Set;

/**
 * Creado por @ author: Pedro Orós
 * el 06/04/2021
 */

public interface MaintenanceService {

    Set<Maintenance> findAll();
    Optional<Maintenance> findById(long id);

    Maintenance addMaintenanceToLocation(long id, MaintenanceDTO maintenanceDTO);
    Maintenance modifyMaintenance(long id, MaintenanceDTO maintenanceDTO);
    Maintenance modifyMaintenanceByBenefits(long id, float benefits);
    void deleteMaintenance(long id);
}
