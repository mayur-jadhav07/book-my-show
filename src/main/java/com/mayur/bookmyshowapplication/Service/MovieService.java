package com.mayur.bookmyshowapplication.Service;

import com.mayur.bookmyshowapplication.DTOs.MovieDTO;
import com.mayur.bookmyshowapplication.Models.Movie;
import com.mayur.bookmyshowapplication.Repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

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

        movie = movieRepository.save(movie);

        return movie.getTitle() + " Added Successfully!";
    }
}
