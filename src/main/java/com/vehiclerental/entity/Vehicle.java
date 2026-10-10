package com.vehiclerental.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "vehicles")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Vehicle name is required")
    @Column(name = "vehicle_name")
    private String vehicleName;

    @NotBlank(message = "Vehicle type is required")
    @Column(name = "vehicle_type")
    private String vehicleType;

    @NotBlank(message = "Model is required")
    private String model;

    @NotBlank(message = "Number plate is required")
    @Column(name = "number_plate", unique = true)
    private String numberPlate;

    /** Price per day in Indian Rupees (INR ₹) */
    @NotNull(message = "Price per day is required")
    @Min(value = 1, message = "Price must be at least ₹1")
    @Column(name = "price_per_day")
    private Double pricePerDay;

    /** AVAILABLE or RENTED */
    private String status = "AVAILABLE";

    public Vehicle() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getVehicleName() { return vehicleName; }
    public void setVehicleName(String vehicleName) { this.vehicleName = vehicleName; }

    public String getVehicleType() { return vehicleType; }
    public void setVehicleType(String vehicleType) { this.vehicleType = vehicleType; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public String getNumberPlate() { return numberPlate; }
    public void setNumberPlate(String numberPlate) { this.numberPlate = numberPlate; }

    public Double getPricePerDay() { return pricePerDay; }
    public void setPricePerDay(Double pricePerDay) { this.pricePerDay = pricePerDay; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}