package com.paypal.bfs.test.bookingserv.impl.exception;

public class DuplicateBookingException extends Exception{
    public DuplicateBookingException(String message){
        super(message);
    }
}
