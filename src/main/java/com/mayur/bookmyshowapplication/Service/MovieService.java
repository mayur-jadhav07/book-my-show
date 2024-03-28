package com.mayur.bookmyshowapplication.Service;

import com.mayur.bookmyshowapplication.DTOs.MovieDTO;
import com.mayur.bookmyshowapplication.Models.Movie;
import org.springframework.stereotype.Service;

@Service
public class MovieService {
    public String addMovie(MovieDTO movieDTO) {
        Movie movie = Movie.builder()
                .title(movieDTO.getTitle())
                .releaseDate(movieDTO.getReleaseDate())
                .movieFormat(movieDTO.getMovieFormat())
                .rating(movieDTO.getRating())
                .duration(movieDTO.getDuration())
                .cast(movieDTO.getCast())
                .language(movieDTO.getLanguage())
                .build();

        return movie.getTitle() + "Added Successfully!";
    }
}
