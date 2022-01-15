package com.paypal.bfs.test.bookingserv.impl.exception;

public class DuplicateBookingException extends RuntimeException{
    public DuplicateBookingException(String message){
        super(message);
    }
}
