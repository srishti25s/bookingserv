package com.paypal.bfs.test.bookingserv.impl.repository;

import com.paypal.bfs.test.bookingserv.api.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking,Integer>{

}