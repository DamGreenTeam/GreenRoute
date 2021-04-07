package com.sanvalero.greenroute.service.location;

import com.sanvalero.greenroute.domain.Location;
import com.sanvalero.greenroute.domain.dto.LocationDTO;
import com.sanvalero.greenroute.exception.LocationNotFoundException;
import com.sanvalero.greenroute.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

/**
 * Creado por @ author: Pedro Orós
 * el 06/04/2021
 */

@Service
public class LocationServiceImp implements LocationService {

    @Autowired
    private LocationRepository locationRepository;

    @Override
    public Set<Location> findAll() {
        return locationRepository.findAll();
    }

    @Override
    public Optional<Location> findById(long id) {
        return locationRepository.findById(id);
    }

    @Override
    public Location findByName(String name) {
        return locationRepository.findByName(name);
    }

    @Override
    public Location addLocation(Location location) {
        return locationRepository.save(location);
    }

    @Override
    public Location modifyLocation(long id, LocationDTO locationDTO) {
        Location location = locationRepository.findById(id)
                .orElseThrow(() -> new LocationNotFoundException(id));
        setLocation(location, locationDTO);
        return locationRepository.save(location);
    }

    @Override
    public Location modifyLocationByAssistance(long id, boolean assistance) {
        Location location = locationRepository.findById(id)
                .orElseThrow(() -> new LocationNotFoundException(id));
        location.setAssistance(assistance);
        return locationRepository.save(location);
    }

    @Override
    public void deleteLocation(long id) {
        Location location = locationRepository.findById(id)
                .orElseThrow(() -> new LocationNotFoundException(id));
        locationRepository.delete(location);
    }

    public void setLocation(Location location, LocationDTO locationDTO) {
        location.setName(locationDTO.getName());
        location.setExtension(locationDTO.getExtension());
        location.setAssistance(locationDTO.isAssistance());
        location.setStartingDate(locationDTO.getStartingDate());
    }
}
