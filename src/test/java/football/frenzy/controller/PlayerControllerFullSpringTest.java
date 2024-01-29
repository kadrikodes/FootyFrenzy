package football.frenzy.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import football.frenzy.TestUtilities;
import football.frenzy.controller.requestmodel.RequestHandler;
import football.frenzy.entity.ClubData;
import football.frenzy.entity.DraftData;
import football.frenzy.entity.PlayerData;
import football.frenzy.service.PlayerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PlayerController.class)
@AutoConfigureMockMvc
public class PlayerControllerFullSpringTest {

    @MockBean
    PlayerService mockPlayerService;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;
    TestUtilities testUtilities = new TestUtilities();

    @Test
    void testGetAllPlayers() throws Exception {
        List<PlayerData> playerData = testUtilities.createPlayerData();

        when(mockPlayerService.getAllPlayers()).thenReturn(playerData);

        ResultActions resultActions = this.mockMvc.perform(
                MockMvcRequestBuilders.get("/players"));
        resultActions.andExpect(status().isOk());

        verify(mockPlayerService, times(1)).getAllPlayers();
    }

    @Test
    void testGetPlayersByClubId() throws Exception {
        Long clubId = 1L;

        ClubData clubData = new ClubData("Manchester City", new ArrayList<>());
        PlayerData cityPlayer = new PlayerData("Manchester City", "John Stones", "Defender", clubData);
        clubData.getPlayers().add(cityPlayer);

        when(mockPlayerService.getPlayersByClubId(clubId)).thenReturn(clubData.getPlayers());

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/players/" + clubId + "/players");
        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andReturn();

        verify(mockPlayerService, times(1)).getPlayersByClubId(clubId);
    }

    @Test
    void testGetPlayersByClub() throws Exception {
        String clubName = "Manchester City";

        ClubData clubData = new ClubData("Manchester City", new ArrayList<>());
        PlayerData cityPlayer = new PlayerData("Manchester City", "John Stones", "Defender", clubData);
        clubData.getPlayers().add(cityPlayer);

        when(mockPlayerService.getPlayersByClub(clubName)).thenReturn(clubData.getPlayers());

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/players/byClub/" + clubName);
        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andReturn();

        verify(mockPlayerService, times(1)).getPlayersByClub(clubName);
    }

    @Test
    void testGetPlayersByClubEmptyList() throws Exception {
        String clubName = "Manchester City";

        ClubData clubData = new ClubData("Manchester City", new ArrayList<>());

        when(mockPlayerService.getPlayersByClub(clubName)).thenReturn(clubData.getPlayers());

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/players/byClub/" + clubName);
        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isNotFound())
                .andReturn();

