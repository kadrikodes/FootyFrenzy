package football.frenzy.service;

import football.frenzy.dataaccess.UserRepository;
import football.frenzy.entity.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserData createUser(UserData userData) {
        // Check if user with the same username already exists
        if (userRepository.findByUsername(userData.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists. Please choose a different one.");
        }

        // Additional validation or business logic can be added

        // Save the new user
        return userRepository.save(userData);
    }

    public boolean validateUserKey(String userKey) {
        return false;
    }
}

