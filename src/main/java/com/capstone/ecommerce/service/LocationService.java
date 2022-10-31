package com.capstone.ecommerce.service;

import com.capstone.ecommerce.model.Location;
import com.capstone.ecommerce.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LocationService {

    @Autowired
    LocationRepository locationRepository;

    public List<Location> getAllLocations(){
        return locationRepository.findAll();
    }

    public Location getLocationById(int id) {
        if(!locationRepository.existsById(id)){
        }
        return locationRepository.getLocationById(id);
    }

    public void updateLocation(int id, Location location) {
        if (locationRepository.existsById(id)) {
            location.setId(id);
            locationRepository.save(location);
        }
    }
    public void deleteLocationById (int id){
        locationRepository.deleteById(id);
    }

    public void addLocation(Location location){
        locationRepository.save(location);
    }
}
