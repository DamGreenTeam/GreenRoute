package com.sanvalero.greenroute.repository;

import com.sanvalero.greenroute.domain.Maintenance;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * Creado por @ author: Pedro Orós
 * el 06/04/2021
 */

@Repository
public interface MaintenanceRepository extends CrudRepository<Maintenance, Long> {

    Set<Maintenance> findAll();
}
