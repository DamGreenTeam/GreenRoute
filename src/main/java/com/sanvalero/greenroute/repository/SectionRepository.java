package com.sanvalero.greenroute.repository;

import com.sanvalero.greenroute.domain.Section;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * Creado por @ author: Pedro Orós
 * el 07/04/2021
 */

@Repository
public interface SectionRepository extends CrudRepository<Section, Long> {

    Set<Section> findAll();
}
