package com.sanvalero.greenroute.controller.webcontroller;

import com.sanvalero.greenroute.domain.Location;
import com.sanvalero.greenroute.domain.Maintenance;
import com.sanvalero.greenroute.domain.Road;
import com.sanvalero.greenroute.exception.LocationNotFoundException;
import com.sanvalero.greenroute.exception.MaintenanceNotFoundException;
import com.sanvalero.greenroute.service.location.LocationService;
import com.sanvalero.greenroute.service.maintenance.MaintenanceService;
import com.sanvalero.greenroute.service.road.RoadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Creado por @ author: Pedro Orós
 * el 07/04/2021
 */

@Controller
public class WebController {

    @Autowired
    private LocationService locationService;

    @Autowired
    private MaintenanceService maintenanceService;

    @Autowired
    private RoadService roadService;

    @RequestMapping(value = "/")
    public String index(Model model) {
        return "index";
    }

    @RequestMapping("/location_list")
    public String location_list(Model model) {
        Set<Location> locations = locationService.findAll();
        model.addAttribute("locations", locations);
        return "location_list";
    }

    @RequestMapping(value = "detail_location/{id}", method = GET)
    public String detail_location(Model model, @PathVariable("id") long id) {
        Location location = locationService.findById(id)
                .orElseThrow(() -> new LocationNotFoundException(id));
        model.addAttribute("location", location);
        return "detail_location";
    }

    @RequestMapping("/maintenance_list")
    public String maintenance_list(Model model) {
        Set<Maintenance> maintenances = maintenanceService.findAll();
        model.addAttribute("maintenances", maintenances);
        return "maintenance_list";
    }

    @RequestMapping(value = "detail_maintenance/{id}", method = GET)
    public String detail_maintenance(Model model, @PathVariable("id") long id) {
        Maintenance maintenance = maintenanceService.findById(id)
                .orElseThrow(() -> new MaintenanceNotFoundException(id));
        model.addAttribute("maintenance", maintenance);
        return "detail_maintenance";
    }

    @RequestMapping("/road_list")
    public String road_list(Model model) {
        Set<Road> roads = roadService.findAll();
        model.addAttribute("roads", roads);
        return "road_list";
    }
}
