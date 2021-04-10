package com.sanvalero.greenroute.service.road;

import com.sanvalero.greenroute.domain.Road;
import com.sanvalero.greenroute.domain.dto.RoadDTO;

import java.util.Optional;
import java.util.Set;

/**
 * Creado por @author: Javier
 * el 06/04/2021
 */
public interface RoadService {

    Optional<Road> findById(long id);
    Set<Road> findAll();
    Road addRoad(Road road);
    Road modifyRoad(long id, RoadDTO roadDTO);
    Road modifyRoadByLength(long id, float length);
    void deleteRoad(long id);

}
