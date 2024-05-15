package football.frenzy.service;

import football.frenzy.dataaccess.UserRepository;
import football.frenzy.entity.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

        // TODO Add validation or business logic
        // Save the new user
        return userRepository.save(userData);
    }

    public boolean validateUserName(String userName) {
        Optional<UserData> userOptional = userRepository.findByUsername(userName);
        return userOptional.isPresent();
    }

    public UserData getUserById(Long userId) {
        Optional<UserData> userOptional = userRepository.findById(userId);
        return userOptional.orElse(null);
    }
}

