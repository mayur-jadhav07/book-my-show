package com.mayur.bookmyshowapplication.DTOs;

import com.mayur.bookmyshowapplication.Enums.ScreenType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ShowDTO {

    private LocalDate showDate;

    private LocalTime showTime;

    private String movieName;

    private Integer theaterId;

    private int screenNo;

    @Enumerated(value = EnumType.STRING)
    private ScreenType screenType;
}
