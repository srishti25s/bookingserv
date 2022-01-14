package com.paypal.bfs.test.bookingserv.impl.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.paypal.bfs.test.bookingserv.api.model.Address;
import com.paypal.bfs.test.bookingserv.api.model.Booking;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
class BookingResourceImplTest {

    @Autowired
    private MockMvc mockMvc;
    private final String BASE_URL = "/v1/bfs";

    @Test
    @DisplayName("Missing input fields")
    public void invalid_input_case() throws Exception{
        final String URL = BASE_URL+"/booking";
        Booking b = new Booking();
        MvcResult result = mockMvc
                .perform(
                        post(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(b.toString())
                ).andDo(print())
                .andReturn();
        int expected = HttpStatus.BAD_REQUEST.value();
        int actual = result.getResponse().getStatus();
        assertEquals(expected,actual);
    }
    @Test
    @DisplayName("Valid input fields")
    public void valid_input() throws Exception{
        final String URL = BASE_URL+"/booking";
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
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(b);

        MvcResult result = mockMvc
                .perform(
                        post(URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestJson)
                ).andDo(print())
                .andReturn();
        int expected = HttpStatus.CREATED.value();
        int actual = result.getResponse().getStatus();
        assertEquals(expected,actual);
    }

}