        verify(mockPlayerService, times(1)).getPlayersByClub(clubName);
    }

    @Test
    void testGetPlayersByPosition() throws Exception {
        String position = "Defender";

        ClubData clubData = new ClubData("Manchester City", new ArrayList<>());
        PlayerData cityPlayer = new PlayerData("Manchester City", "John Stones", "Defender", clubData);
        clubData.getPlayers().add(cityPlayer);

        when(mockPlayerService.getPlayersByPosition(position)).thenReturn(clubData.getPlayers());

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/players/byPosition/" + position);
        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andReturn();

        verify(mockPlayerService, times(1)).getPlayersByPosition(position);
    }

    @Test
    void testGetPlayersByInvalidPosition() throws Exception {
        String position = "Defender";

        ClubData clubData = new ClubData("Manchester City", new ArrayList<>());
        PlayerData cityPlayer = new PlayerData("Manchester City", "Erling Haaland", "Attacker", clubData);
        clubData.getPlayers().add(cityPlayer);

        when(mockPlayerService.getPlayersByPosition(position)).thenReturn(new ArrayList<>());

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/players/byPosition/" + position);
        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isNotFound())
                .andReturn();

        verify(mockPlayerService, times(1)).getPlayersByPosition(position);
    }

    @Test
    void testGetPlayerById() throws Exception {
        Long playerId = 1L;

        ClubData clubData = new ClubData("Manchester City", new ArrayList<>());
        PlayerData cityPlayer = new PlayerData("Manchester City", "John Stones", "Defender", clubData);
        clubData.getPlayers().add(cityPlayer);

        when(mockPlayerService.getPlayerById(playerId)).thenReturn(cityPlayer);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/players/" + playerId);
        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andReturn();

        verify(mockPlayerService, times(1)).getPlayerById(playerId);
    }

    @Test
    void testGetPlayerByInvalidId() throws Exception {
        Long playerId = 2L;

        ClubData clubData = new ClubData("Manchester City", new ArrayList<>());
        PlayerData cityPlayer = new PlayerData("Manchester City", "John Stones", "Defender", clubData);
        clubData.getPlayers().add(cityPlayer);

        when(mockPlayerService.getPlayerById(playerId)).thenReturn(null);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/players/" + playerId);
        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isNotFound())
                .andReturn();

        verify(mockPlayerService, times(1)).getPlayerById(playerId);
    }

    @Test
    void testGetPlayersByPlayerName() throws Exception {
        String playerName = "John Stones";

        ClubData clubData = new ClubData("Manchester City", new ArrayList<>());
        PlayerData cityPlayer = new PlayerData("Manchester City", "John Stones", "Defender", clubData);
        clubData.getPlayers().add(cityPlayer);

        when(mockPlayerService.getPlayerDataByPlayerName(playerName)).thenReturn(clubData.getPlayers());

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/players/byPlayerName/" + playerName);
        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andReturn();

        verify(mockPlayerService, times(1)).getPlayerDataByPlayerName(playerName);
    }

    @Test
    void testGetPlayersByInvalidPlayerName() throws Exception {
        String playerName = "Dennis Fadipe";

        ClubData clubData = new ClubData("Manchester City", new ArrayList<>());
        PlayerData cityPlayer = new PlayerData("Manchester City", "John Stones", "Defender", clubData);
        clubData.getPlayers().add(cityPlayer);

        when(mockPlayerService.getPlayerDataByPlayerName(playerName)).thenReturn(new ArrayList<>());

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/players/byPlayerName/" + playerName);
        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isNotFound())
                .andReturn();

        verify(mockPlayerService, times(1)).getPlayerDataByPlayerName(playerName);
    }

    @Test
    void testAddPlayer() throws Exception {
        ClubData clubData = new ClubData("Manchester City", new ArrayList<>());
        PlayerData cityPlayer = new PlayerData("Manchester City", "John Stones", "Defender", clubData);
        clubData.getPlayers().add(cityPlayer);

        String json = mapper.writeValueAsString(cityPlayer);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/players");
        MvcResult result = mockMvc.perform((requestBuilder)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andReturn();

        verify(mockPlayerService, times(1)).addPlayer(any(PlayerData.class));
    }

    @Test
    void testValidatePlayerSelection() throws Exception {
        // Create a sample request
        RequestHandler selectionRequest = new RequestHandler();
        selectionRequest.setDraftId(new DraftData());
        selectionRequest.setSelectedPlayer("ValidPlayer");

        // Mock the behavior of your service
        when(mockPlayerService.isValidSelectedPlayerSelection(any(DraftData.class), eq("ValidPlayer")))
                .thenReturn(true);

        // Convert request object to JSON
        String jsonRequest = mapper.writeValueAsString(selectionRequest);

        // Build the request
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/players/validate-selection")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest);

        // Perform the request and assert the expected result
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().string("Selected player is valid for the current draft round and club."));

        // Verify that the service method was called
        verify(mockPlayerService, times(1)).isValidSelectedPlayerSelection(any(DraftData.class), eq("ValidPlayer"));
    }

    @Test
    void testValidateInvalidPlayerSelection() throws Exception {
        // Creating a sample request
        RequestHandler selectionRequest = new RequestHandler();
        selectionRequest.setDraftId(new DraftData());
        selectionRequest.setSelectedPlayer("InvalidPlayer");

        // Mocking the behavior of player service
        when(mockPlayerService.isValidSelectedPlayerSelection(any(DraftData.class), eq("InvalidPlayer")))
                .thenReturn(false);

        // Converting request object to JSON
        String jsonRequest = mapper.writeValueAsString(selectionRequest);

        // Building the request
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/players/validate-selection")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest);

        // Performing the request and asserting the expected result
        mockMvc.perform(requestBuilder)
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Invalid player selection for the current draft round and club."));

        // Verifying that the service method was called
        verify(mockPlayerService, times(1)).isValidSelectedPlayerSelection(any(DraftData.class), eq("InvalidPlayer"));
    }

}
