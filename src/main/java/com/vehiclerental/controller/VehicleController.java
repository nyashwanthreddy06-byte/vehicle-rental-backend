package com.vehiclerental.controller;

import com.vehiclerental.entity.Vehicle;
import com.vehiclerental.service.VehicleService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
@CrossOrigin(origins = "*")
public class VehicleController {

    private final VehicleService service;

    public VehicleController(VehicleService service) {
        this.service = service;
    }

    @GetMapping
    public List<Vehicle> getAllVehicles() {
        return service.getAllVehicles();
    }

    @GetMapping("/available")
    public List<Vehicle> getAvailableVehicles() {
        return service.getAvailableVehicles();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.getVehicleById(id));
    }

    @PostMapping
    public ResponseEntity<Vehicle> addVehicle(@Valid @RequestBody Vehicle vehicle) {
        return ResponseEntity.ok(service.addVehicle(vehicle));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vehicle> updateVehicle(@PathVariable Integer id,
                                                  @Valid @RequestBody Vehicle vehicle) {
        return ResponseEntity.ok(service.updateVehicle(id, vehicle));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Integer id) {
        service.deleteVehicle(id);
        return ResponseEntity.noContent().build();
    }
}