package com.texnoera.controller;

import com.texnoera.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static com.texnoera.constants.TestConstants.NAME;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void getUsers() throws Exception {


//        mockMvc.perform(get("/users")
//                .queryParam("name", "John")
//                .queryParam("ageFrom", "18")
//                .queryParam("ageTo", "30"))
//                .andExpect(status().isOk());
    }

}
