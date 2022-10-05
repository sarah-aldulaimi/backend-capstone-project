package com.capstone.ecommerce.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.capstone.ecommerce.model.Location;

public interface LocationRepository extends JpaRepository<Location, Integer> {
    public Location getLocationById(Integer id);
}

