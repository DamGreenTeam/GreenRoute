package com.sanvalero.greenroute.controller.webcontroller;

import com.sanvalero.greenroute.domain.Location;
import com.sanvalero.greenroute.service.location.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

/**
 * Creado por @ author: Pedro Orós
 * el 07/04/2021
 */

@Controller
public class WebLocationController {

    @Autowired
    private LocationService locationService;

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
}
