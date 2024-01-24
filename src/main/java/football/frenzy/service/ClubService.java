package football.frenzy.service;

import football.frenzy.dataaccess.ClubRepository;
import football.frenzy.entity.ClubData;
import football.frenzy.entity.PlayerData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClubService {
    @Autowired
    private ClubRepository clubRepository;

    public ClubData saveClub(ClubData club) {
        return clubRepository.save(club);
    }

    public List<ClubData> getAllClubs() {
        return clubRepository.findAll();
    }

    public void addClub(ClubData clubData) {
        clubRepository.save(clubData);
    }

    public List<ClubData> findAll() {
        return clubRepository.findAll();
    }

    public ClubData getClubDataById(long clubId) {
        Optional<ClubData> clubs = clubRepository.findById(clubId);
        return clubs.orElse(null);
    }

    public List<ClubData> getClubDataByClubName(String name) {
        return clubRepository.findClubDataByClubNames(name);
    }
}
