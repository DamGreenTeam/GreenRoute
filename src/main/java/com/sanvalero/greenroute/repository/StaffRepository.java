package com.sanvalero.greenroute.repository;

import com.sanvalero.greenroute.domain.Staff;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * Creado por @author: Javier
 * el 06/04/2021
 */
@Repository
public interface StaffRepository extends CrudRepository<Staff, Long> {

    Set<Staff> findAll();

}
