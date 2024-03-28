package com.mayur.bookmyshowapplication.Repository;

import com.mayur.bookmyshowapplication.Models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, String> {
}
