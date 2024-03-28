package com.mayur.bookmyshowapplication.DTOs;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
public class BookTicketDTO {

    private String movieName;

    private LocalDate showDate;

    private LocalTime showTime;

    private List<String> requestedSeats;

    private Integer theatreId;

    private String mobileNo;
}
