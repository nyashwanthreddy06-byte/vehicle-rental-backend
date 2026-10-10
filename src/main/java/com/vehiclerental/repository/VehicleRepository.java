package com.vehiclerental.repository;

import com.vehiclerental.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {
    List<Vehicle> findByStatus(String status);
    List<Vehicle> findByVehicleType(String vehicleType);
}