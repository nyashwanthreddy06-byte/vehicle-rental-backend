package com.vehiclerental.controller;

import com.vehiclerental.entity.Booking;
import com.vehiclerental.service.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = "*")
public class BookingController {

    private final BookingService service;

    public BookingController(BookingService service) {
        this.service = service;
    }

    @GetMapping
    public List<Booking> getAllBookings() {
        return service.getAllBookings();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.getBookingById(id));
    }

    @GetMapping("/customer/{customerId}")
    public List<Booking> getByCustomer(@PathVariable Integer customerId) {
        return service.getBookingsByCustomer(customerId);
    }

    /**
     * Create booking — expects JSON body with startDate, endDate
     * plus query params customerId and vehicleId.
     *
     * Example POST /api/bookings?customerId=1&vehicleId=2
     * Body: { "startDate": "2024-12-01", "endDate": "2024-12-05" }
     */
    @PostMapping
    public ResponseEntity<Booking> createBooking(
            @RequestParam Integer customerId,
            @RequestParam Integer vehicleId,
            @RequestBody Booking booking) {
        return ResponseEntity.ok(service.createBooking(customerId, vehicleId, booking));
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<Booking> cancelBooking(@PathVariable Integer id) {
        return ResponseEntity.ok(service.cancelBooking(id));
    }

    @PutMapping("/{id}/complete")
    public ResponseEntity<Booking> completeBooking(@PathVariable Integer id) {
        return ResponseEntity.ok(service.completeBooking(id));
    }
}
