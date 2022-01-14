package com.paypal.bfs.test.bookingserv.impl;

import com.paypal.bfs.test.bookingserv.api.BookingResource;
import com.paypal.bfs.test.bookingserv.api.model.Booking;
import com.paypal.bfs.test.bookingserv.api.model.Address;
import com.paypal.bfs.test.bookingserv.impl.exception.DuplicateBookingException;
import com.paypal.bfs.test.bookingserv.impl.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class BookingResourceImpl implements BookingResource {

    @Autowired
    BookingService bookingService;

    @Override
    public ResponseEntity create(Booking booking) {
        ResponseEntity<Booking> response = null;
        try{
            response = new ResponseEntity<Booking>(bookingService.create(booking), HttpStatus.CREATED);
        }catch (DuplicateBookingException dup){
            return new ResponseEntity(dup.getMessage(),HttpStatus.BAD_REQUEST);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return response;
    }

    @Override
    public ResponseEntity<List<Booking>> getBookings() {
        return new ResponseEntity<>(bookingService.getBookings(),HttpStatus.FOUND);
    }
}
