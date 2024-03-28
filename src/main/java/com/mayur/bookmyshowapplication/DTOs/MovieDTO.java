package com.mayur.bookmyshowapplication.DTOs;

import com.mayur.bookmyshowapplication.Enums.Language;
import com.mayur.bookmyshowapplication.Enums.MovieFormat;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class MovieDTO {
    private String title;

    private LocalDate releaseDate;

    private Double rating;

    private Double duration;

    @Enumerated(value = EnumType.STRING)
    private Language language;

    @Enumerated(value = EnumType.STRING)
    private MovieFormat movieFormat;

    private String cast;
}
