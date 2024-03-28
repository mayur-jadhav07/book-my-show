package com.mayur.bookmyshowapplication.Repository;

import com.mayur.bookmyshowapplication.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findUserByMobNo(String mobileNo);
}
