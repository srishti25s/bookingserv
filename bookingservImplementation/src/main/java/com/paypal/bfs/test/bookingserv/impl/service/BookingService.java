package com.paypal.bfs.test.bookingserv.impl.service;

import com.paypal.bfs.test.bookingserv.api.model.Booking;
import com.paypal.bfs.test.bookingserv.impl.exception.DuplicateBookingException;
import com.paypal.bfs.test.bookingserv.impl.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class BookingService {

    @Autowired
    BookingRepository bookingRepository;

    public Booking create(Booking booking) throws DuplicateBookingException {
        List<Booking> bookingList = getBookings();
        AtomicBoolean duplicate = new AtomicBoolean(false);
        bookingList.forEach( a -> {
            System.out.println(a);
            if ((a.getFirstName().equals(booking.getFirstName()))
                            && (a.getLastName().equals(booking.getLastName()))
                            && (a.getCheckInDateTime().equals(booking.getCheckInDateTime()))
                            && (a.getDateOfBirth().equals(booking.getDateOfBirth()))
                            && (a.getAddress().getCity().equals(booking.getAddress().getCity()))
                            && (a.getAddress().getLine1().equals(booking.getAddress().getLine1()))
                            && (a.getAddress().getState().equals(booking.getAddress().getState()))
                            && (a.getAddress().getZipcode().equals(booking.getAddress().getZipcode()))
            ){
                duplicate.set(true);
            }
        });

        if(duplicate.get()){
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
