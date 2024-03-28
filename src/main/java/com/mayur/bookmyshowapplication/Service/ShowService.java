package com.mayur.bookmyshowapplication.Service;

import com.mayur.bookmyshowapplication.DTOs.ShowDTO;
import com.mayur.bookmyshowapplication.DTOs.ShowSeatDTO;
import com.mayur.bookmyshowapplication.Enums.SeatType;
import com.mayur.bookmyshowapplication.Models.*;
import com.mayur.bookmyshowapplication.Repository.MovieRepository;
import com.mayur.bookmyshowapplication.Repository.ShowRepository;
import com.mayur.bookmyshowapplication.Repository.ShowSeatRepository;
import com.mayur.bookmyshowapplication.Repository.TheatreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShowService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private TheatreRepository theatreRepository;

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private ShowSeatRepository showSeatRepository;

    public String addShow(ShowDTO showDTO) {
        Movie movie = movieRepository.findByTitle(showDTO.getMovieName());

        Theatre theatre = theatreRepository.findById(showDTO.getTheaterId()).get();

        Show show = Show.builder()
                .showDate(showDTO.getShowDate())
                .showTime(showDTO.getShowTime())
                .movie(movie)
                .theatre(theatre)
                .screenNumber(showDTO.getScreenNo())
                .screenType(showDTO.getScreenType())
                .build();

        show = showRepository.save(show);

        return "The show has been saved to the DB with showId"+ show.getShowId();
    }

    public String addShowSeats(ShowSeatDTO showSeatDTO) {
        Integer showId = showSeatDTO.getShowId();
        Show show = showRepository.findById(showId).get();

        Theatre theatre = show.getTheatre();
        List<TheatreSeat> theatreSeatList = theatre.getTheaterSeatList();

        //Your goal is generation of show Seat List
        List<ShowSeat> showSeatList = new ArrayList<>();

        for(TheatreSeat theatreSeat:theatreSeatList){

            ShowSeat showSeat = ShowSeat.builder()
                    .seatNumber(theatreSeat.getSeatNo())
                    .seatType(theatreSeat.getSeatType())
                    .isBooked(Boolean.TRUE)
                    .show(show)
                    .build();

            if(theatreSeat.getSeatType().equals(SeatType.CLASSIC)){
                showSeat.setPrice(showSeatDTO.getPriceOfClassicSeat());
            }else if(theatreSeat.getSeatType().equals(SeatType.VIP)){
                showSeat.setPrice(showSeatDTO.getPriceOfVIPSeat());
            }else if(theatreSeat.getSeatType().equals(SeatType.PREMIUM)){
                showSeat.setPrice(showSeatDTO.getPriceOfPremiumSeat());
            } else if(theatreSeat.getSeatType().equals(SeatType.REGULAR)){
                showSeat.setPrice(showSeatDTO.getPriceOfRegularSeat());
            }else{
                throw new IllegalArgumentException("Enter valid seat type");
            }

            showSeatList.add(showSeat);
        }

        showSeatRepository.saveAll(showSeatList);

        return "Show seats have been generated for the given showId";
    }

    public List<Show> showsByMovie(String movieName) {
        Movie movie = movieRepository.findByTitle(movieName);
        List<Show> showsOfMovie = showRepository.findAllByMovie(movie);
        return showsOfMovie;
    }

    public List<Show> showsByTheatre(String theatreName) {
        Theatre theatre = theatreRepository.findByName(theatreName);
        List<Show> showsOfTheatre = showRepository.findAllByTheatre(theatre);
        return showsOfTheatre;
    }
}
