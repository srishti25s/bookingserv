package com.paypal.bfs.test.bookingserv.impl;

import com.paypal.bfs.test.bookingserv.api.BookingResource;
import com.paypal.bfs.test.bookingserv.api.model.Booking;
import com.paypal.bfs.test.bookingserv.impl.exception.DuplicateBookingException;
import com.paypal.bfs.test.bookingserv.impl.service.BookingService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(value="Booking", description="endpoints for booking")
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
        return new ResponseEntity<>(bookingService.getBookings(),HttpStatus.OK);
    }
}
