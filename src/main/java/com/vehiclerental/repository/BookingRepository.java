package com.vehiclerental.repository;

import com.vehiclerental.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
    List<Booking> findByCustomerId(Integer customerId);
    List<Booking> findByVehicleId(Integer vehicleId);
    List<Booking> findByStatus(String status);
}
