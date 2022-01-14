package com.paypal.bfs.test.bookingserv.impl.repository;

import com.paypal.bfs.test.bookingserv.api.model.Booking;
import org.springframework.data.repository.CrudRepository;

public interface BookingRepository extends CrudRepository<Booking,Integer> {

}