package com.sanvalero.greenroute.service.location;

import com.sanvalero.greenroute.domain.Location;
import com.sanvalero.greenroute.domain.dto.LocationDTO;

import java.util.Optional;
import java.util.Set;

/**
 * Creado por @ author: Pedro Orós
 * el 06/04/2021
 */

public interface LocationService {

    Set<Location> findAll();
    Optional<Location> findById(long id);

    Location addLocation(Location location);
    Location modifyLocation(long id, LocationDTO locationDTO);
    Location modifyLocationByAssistance(long id, boolean assistance);
    void deleteLocation(long id);
}
