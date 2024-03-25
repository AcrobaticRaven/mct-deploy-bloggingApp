

package org.example.BloggingPlatformApi.ControllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.BloggingPlatformApi.Controller.UserController;
import org.example.BloggingPlatformApi.Model.AuthenticationToken;
import org.example.BloggingPlatformApi.Model.DTO.SignInInput;
import org.example.BloggingPlatformApi.Model.DTO.SignUpOutput;
import org.example.BloggingPlatformApi.Model.User;
import org.example.BloggingPlatformApi.Service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void test_signUpUser() throws Exception {
        User user = new User();
        user.setBlogUserId(1);
        user.setUserName("balaji");
        user.setUserMail("abc@gmail.com");
        user.setUserPassword("abc");
        user.setUserNumber(1);
        String signUpStatusMessage = "User registered successfully";
        boolean signUpStatus = true;
        when(userService.signUpUser(user)).thenReturn(new SignUpOutput(signUpStatusMessage,signUpStatus));
        mockMvc.perform(MockMvcRequestBuilders.post("/User/SignUp").contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(user)))
                .andExpect(MockMvcResultMatchers.status().isOk());


    }

    @Test
    public void test_signOutUser() throws Exception {
        User user = new User();
        user.setBlogUserId(1);
        user.setUserName("balaji");
        user.setUserMail("abc@gmail.com");
        user.setUserPassword("abc");
        user.setUserNumber(1);
        when(userService.signOutUser("abc@gmail.com")).thenReturn("User signed out successfully");
        mockMvc.perform(MockMvcRequestBuilders.delete("/User/SignOut/{mail}","abc@gmail.com").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void test_signInUser() throws Exception {
        User user = new User();
        user.setBlogUserId(1);
        user.setUserName("balaji");
        user.setUserMail("abc@gmail.com");
        user.setUserPassword("abc");
        user.setUserNumber(1);
        AuthenticationToken authToken = new AuthenticationToken();
        authToken.setAuthUser(user);
        authToken.setTokenValue("abc");
        authToken.setAuthCreationStamp(LocalDateTime.now());
        authToken.setAuthId(1);
        SignInInput signInInput = new SignInInput();
        signInInput.setMail("abc@gmail.com");
        signInInput.setPassword("abc");
        when(userService.signInUser(signInInput)).thenReturn(authToken.getTokenValue());
        mockMvc.perform(MockMvcRequestBuilders.post("/User/SignIn").contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(signInInput)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}


