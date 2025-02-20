package com.mayur.bookmyshowapplication.DTOs;

import lombok.Data;

@Data
public class UserDTO {
    private String username;

    private String password;

    private String role;

    private String emailId;

    private String MobileNo;
}
