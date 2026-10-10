package com.vehiclerental.service;

import com.vehiclerental.entity.Vehicle;
import com.vehiclerental.repository.VehicleRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VehicleService {

    private final VehicleRepository repository;

    public VehicleService(VehicleRepository repository) {
        this.repository = repository;
    }

    public List<Vehicle> getAllVehicles() {
        return repository.findAll();
    }

    public List<Vehicle> getAvailableVehicles() {
        return repository.findByStatus("AVAILABLE");
    }

    public Vehicle getVehicleById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vehicle not found with id: " + id));
    }

    public Vehicle addVehicle(Vehicle vehicle) {
        vehicle.setStatus("AVAILABLE");
        return repository.save(vehicle);
    }

    public Vehicle updateVehicle(Integer id, Vehicle updatedVehicle) {
        Vehicle existing = getVehicleById(id);
        existing.setVehicleName(updatedVehicle.getVehicleName());
        existing.setVehicleType(updatedVehicle.getVehicleType());
        existing.setModel(updatedVehicle.getModel());
        existing.setNumberPlate(updatedVehicle.getNumberPlate());
        existing.setPricePerDay(updatedVehicle.getPricePerDay());
        existing.setStatus(updatedVehicle.getStatus());
        return repository.save(existing);
    }

    public void deleteVehicle(Integer id) {
        getVehicleById(id); // Ensure exists
        repository.deleteById(id);
    }
}