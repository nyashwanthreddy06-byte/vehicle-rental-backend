package com.vehiclerental.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * Booking entity — links a Customer to a Vehicle for a rental period.
 * Total cost is stored in Indian Rupees (INR ₹).
 */
@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Customer is required")
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @NotNull(message = "Vehicle is required")
    @ManyToOne
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle vehicle;

    @NotNull(message = "Start date is required")
    @Column(name = "start_date")
    private LocalDate startDate;

    @NotNull(message = "End date is required")
    @Column(name = "end_date")
    private LocalDate endDate;

    /** Total rental days */
    @Column(name = "rental_days")
    private Integer rentalDays;

    /** Total cost in Indian Rupees (INR ₹) = rentalDays × vehicle.pricePerDay */
    @Column(name = "total_cost_inr")
    private Double totalCostInr;

    /** CONFIRMED, ACTIVE, COMPLETED, CANCELLED */
    private String status = "CONFIRMED";

    public Booking() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }

    public Vehicle getVehicle() { return vehicle; }
    public void setVehicle(Vehicle vehicle) { this.vehicle = vehicle; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    public Integer getRentalDays() { return rentalDays; }
    public void setRentalDays(Integer rentalDays) { this.rentalDays = rentalDays; }

    public Double getTotalCostInr() { return totalCostInr; }
    public void setTotalCostInr(Double totalCostInr) { this.totalCostInr = totalCostInr; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
