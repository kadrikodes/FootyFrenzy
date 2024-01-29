package football.frenzy.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import football.frenzy.entity.ClubData;
import football.frenzy.entity.DraftData;
import football.frenzy.entity.model.DraftRound;
import football.frenzy.entity.model.DraftStatus;
import football.frenzy.service.draftservice.DraftService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DraftController.class)
@AutoConfigureMockMvc
public class DraftControllerFullSpringTest {

    @MockBean
    DraftService mockDraftService;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;

    @Test
    void testStartDraft() throws Exception {
        ClubData clubData = new ClubData("Chelsea", null);

        List<ClubData> availableClubs = new ArrayList<>();
        availableClubs.add(new ClubData("Liverpool", null));
        availableClubs.add(new ClubData("Tottenham Hotspur", null));

        DraftData draftData = new DraftData(DraftRound.ROUND_ONE, clubData, DraftStatus.IN_PROGRESS, availableClubs);

        when(mockDraftService.initiateDraft()).thenReturn(draftData);

        String json = mapper.writeValueAsString(draftData);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/draft/start");
        MvcResult result = mockMvc.perform((requestBuilder)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andReturn();

        verify(mockDraftService, times(1)).initiateDraft();
    }

    @Test
    void testSelectPlayer() throws Exception {
        Long draftId = 1L;
        String userName = "Kungfukadri";
        String selectedPlayer = "James Maddison";

        when(mockDraftService.selectPlayer(draftId, userName, selectedPlayer)).thenReturn(ResponseEntity.ok("Player selected successfully"));

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/draft/select");
        MvcResult result = mockMvc.perform((requestBuilder)
                        .param("draftId", draftId.toString())
                        .param("userName", userName)
                        .param("selectedPlayer", selectedPlayer)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        verify(mockDraftService, times(1)).selectPlayer(draftId, userName, selectedPlayer);

        assertEquals("Player selected successfully", result.getResponse().getContentAsString());
    }

    @Test
    void testSelectInvalidPlayer() throws Exception {
        Long draftId = 1L;
        String userName = "Kungfukadri";
        String selectedPlayer = "Boluwaji Oladuja";

        when(mockDraftService.selectPlayer(draftId, userName, selectedPlayer))
                .thenThrow(new RuntimeException("Invalid player selection"));

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/draft/select");
        MvcResult result = mockMvc.perform((requestBuilder)
                        .param("draftId", draftId.toString())
                        .param("userName", userName)
                        .param("selectedPlayer", selectedPlayer)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();

        verify(mockDraftService, times(1)).selectPlayer(draftId, userName, selectedPlayer);

        assertEquals("Invalid player selection", result.getResponse().getContentAsString());
    }
}
