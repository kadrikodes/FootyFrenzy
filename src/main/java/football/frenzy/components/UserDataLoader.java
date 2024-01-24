package football.frenzy.components;

import football.frenzy.entity.UserData;
import football.frenzy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class UserDataLoader implements CommandLineRunner {
    private final UserService userService;

    @Autowired
    public UserDataLoader(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) {
        // Load sample data during application startup
        userService.addUser(new UserData("sampleUser1", "password1"));
        userService.addUser(new UserData("sampleUser2", "password2"));
        // Add more sample users as needed
    }
}
