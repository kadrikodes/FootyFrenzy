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

    public List<UserData> getAllUsers() {
        return userRepository.findAll();
    }

    public UserData addUser(UserData user) {
        return userRepository.save(user);
    }

    public Optional<UserData> getUserById(Long userId) {
        return userRepository.findById(userId);
    }

    public Optional<UserData> getUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    public static ArrayList<UserData> createUserFromCSV() {
        FileInputStream fis;
        Scanner fileScanner;
        ArrayList<UserData> userList = new ArrayList<>();
        try {
            fis = new FileInputStream("src/main/resources/userFile.csv");
            fileScanner = new Scanner(fis);
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] userRow = line.split(",");
                if (userRow.length == 2) {
                    String userName = userRow[0].trim();
                    String userPassword = userRow[1].trim();
                    userList.add(new UserData(userName, userPassword));
                }
            }
            fis.close();
        } catch (FileNotFoundException e) {
//            System.out.println("User not found");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return userList;
    }
}
