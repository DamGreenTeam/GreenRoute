package com.sanvalero.greenroute.repository;

import com.sanvalero.greenroute.domain.Maintenance;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

/**
 * Creado por @author: Javier
 * el 06/04/2021
 */
public interface MaintenanceRepository extends CrudRepository<Maintenance, Long> {

    Set<Maintenance> findAll();

}
