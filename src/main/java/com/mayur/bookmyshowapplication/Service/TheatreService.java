package com.mayur.bookmyshowapplication.Service;

import com.mayur.bookmyshowapplication.DTOs.TheatreDTO;
import com.mayur.bookmyshowapplication.DTOs.TheatreSeatDTO;
import com.mayur.bookmyshowapplication.Enums.SeatType;
import com.mayur.bookmyshowapplication.Models.Theatre;
import com.mayur.bookmyshowapplication.Models.TheatreSeat;
import com.mayur.bookmyshowapplication.Repository.TheatreRepository;
import com.mayur.bookmyshowapplication.Repository.TheatreSeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TheatreService {

    @Autowired
    private TheatreRepository theatreRepository;

    @Autowired
    private TheatreSeatRepository theatreSeatRepository;

    public String createTheatre(TheatreDTO theatreDTO) {
        Theatre theatre = Theatre.builder()
                .name(theatreDTO.getName())
                .city(theatreDTO.getCity())
                .address(theatreDTO.getAddress())
                .totalScreens(theatreDTO.getTotalScreens())
                .build();

        theatre = theatreRepository.save(theatre);

        return theatre.getName() +" Added successfylly!";
    }

    public String addTheaterSeats(TheatreSeatDTO theatreSeatDTO){

        int noOfClassicSeats = theatreSeatDTO.getNoOfClassicSeats();
        int noOfPremiumSeats = theatreSeatDTO.getNoOfPremiumSeats();

        Integer theaterId = theatreSeatDTO.getTheatreId();
        Theatre theatre = theatreRepository.findById(theaterId).get();

        int classicSeatCounter = 1;
        char ch='A';
        int rowNo = 1;
        List<TheatreSeat> theaterSeatList = new ArrayList<>();
        while(classicSeatCounter<=noOfClassicSeats) {

            String seatNo = rowNo+""+ch;
            TheatreSeat theaterSeat = TheatreSeat.builder()
                    .seatNo(seatNo)
                    .seatType(SeatType.CLASSIC)
                    .theatre(theatre)
                    .build();

            theaterSeatList.add(theaterSeat);
            ch++;
            if(classicSeatCounter%5==0) {
                rowNo = rowNo+1;
                ch = 'A';
            }
            classicSeatCounter++;
        }
        int premiumSeatCounter = 1;
        ch='A';

        if(classicSeatCounter%5!=1)
            rowNo = rowNo+1;


        while(premiumSeatCounter<=noOfPremiumSeats){

            String seatNo = rowNo+""+ch;
            TheatreSeat theaterSeat = TheatreSeat.builder()
                    .seatNo(seatNo)
                    .theatre(theatre) //Setting the unidirectional
                    .seatType(SeatType.PREMIUM)
                    .build();

            theaterSeatList.add(theaterSeat);
            ch++;
            if(premiumSeatCounter%5==0) {
                rowNo = rowNo+1;
                ch = 'A';
            }
            premiumSeatCounter++;
        }

        theatre.setTheaterSeatList(theaterSeatList);
        theatreRepository.save(theatre);

        //Theater seats will get automatically saved
        //bcz of cascading property written in the parent table
        return "Theater seats have been generated";
    }

    public List<Theatre> listOfTheatresInCity(String city) {
        return theatreRepository.findAllByCity(city);
    }
}
