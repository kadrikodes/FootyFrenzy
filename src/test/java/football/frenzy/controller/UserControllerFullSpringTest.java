package football.frenzy.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import football.frenzy.entity.PlayerData;
import football.frenzy.entity.UserData;
import football.frenzy.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc
public class UserControllerFullSpringTest {

    @MockBean
    UserService mockUserService;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;

    @Test
    void testCreateUser() throws Exception {
        UserData userData = new UserData("kungfukadri", "ki123", "kadri@gmail.com", "Kadri", "Idris");

        String json = mapper.writeValueAsString(userData);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/user");
        MvcResult result = mockMvc.perform((requestBuilder)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andReturn();

        verify(mockUserService, times(1)).createUser(any(UserData.class));
    }
}
