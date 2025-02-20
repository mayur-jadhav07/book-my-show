package com.mayur.bookmyshowapplication.Service;

import com.mayur.bookmyshowapplication.DTOs.UserDTO;
import com.mayur.bookmyshowapplication.Enums.Role;
import com.mayur.bookmyshowapplication.Exceptions.UserNotFoundException;
import com.mayur.bookmyshowapplication.Models.User;
import com.mayur.bookmyshowapplication.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String createUser(UserDTO userDTO) {
        try {
            User user = User.builder()
                    .username(userDTO.getUsername())
                    .password(passwordEncoder.encode(userDTO.getPassword()))
                    .role(userDTO.getRole().equalsIgnoreCase("ADMIN") ? Role.ADMIN : Role.USER)
                    .emailId(userDTO.getEmailId())
                    .mobNo(userDTO.getMobileNo())
                    .build();

            user = userRepository.save(user);

            return "User Created with user id " + user.getUserId();
        } catch (Exception e) {
            // Optionally log the exception for debugging
            System.out.println("Error creating user"+ e);
            return "Something went wrong";
        }
    }


    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(this::mapUserToDTO).collect(Collectors.toList());
    }

    private UserDTO mapUserToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(user.getUsername());
//        userDTO.setPassword(user.getPassword());
        userDTO.setEmailId(user.getEmailId());
        userDTO.setMobileNo(user.getMobNo());
        return userDTO;
    }

    public UserDTO getUserById(Integer id) throws UserNotFoundException {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            return mapUserToDTO(optionalUser.get());
        }
        throw new UserNotFoundException("Enter valid userId");
    }

    public String updateUser(Integer id, UserDTO userDTO) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setUsername(user.getUsername());
            user.setPassword(user.getPassword());
            user.setEmailId(userDTO.getEmailId());
            user.setMobNo(userDTO.getMobileNo());
            userRepository.save(user);
            return "User updated successfully";
        }
        return "User not found";
    }

    public String deleteUser(Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            userRepository.deleteById(id);
            return "User deleted successfully";
        }
        return "User not found";
    }
}
