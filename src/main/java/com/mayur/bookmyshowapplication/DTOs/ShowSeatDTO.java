package com.mayur.bookmyshowapplication.DTOs;

import lombok.Data;

@Data
public class ShowSeatDTO {

    private Integer showId;

    private Double priceOfRegularSeat;

    private Double priceOfPremiumSeat;

    private Double priceOfVIPSeat;

    private Double priceOfClassicSeat;

}
