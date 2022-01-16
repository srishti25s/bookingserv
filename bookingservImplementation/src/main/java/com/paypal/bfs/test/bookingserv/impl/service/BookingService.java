package com.paypal.bfs.test.bookingserv.impl.service;

import com.paypal.bfs.test.bookingserv.api.model.Booking;
import com.paypal.bfs.test.bookingserv.impl.exception.DuplicateBookingException;
import com.paypal.bfs.test.bookingserv.impl.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookingService {

    @Autowired
    BookingRepository bookingRepository;

    public Booking create(Booking booking) throws DuplicateBookingException {
        Booking bookingList = bookingRepository.find(booking.getFirstName(),booking.getLastName(), booking.getTotalPrice(), booking.getDateOfBirth(),booking.getCheckInDateTime(), booking.getCheckOutDateTime(), booking.getDeposit());
        if(bookingList!=null){
            throw new DuplicateBookingException("Booking already exists!");
        }
        Booking b = bookingRepository.save(booking);
        return b;
    }

    public List<Booking> getBookings(){
        List<Booking> bookings = new ArrayList<>();
        bookingRepository.findAll().forEach(bookings::add);
        return bookings;
    }
}
