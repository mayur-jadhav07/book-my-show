package com.mayur.bookmyshowapplication.Controllers;

import com.mayur.bookmyshowapplication.DTOs.BookTicketDTO;
import com.mayur.bookmyshowapplication.Models.Booking;
import com.mayur.bookmyshowapplication.Service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("book-ticket")
    public ResponseEntity bookTicket(@RequestBody BookTicketDTO bookTicketDTO){
        try {
            Booking ticket = bookingService.bookTicket(bookTicketDTO);
            return new ResponseEntity(ticket, HttpStatus.OK);

        }catch (Exception e) {
            String errMsg = "Error while booking you tickets : "+e.getMessage();
            return new ResponseEntity(errMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("cancel/{ticketId}")
    public ResponseEntity cancelTicket(@PathVariable String ticketId){
        boolean canceled = bookingService.cancelTicket(ticketId);
        if(canceled){
            return new ResponseEntity("Ticket cancelled successfully!" , HttpStatus.OK);
        }else {
            return new ResponseEntity("Something went wrong! Please ensure you have given correct ticket Id", HttpStatus.BAD_REQUEST);
        }
    }
}
