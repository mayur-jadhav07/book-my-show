package com.mayur.bookmyshowapplication.Controllers;

import com.mayur.bookmyshowapplication.DTOs.TheatreDTO;
import com.mayur.bookmyshowapplication.DTOs.TheatreSeatDTO;
import com.mayur.bookmyshowapplication.DTOs.UserDTO;
import com.mayur.bookmyshowapplication.Models.Theatre;
import com.mayur.bookmyshowapplication.Service.TheatreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Theatres")
public class TheatreController {

    @Autowired
    private TheatreService theatreService;

    @PostMapping("add-theatre")
    public String addTheatre(@RequestBody TheatreDTO theatreDTO){
        return theatreService.createTheatre(theatreDTO);
    }

    @PostMapping("add-theatre-seats")
    public String addTheatreSeats(@RequestBody TheatreSeatDTO theatreSeatDTO){
        return theatreService.addTheaterSeats(theatreSeatDTO);
    }

    @GetMapping("list-of-theatres")
    public ResponseEntity listOfTheatresInCity(@RequestParam String city){
        List<Theatre> theatresByCity = theatreService.listOfTheatresInCity(city);
        return new ResponseEntity(theatresByCity, HttpStatus.OK);
    }
}
