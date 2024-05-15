package football.frenzy.service;

import football.frenzy.dataaccess.ClubRepository;
import football.frenzy.entity.ClubData;
import football.frenzy.entity.DraftData;
import football.frenzy.entity.PlayerData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Service
public class ClubService {

    private final ClubRepository clubRepository;

    @Autowired
    public ClubService(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    public List<ClubData> getAllClubs() {
        return clubRepository.findAll();
    }

    public ClubData getClubById(Long clubId) {
        return clubRepository.findById(clubId).orElse(null);
    }

//    public List<PlayerData> getPlayersByClubId(Long clubId) {
//        ClubData club = clubRepository.findById(clubId).orElse(null);
//        return club != null ? club.getPlayers() : Collections.emptyList();
//    }
    public List<PlayerData> getPlayersByClubId(Long clubId) {
        ClubData club = clubRepository.findById(clubId).orElse(null);
        return club != null ? new ArrayList<>(club.getPlayers()) : Collections.emptyList();
    }


    public ClubData getRandomAvailableClub(DraftData draftData) {
        List<ClubData> availableClubs = getAllAvailableClubs(draftData);
        if (availableClubs.isEmpty()) {
            return null; // No available clubs
        }

        Random random = new Random();
        int randomIndex = random.nextInt(availableClubs.size());
        return availableClubs.get(randomIndex);
    }

    private List<ClubData> getAllAvailableClubs(DraftData draftData) {
        List<ClubData> allClubs = getAllClubs();

        // Check if there is a current club in the draft
        if (draftData.getCurrentClub() != null) {
            // Remove the current club from the available clubs
            allClubs.remove(draftData.getCurrentClub());
        }

        // Remove clubs that have already been selected in the draft
        List<ClubData> selectedClubs = draftData.getAvailableClubs();
        allClubs.removeAll(selectedClubs);

        return allClubs;
    }

//    public ClubData createClub(ClubData club) {
//        // Check if user with the same username already exists
//        if (clubRepository.findByClubName(club.getClubName()).isPresent()) {
//            throw new RuntimeException("ClubName already exists. Please choose a different one.");
//        }
//
//        // TODO Add validation or business logic
//        // Save the new user
//        return clubRepository.save(club);
//    }

    public ClubData saveClub(ClubData club) {
        return clubRepository.save(club);
    }
}

