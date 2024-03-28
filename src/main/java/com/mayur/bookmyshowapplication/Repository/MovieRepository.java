package com.mayur.bookmyshowapplication.Repository;

import com.mayur.bookmyshowapplication.Models.Movie;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
    Movie findByTitle(String movieName);

}
