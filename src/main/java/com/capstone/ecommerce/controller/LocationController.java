package com.capstone.ecommerce.controller;
import com.capstone.ecommerce.model.Location;
import com.capstone.ecommerce.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/locations")
public class LocationController {
    @Autowired
    LocationService locationService;
    @GetMapping
    private List<Location> getAllLocations(){
        return locationService.getAllLocations();
    }
    @GetMapping("/{locationID}")
    private Location getLocationByID(@PathVariable("locationID") int locationID) {
        return locationService.getLocationById(locationID);
    }
    @DeleteMapping("/{locationID}")
    private ResponseEntity deleteLocation(@PathVariable("locationID") int locationID) {
        locationService.deleteLocationById(locationID);
        return new ResponseEntity(HttpStatus.OK);
    }
    @PostMapping
    private ResponseEntity saveLocation(@RequestBody Location location) {
        locationService.addLocation(location);
        return new ResponseEntity(HttpStatus.CREATED);
    }
    @PutMapping("/{locationID}")
    private ResponseEntity updateLocation(@PathVariable("locationID") int locationID, @RequestBody Location location){
        locationService.updateLocation(locationID, location);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
