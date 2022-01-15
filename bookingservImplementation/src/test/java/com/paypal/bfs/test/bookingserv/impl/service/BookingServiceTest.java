package com.paypal.bfs.test.bookingserv.impl.service;

import com.paypal.bfs.test.bookingserv.api.model.Address;
import com.paypal.bfs.test.bookingserv.api.model.Booking;
import com.paypal.bfs.test.bookingserv.impl.exception.DuplicateBookingException;
import com.paypal.bfs.test.bookingserv.impl.repository.BookingRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookingServiceTest {

    @Autowired
    @InjectMocks
    private BookingService bookingService;

    @Mock
    private BookingRepository bookingRepository;

    @Test
    @DisplayName("Get All Bookings")
    public void getAllBookings(){
        List<Booking> bookingList = new ArrayList<>();
        when(bookingRepository.findAll()).thenReturn(bookingList);
        List<Booking> actualBookingList = bookingService.getBookings();
        assertEquals(bookingList,actualBookingList);
    }

    @Test
    @DisplayName("Adding a new booking")
    public void addBooking() throws DuplicateBookingException {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Booking b = new Booking();
        b.setFirstName("test");
        b.setLastName("test");
        b.setDeposit(10000.0);
        b.setTotalPrice(10000.0);
        b.setCheckInDateTime(formatter.format(date));
        b.setCheckOutDateTime(formatter.format(date));
        b.setDateOfBirth(formatter.format(date));
        Address add = new Address();
        add.setCity("test");
        add.setState("test");
        add.setLine1("test");
        add.setZipcode(12345);
        b.setAddress(add);

        when(bookingRepository.save(b)).thenReturn(b);
        Booking actualResp = bookingService.create(b);
        assertEquals(b,actualResp);
    }

    @Test
    @DisplayName("Adding duplicate booking")
    public void addDuplicateBooking() throws DuplicateBookingException {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Booking b = new Booking();
        b.setFirstName("test");
        b.setLastName("test");
        b.setDeposit(10000.0);
        b.setTotalPrice(10000.0);
        b.setCheckInDateTime(formatter.format(date));
        b.setCheckOutDateTime(formatter.format(date));
        b.setDateOfBirth(formatter.format(date));
        Address add = new Address();
        add.setCity("test");
        add.setState("test");
        add.setLine1("test");
        add.setZipcode(12345);
        b.setAddress(add);
        when(bookingRepository.save(b)).thenReturn(b);
        bookingService.create(b);
        when(bookingRepository.save(b)).thenThrow(new DuplicateBookingException("Booking already exists!"));
        assertThrows(DuplicateBookingException.class, () -> {
            bookingService.create(b);
            bookingService.create(b);
        });

    }
}