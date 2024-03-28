package com.mayur.bookmyshowapplication.Controllers;


import com.mayur.bookmyshowapplication.DTOs.MovieDTO;
import com.mayur.bookmyshowapplication.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("movies")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @PostMapping("add-movie")
    public String addMovie(@RequestBody MovieDTO movieDTO){
        return movieService.addMovie(movieDTO);
    }

}
