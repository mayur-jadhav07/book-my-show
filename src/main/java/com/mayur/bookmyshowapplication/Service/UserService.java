package com.mayur.bookmyshowapplication.Service;

import com.mayur.bookmyshowapplication.DTOs.UserDTO;
import com.mayur.bookmyshowapplication.Exceptions.UserNotFoundException;
import com.mayur.bookmyshowapplication.Models.User;
import com.mayur.bookmyshowapplication.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public String createUser(UserDTO userDTO) {
        User user = User.builder()
                .name(userDTO.getName())
                .emailId(userDTO.getEmailId())
                .mobNo(userDTO.getMobileNo())
                .build();

        user = userRepository.save(user);

        return "User Created with user id "+ user.getUserId();
    }

    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(this::mapUserToDTO).collect(Collectors.toList());
    }

    private UserDTO mapUserToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setName(user.getName());
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
            user.setName(userDTO.getName());
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
