package football.frenzy.dto;

public class ClubDataDTO {
    private String clubName;

    // Getters and setters

    public ClubDataDTO() {
    }

    public ClubDataDTO(String clubName) {
        this.clubName = clubName;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }
}
