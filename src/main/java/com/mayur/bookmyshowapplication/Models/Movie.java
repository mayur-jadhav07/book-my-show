package com.mayur.bookmyshowapplication.Models;

import com.mayur.bookmyshowapplication.Enums.Language;
import com.mayur.bookmyshowapplication.Enums.MovieFormat;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "movies")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer movieId;

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
