package com.mayur.bookmyshowapplication.Repository;

import com.mayur.bookmyshowapplication.Models.Show;
import com.mayur.bookmyshowapplication.Models.ShowSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShowSeatRepository extends JpaRepository<ShowSeat, Integer> {
    @Query(nativeQuery = true,value = "select * from show_seats where show_show_id = :showId")
    public List<ShowSeat> findShowSeats(Integer showId);

    public List<ShowSeat> findAllByShow(Show show); //Inbuilt method invoking
}
