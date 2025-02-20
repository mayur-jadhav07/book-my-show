package com.mayur.bookmyshowapplication.Controllers;

import com.mayur.bookmyshowapplication.DTOs.ShowDTO;
import com.mayur.bookmyshowapplication.DTOs.ShowSeatDTO;
import com.mayur.bookmyshowapplication.Models.Show;
import com.mayur.bookmyshowapplication.Service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("shows")
public class ShowController {

    @Autowired
    private ShowService showService;

    @PostMapping("add-show")
    public String addShow(@RequestBody ShowDTO showDTO){
        return showService.addShow(showDTO);
    }

    @PostMapping("add-show-seats")
    public String addShowSeats(@RequestBody ShowSeatDTO showSeatDTO){
        return showService.addShowSeats(showSeatDTO);
    }

    @GetMapping("shows-by-movies")
    public ResponseEntity getShowsByMovies(@RequestParam String movieName){
        List<Show> showsByMovies = showService.showsByMovie(movieName);
        return new ResponseEntity<>(showsByMovies, HttpStatus.OK);
    }

    @GetMapping("shows-by-theatre")
    public ResponseEntity getShowsByTheatre(@RequestParam String theatreName){
        List<Show> showsByTheatre = showService.showsByTheatre(theatreName);
        return new ResponseEntity(showsByTheatre, HttpStatus.OK);
    }
}
