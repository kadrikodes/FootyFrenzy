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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserDraftSelectionControllerFullSpringTest.class)
@AutoConfigureMockMvc
public class UserDraftSelectionControllerFullSpringTest {

    @MockBean
    UserDraftSelectionService mockUserDraftSelectionService;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;

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
//        when(mockUserDraftSelectionService.getUserSelections(any(UserData.class), any(DraftData.class))).thenReturn(Collections.singletonList(new UserDraftSelection(userData, draftData, roundNumber, selectedClub)));
//
//        ResultActions resultActions = this.mockMvc.perform(
//                MockMvcRequestBuilders.get("/user/" + userData + "/draft/" + draftData + "/selection"));
//        resultActions.andExpect(status().isOk());
//
//        System.out.println(resultActions.andReturn().getResponse().getContentAsString());
//
//
//        verify(mockUserDraftSelectionService, times(1)).getUserSelections(userData, draftData);
//    }

}
