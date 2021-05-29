package com.example.demo.service;

import com.example.demo.entity.Location;
import com.example.demo.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {
    @Autowired
    private LocationRepository repository;

    public List<Location> getAll() {
        return repository.findAll();
    }

    public Location getById(Long id)
    {
        return repository.findById(id).orElse(null);
    }
}
