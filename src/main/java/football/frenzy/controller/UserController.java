package football.frenzy.controller;

import football.frenzy.entity.UserData;
import football.frenzy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String getAllUsers(Model model) {
        List<UserData> allUsers = userService.getAllUsers();
        model.addAttribute("users", allUsers);
        return "userList"; // Thymeleaf template name
    }

    @PostMapping("/users/new")
    public String addUser(UserData user) {
        userService.addUser(user);
        return "redirect:/users"; // Redirect to the list of users after adding a new one
    }

    // Additional methods for updating, deleting, or other user-related operations
}
