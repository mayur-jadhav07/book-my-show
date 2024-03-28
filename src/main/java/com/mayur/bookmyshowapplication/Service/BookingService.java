package com.mayur.bookmyshowapplication.Service;

import com.mayur.bookmyshowapplication.DTOs.BookTicketDTO;
import com.mayur.bookmyshowapplication.Exceptions.SeatUnavailableException;
import com.mayur.bookmyshowapplication.Models.*;
import com.mayur.bookmyshowapplication.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private TheatreRepository theatreRepository;

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private ShowSeatRepository showSeatRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookingRepository bookingRepository;

    public Booking bookTicket(BookTicketDTO bookTicketDTO) throws Exception {
        //1. Calculate the total cost of the tickets

        Movie movie = movieRepository.findByTitle(bookTicketDTO.getMovieName());
        Theatre theatre = theatreRepository.findById(bookTicketDTO.getTheatreId()).get();

        //1.1 Find the ShowEntity with this date and Time
        Show show = showRepository.findShowByShowDateAndShowTimeAndMovieAndTheatre(bookTicketDTO.getShowDate(), bookTicketDTO.getShowTime(), movie, theatre);


        Integer showId = show.getShowId();
        List<ShowSeat> showSeatList = showSeatRepository.findShowSeats(showId);

        //Calculate the total Amt and if all seats mentioned are available or not
        Double totalAmount = 0.0;
        Boolean areAllSeatsAvailable = Boolean.TRUE;

        for(String seatNo:bookTicketDTO.getRequestedSeats()) {
            for(ShowSeat showSeat:showSeatList) {
                if(showSeat.getSeatNumber().equals(seatNo))
                {
                    if(showSeat.getIsBooked() == Boolean.FALSE){
                        areAllSeatsAvailable = Boolean.FALSE;
                        break;
                    }
                    totalAmount = totalAmount+showSeat.getPrice();
                }
            }
        }

        if(areAllSeatsAvailable==Boolean.FALSE){
            throw new SeatUnavailableException("The requested Seats are unavailable");
        }


        //2. Make the seats booked:(Only if seats are available : otherwise throw exception)

        for(String seatNo:bookTicketDTO.getRequestedSeats()) {
            for(ShowSeat showSeat:showSeatList) {
                if(showSeat.getSeatNumber().equals(seatNo))
                {
                    showSeat.setIsBooked(Boolean.FALSE);
                }
            }
        }

        User user = userRepository.findUserByMobNo(bookTicketDTO.getMobileNo());

        //3. Save the ticketEntity

        Booking ticket = Booking.builder().user(user)
                .movieName(bookTicketDTO.getMovieName())
                .showDate(bookTicketDTO.getShowDate())
                .theaterNameAndAddress(theatre.getName()+" "+theatre.getAddress())
                .showTime(bookTicketDTO.getShowTime())
                .totalPrice(totalAmount)
                .build();

        ticket = bookingRepository.save(ticket);

        //4. Generate and return back the actual ticket response

        return ticket;
    }

    public boolean cancelTicket(String ticketId) {
        Optional<Booking> ticketOptional = bookingRepository.findById(ticketId);
        if(ticketOptional.isPresent()){
            Booking ticket = ticketOptional.get();
            bookingRepository.delete(ticket);
            return true;
        }
        return false;
    }
}
