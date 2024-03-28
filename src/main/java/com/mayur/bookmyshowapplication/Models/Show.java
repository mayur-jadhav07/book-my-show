package com.mayur.bookmyshowapplication.Models;

import com.mayur.bookmyshowapplication.Enums.ScreenType;
import com.mayur.bookmyshowapplication.Enums.MovieFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "shows")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Show {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer showId;

    private LocalDate showDate;

    private LocalTime showTime;

    private Integer screenNumber;

    @Enumerated(value = EnumType.STRING)
    private ScreenType screenType;

    @ManyToOne
    @JoinColumn
    private Movie movie;

    @ManyToOne
    @JoinColumn
    private Theatre theatre;

    @AssertTrue(message = "Invalid screen number for the theatre")
    public boolean isValidScreenNumber() {
        // Check if the theatre is not null and the screenNumber is within the valid range
        return theatre != null && screenNumber != null && screenNumber >= 1 && screenNumber <= theatre.getTotalScreens();
    }
}
