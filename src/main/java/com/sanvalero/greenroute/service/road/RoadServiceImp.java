package com.sanvalero.greenroute.service.road;

import com.sanvalero.greenroute.domain.Road;
import com.sanvalero.greenroute.domain.dto.RoadDTO;
import com.sanvalero.greenroute.exception.RoadNotFoundException;
import com.sanvalero.greenroute.repository.RoadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

/**
 * Creado por @ author: Pedro Orós
 * el 07/04/2021
 */

@Service
public class RoadServiceImp implements RoadService {

    @Autowired
    private RoadRepository roadRepository;

    @Override
    public Optional<Road> findById(long id) {
        return roadRepository.findById(id);
    }

    @Override
    public Set<Road> findAll() {
        return roadRepository.findAll();
    }

    @Override
    public Road addRoad(Road road) {
        return roadRepository.save(road);
    }

    @Override
    public Road modifyRoad(long id, RoadDTO roadDTO) {
        Road road = roadRepository.findById(id)
                .orElseThrow(() -> new RoadNotFoundException(id));
        setRoad(road, roadDTO);
        return roadRepository.save(road);
    }

    @Override
    public Road modifyRoadByLength(long id, float length) {
        Road road = roadRepository.findById(id)
                .orElseThrow(() -> new RoadNotFoundException(id));
        road.setLength(length);
        return roadRepository.save(road);
    }

    @Override
    public void deleteRoad(long id) {
        Road road = roadRepository.findById(id)
                .orElseThrow(() -> new RoadNotFoundException(id));
        roadRepository.delete(road);
    }

    public void setRoad(Road road, RoadDTO roadDTO){
        road.setName(roadDTO.getName());
        road.setLength(roadDTO.getLength());
        road.setToll(roadDTO.isToll());
        road.setBuildDate(roadDTO.getBuildDate());
    }
}
