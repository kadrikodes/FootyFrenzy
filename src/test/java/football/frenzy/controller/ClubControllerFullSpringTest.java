package football.frenzy.controller;

import football.frenzy.TestUtilities;
import football.frenzy.entity.ClubData;
import football.frenzy.entity.PlayerData;
import football.frenzy.service.ClubService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClubController.class)
@AutoConfigureMockMvc
public class ClubControllerFullSpringTest {

    @MockBean
    ClubService mockClubService;
    @Autowired
    MockMvc mockMvc;
    TestUtilities testUtilities = new TestUtilities();

    @Test
    void testGetAllClubs() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/clubs");

        mockMvc.perform(requestBuilder).andExpect(status().isOk());

        verify(mockClubService, times(1)).getAllClubs();
    }

    @Test
    void testGetAllClubsFromHttpRequest() throws Exception {
        List<ClubData> clubData = testUtilities.createClubData();

        when(mockClubService.getAllClubs()).thenReturn(clubData);

        ResultActions resultActions = this.mockMvc.perform(
                MockMvcRequestBuilders.get("/clubs"));
        resultActions.andExpect(status().isOk());

        verify(mockClubService, times(1)).getAllClubs();
    }

    @Test
    void testGetClubDataById() throws Exception {
        Long clubId = 1L;

        ClubData clubData = new ClubData("Manchester United", new ArrayList<>());
        PlayerData unitedPlayer = new PlayerData("Manchester United", "Bruno Fernandes", "Midfielder", clubData);
        clubData.getPlayers().add(unitedPlayer);

        when(mockClubService.getClubById(clubId)).thenReturn(clubData);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/clubs/" + clubId.toString());
        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andReturn();

        verify(mockClubService, times(1)).getClubById(clubId);
    }

    @Test
    void testGetClubDataByIdEmptyList() throws Exception {
        Long clubId = 1L;

        when(mockClubService.getClubById(clubId)).thenReturn(null);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/clubs/" + clubId.toString());
        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isNotFound())
                .andReturn();

        verify(mockClubService, times(1)).getClubById(clubId);
    }

    @Test
    void testGetPlayersByClubId() throws Exception {
        Long clubId = 1L;

        ClubData clubData = new ClubData("Manchester City", new ArrayList<>());
        PlayerData cityPlayer = new PlayerData("Manchester City", "John Stones", "Defender", clubData);
        clubData.getPlayers().add(cityPlayer);

        when(mockClubService.getPlayersByClubId(clubId)).thenReturn(clubData.getPlayers());

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/clubs/" + clubId + "/players");
        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andReturn();

        verify(mockClubService, times(1)).getPlayersByClubId(clubId);
    }

    @Test
    void testGetPlayersByClubIdEmptyList() throws Exception {
        Long clubId = 1L;

        ClubData clubData = new ClubData("Manchester City", new ArrayList<>());

        when(mockClubService.getPlayersByClubId(clubId)).thenReturn(clubData.getPlayers());

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/clubs/" + clubId + "/players");
        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isNotFound())
                .andReturn();

        verify(mockClubService, times(1)).getPlayersByClubId(clubId);
    }
}
