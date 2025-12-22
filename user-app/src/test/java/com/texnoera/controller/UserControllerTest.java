package com.texnoera.controller;

import com.texnoera.dto.UserDto;
import com.texnoera.model.UserFilter;
import com.texnoera.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static com.texnoera.constants.TestConstants.NAME;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc(addFilters = false)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;

    @Test
    void getUsers() throws Exception {
        UserFilter given = new UserFilter();
        given.setName(NAME);

        UserDto actualUserDto = new UserDto();

        Mockito.when(userService.getUsers(given))
                .thenReturn(List.of(actualUserDto));

        mockMvc.perform(get("/users")
                        .queryParam("name", NAME))
                .andExpect(status().isOk());
    }

}
