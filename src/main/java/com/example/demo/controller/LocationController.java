package com.example.demo.controller;

import com.example.demo.entity.Location;
import com.example.demo.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class LocationController {

    @Autowired
    private LocationService service;

    public LocationController(LocationService service) {
        this.service = service;
    }

    @GetMapping(path = "/locations/get")
    public List<Location> getAll() {
        return service.getAll();
    }
}
