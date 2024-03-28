package com.mayur.bookmyshowapplication.Repository;

import com.mayur.bookmyshowapplication.Models.Movie;
import com.mayur.bookmyshowapplication.Models.Show;
import com.mayur.bookmyshowapplication.Models.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ShowRepository extends JpaRepository<Show, Integer> {
    Show findShowByShowDateAndShowTimeAndMovieAndTheatre(LocalDate showDate, LocalTime showTime, Movie movie, Theatre theatre);
    List<Show> findAllByMovie(Movie movieName);

    List<Show> findAllByTheatre(Theatre theatre);
}
