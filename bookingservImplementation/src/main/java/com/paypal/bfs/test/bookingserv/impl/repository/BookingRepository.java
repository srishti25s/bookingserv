package com.paypal.bfs.test.bookingserv.impl.repository;

import com.paypal.bfs.test.bookingserv.api.model.Booking;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface BookingRepository extends CrudRepository<Booking,Integer> {

    @Query("select b from Booking b where b.firstName=:fname and b.lastName=:lname and b.totalPrice=:price and b.dateOfBirth=:dob and b.checkInDateTime=:checkin and b.checkOutDateTime=:checkout and b.deposit=:deposit")
    public Booking find(@Param("fname") String fname,
    @Param("lname") String lname,
    @Param("price") Double price,
    @Param("dob") String dob,
    @Param("checkin") String checkin,
    @Param("checkout") String checkout,
    @Param("deposit") Double deposit);
}