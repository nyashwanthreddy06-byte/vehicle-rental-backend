package com.vehiclerental.service;

import com.vehiclerental.entity.Booking;
import com.vehiclerental.entity.Customer;
import com.vehiclerental.entity.Vehicle;
import com.vehiclerental.repository.BookingRepository;
import com.vehiclerental.repository.CustomerRepository;
import com.vehiclerental.repository.VehicleRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final CustomerRepository customerRepository;
    private final VehicleRepository vehicleRepository;

    public BookingService(BookingRepository bookingRepository,
                          CustomerRepository customerRepository,
                          VehicleRepository vehicleRepository) {
        this.bookingRepository = bookingRepository;
        this.customerRepository = customerRepository;
        this.vehicleRepository = vehicleRepository;
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Booking getBookingById(Integer id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Booking not found with id: " + id));
    }

    /**
     * Creates a new booking and automatically:
     *  - Calculates rental days
     *  - Calculates total cost in INR (₹)
     *  - Marks the vehicle as RENTED
     */
    @Transactional
    public Booking createBooking(Integer customerId, Integer vehicleId, Booking booking) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found: " + customerId));
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new EntityNotFoundException("Vehicle not found: " + vehicleId));

        if (!"AVAILABLE".equalsIgnoreCase(vehicle.getStatus())) {
            throw new IllegalStateException("Vehicle is not available for rent");
        }

        // Calculate rental days and total cost in INR
        long days = ChronoUnit.DAYS.between(booking.getStartDate(), booking.getEndDate());
        if (days <= 0) {
            throw new IllegalArgumentException("End date must be after start date");
        }

        booking.setCustomer(customer);
        booking.setVehicle(vehicle);
        booking.setRentalDays((int) days);
        booking.setTotalCostInr(days * vehicle.getPricePerDay());
        booking.setStatus("CONFIRMED");

        // Mark vehicle as RENTED
        vehicle.setStatus("RENTED");
        vehicleRepository.save(vehicle);

        return bookingRepository.save(booking);
    }

    /**
     * Cancel a booking and mark vehicle AVAILABLE again.
     */
    @Transactional
    public Booking cancelBooking(Integer bookingId) {
        Booking booking = getBookingById(bookingId);
        booking.setStatus("CANCELLED");

        Vehicle vehicle = booking.getVehicle();
        vehicle.setStatus("AVAILABLE");
        vehicleRepository.save(vehicle);

        return bookingRepository.save(booking);
    }

    /**
     * Complete a booking and mark vehicle AVAILABLE again.
     */
    @Transactional
    public Booking completeBooking(Integer bookingId) {
        Booking booking = getBookingById(bookingId);
        booking.setStatus("COMPLETED");

        Vehicle vehicle = booking.getVehicle();
        vehicle.setStatus("AVAILABLE");
        vehicleRepository.save(vehicle);

        return bookingRepository.save(booking);
    }

    public List<Booking> getBookingsByCustomer(Integer customerId) {
        return bookingRepository.findByCustomerId(customerId);
    }
}
