package football.frenzy.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import football.frenzy.entity.ClubData;
import football.frenzy.entity.DraftData;
import football.frenzy.entity.UserData;
import football.frenzy.entity.UserDraftSelection;
import football.frenzy.entity.model.DraftRound;
import football.frenzy.entity.model.DraftStatus;
import football.frenzy.service.UserDraftSelectionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserDraftSelectionController.class)
@AutoConfigureMockMvc
public class UserDraftSelectionControllerFullSpringTest {

    @MockBean
    UserDraftSelectionService mockUserDraftSelectionService;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;

    @Test
    void testGetUserSelections() throws Exception {
        Long userId = 1L;
        Long draftId = 1L;

        UserData userData = new  UserData("kungfukadri", "ki123", "kadri@gmail.com", "Kadri", "Idris");

        int roundNumber = 1;
        ClubData selectedClub = new ClubData("Manchester City", new ArrayList<>());
        ClubData club = new ClubData("Liverpool", new ArrayList<>());
        List<ClubData> availableClubs = new ArrayList<>();
        availableClubs.add(club);
        DraftData draftData = new DraftData(DraftRound.ROUND_ONE, selectedClub, DraftStatus.IN_PROGRESS, availableClubs);

        UserDraftSelection selection = new UserDraftSelection(userData, draftData, roundNumber, selectedClub);
        selection.setUserIdFromLong(userId);
        selection.setDraftIdFromLong(draftId);


        userData.setUserId(userId);



        draftData.setDraftId(draftId);


        when(mockUserDraftSelectionService.getUserSelections(selection.getUserId(), selection.getDraftId())).thenReturn(Collections.singletonList(selection));

        ResultActions resultActions = this.mockMvc.perform(
                MockMvcRequestBuilders.get("/user/" + selection.getUserIdAsLong() + "/draft/" + selection.getDraftIdAsLong() + "/selection"));
        resultActions.andExpect(status().isInternalServerError());

        System.out.println(resultActions.andReturn().getResponse().getContentAsString());


        verify(mockUserDraftSelectionService, times(1)).getUserSelections(selection.getUserId(), selection.getDraftId());
    }

//    @Test
//    void testGetUserSelections() throws Exception {
//        Long userId = 1L;
//        Long draftId = 1L;
//        UserData userData = new  UserData("kungfukadri", "ki123", "kadri@gmail.com", "Kadri", "Idris");
//        userData.setUserId(userId);
//        int roundNumber = 1;
//        ClubData selectedClub = new ClubData("Manchester City", new ArrayList<>());
//        ClubData club = new ClubData("Liverpool", new ArrayList<>());
//        List<ClubData> availableClubs = new ArrayList<>();
//        availableClubs.add(club);
//        DraftData draftData = new DraftData(DraftRound.ROUND_ONE, selectedClub, DraftStatus.IN_PROGRESS, availableClubs);
//        draftData.setDraftId(draftId);
//
//
//        when(mockUserDraftSelectionService.getUserSelections(any(UserData.class), any(DraftData.class)))
//                .thenThrow(new RuntimeException("Simulating an exception in the service"));
//
//        ResultActions resultActions = this.mockMvc.perform(
//                MockMvcRequestBuilders.get("/user/" + userData.getUserId() + "/draft/" + draftData.getDraftId() + "/selection"));
//
//        resultActions.andExpect(status().isInternalServerError());
//
//        System.out.println(resultActions.andReturn().getResponse().getContentAsString());
//
//
//        verify(mockUserDraftSelectionService, times(1)).getUserSelections(userData, draftData);
//    }

    @Test
    void testAddUserSelections() throws Exception {
        Long userId = 1L;
        Long draftId = 1L;
        UserData userData = new  UserData("kungfukadri", "ki123", "kadri@gmail.com", "Kadri", "Idris");
        userData.setUserId(userId);
        int roundNumber = 1;
        ClubData selectedClub = new ClubData("Manchester City", new ArrayList<>());
        ClubData club = new ClubData("Liverpool", new ArrayList<>());
        List<ClubData> availableClubs = new ArrayList<>();
        availableClubs.add(club);
        DraftData draftData = new DraftData(DraftRound.ROUND_ONE, selectedClub, DraftStatus.IN_PROGRESS, availableClubs);
        draftData.setDraftId(draftId);

        UserDraftSelection userDraftSelection = new UserDraftSelection(userData, draftData, roundNumber, selectedClub);

        String json = mapper.writeValueAsString(userDraftSelection);


//        when(mockUserDraftSelectionService.getUserSelections(userData, draftData)).thenReturn(Collections.singletonList(new UserDraftSelection(userData, draftData, roundNumber, selectedClub)));

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/user/" + userData.getUserId() + "/draft/" + draftData.getDraftId() + "/selection/" + "/add");
        MvcResult result = mockMvc.perform((requestBuilder)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isInternalServerError())
                .andReturn();

//        ResultActions resultActions = this.mockMvc.perform(
//                MockMvcRequestBuilders.post("/user/" + userData.getUserId() + "/draft/" + draftData.getDraftId() + "/selection"));
//        resultActions.andExpect(status().isInternalServerError());
//
//        System.out.println(resultActions.andReturn().getResponse().getContentAsString());


        verify(mockUserDraftSelectionService, times(1)).addUserSelection(userDraftSelection);
    }

}
