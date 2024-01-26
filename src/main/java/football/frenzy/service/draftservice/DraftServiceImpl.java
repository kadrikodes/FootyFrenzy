package football.frenzy.service.draftservice;

import football.frenzy.dataaccess.DraftRepository;
import football.frenzy.entity.ClubData;
import football.frenzy.entity.DraftData;
import football.frenzy.entity.PlayerData;
import football.frenzy.entity.UserDraftSelection;
import football.frenzy.entity.model.DraftRound;
import football.frenzy.entity.model.DraftStatus;
import football.frenzy.service.ClubService;
import football.frenzy.service.PlayerService;
import football.frenzy.service.UserService;
import org.glassfish.jaxb.core.v2.TODO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DraftServiceImpl implements DraftService {

    private final DraftRepository draftRepository;
    private final ClubService clubService;
    private final PlayerService playerService;
    private final UserService userService;

    @Autowired
    public DraftServiceImpl(DraftRepository draftRepository, ClubService clubService, PlayerService playerService, UserService userService) {
        this.draftRepository = draftRepository;
        this.clubService = clubService;
        this.playerService = playerService;
        this.userService = userService;
    }

    @Override
    public DraftData initiateDraft() {
        DraftData draftData = new DraftData(); // Create a new draft
        draftData.setRoundNumber(DraftRound.ROUND_ONE);
        draftData.setStatus(DraftStatus.IN_PROGRESS);

        // Randomly select an available club
        ClubData randomClub = clubService.getRandomAvailableClub(draftData);
        draftData.setCurrentClub(randomClub);

        // Retrieve the list of available players from the selected club
        List<PlayerData> availablePlayers = playerService.getPlayersByClub(String.valueOf(randomClub));
        draftData.setAvailablePlayers(availablePlayers);

        // Save the draft in the repository
        draftRepository.save(draftData);

        return draftData;
    }

    @Override
    public ResponseEntity<String> selectPlayer(Long draftId, String userKey, String selectedPlayer) {
        //logic to validate and record user selections
        // Retrieving the draft from the repository, update the state, and save it back
        DraftData draftData = draftRepository.findById(draftId).orElse(null);
        if (draftData == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Draft not found");
        }
        // Handling validation, such as checking if the player is available, user hasn't made this selection, etc.
        boolean isValidUser = userService.validateUserKey(userKey);
        if (!isValidUser) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid user key");
        }
        boolean isValidPlayer = isValidPlayerSelection(draftData, selectedPlayer);
        if (!isValidPlayer) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid player selection");
        }
        // Updating the draft state
        updateDraftState(draftData, userKey, selectedPlayer);
        // Step 5: Save the Updated Draft
        draftRepository.save(draftData);

        // Step 6: Return Response
        return ResponseEntity.ok("Player selected successfully");
    }

    @Override
    public DraftData getDraftStatus(Long draftId) {
        //logic to retrieve and return the current status of the draft
        return null;
    }

    @Override
    public boolean isValidPlayerSelection(DraftData draftData, String selectedPlayer) {

        List<String> availablePlayers = draftData.getCurrentClub().getPlayers().stream()
                .map(PlayerData::getPlayerName)
                .collect(Collectors.toList());

        return availablePlayers.contains(selectedPlayer);
    }

    public void updateDraftState(DraftData draftData, String userKey, String selectedPlayer) {
        // Validate if the draftData is not null
        if (draftData == null) {
            throw new IllegalArgumentException("DraftData cannot be null");
        }

        // Validate if the userKey and selectedPlayer are not null or empty
        if (userKey == null || userKey.isEmpty() || selectedPlayer == null || selectedPlayer.isEmpty()) {
            throw new IllegalArgumentException("Invalid userKey or selectedPlayer");
        }

        // Validate if the draft is in a state where player selection is allowed
        if (draftData.getStatus() != DraftStatus.IN_PROGRESS) {
            throw new IllegalStateException("Draft is not in progress");
        }

        // Get the current round number and the club for the current round
        DraftRound currentRound = draftData.getRoundNumber();
        ClubData currentClub = draftData.getCurrentClub();

        // Validate if the userKey is assigned to the current round and club
        if (!draftData.getSelectedPlayersByUser().containsKey(userKey)) {
            throw new IllegalStateException("User has not been assigned to the current round");
        }

        // Validate if the selectedPlayer is available in the current club
        if (!currentClub.getPlayers().stream().anyMatch(player -> player.getPlayerName().equals(selectedPlayer))) {
            throw new IllegalArgumentException("Selected player is not available in the current club");
        }

        // Update the list of selected players for the current user, round, and club
        draftData.getSelectedPlayersByUser().get(userKey).add(selectedPlayer);
    }

    @Override
    public List<UserDraftSelection> getUserDraftSelections(Long userId, Long draftId) {
        return null;
    }
}

