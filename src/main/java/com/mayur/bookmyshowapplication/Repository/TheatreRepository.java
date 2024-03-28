package com.mayur.bookmyshowapplication.Repository;

import com.mayur.bookmyshowapplication.Models.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TheatreRepository extends JpaRepository<Theatre, Integer> {
    Theatre findByName(String theatreName);

    List<Theatre> findAllByCity(String city);
}
