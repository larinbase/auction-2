package ru.itis.auctionmarketplace;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.itis.auctionmarketplace.dto.request.RegistrationForm;
import ru.itis.auctionmarketplace.exception.AccountAlreadyExistsException;
import ru.itis.auctionmarketplace.service.AuthService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthService authService;

    @Test
    public void testSignInView() throws Exception {
        mockMvc.perform(get("/auth/sign-in"))
                .andExpect(status().isOk())
                .andExpect(view().name("sign_in_form"))
                .andExpect(model().attributeDoesNotExist("error"));
    }

    @Test
    public void testSignInViewWithError() throws Exception {
        mockMvc.perform(get("/auth/sign-in").param("error", "true"))
                .andExpect(status().isOk())
                .andExpect(view().name("sign_in_form"))
                .andExpect(model().attributeExists("error"))
                .andExpect(model().attribute("error", "true"));
    }

    @Test
    public void testSignUpView() throws Exception {
        mockMvc.perform(get("/auth/sign-up"))
                .andExpect(status().isOk())
                .andExpect(view().name("sign_up_form"));
    }

    @Test
    public void testSignUpSuccess() throws Exception {
        RegistrationForm form = new RegistrationForm("test1", "1234567");

        mockMvc.perform(post("/auth/sign-up")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                        .param("username", form.username())
                        .param("password", form.password()))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());

        Mockito.verify(authService, Mockito.times(1)).register(form);
    }

    @Test
    public void testSignUpAccountAlreadyExists() throws Exception {
        RegistrationForm form = new RegistrationForm("test", "123456");

        Mockito.doThrow(new AccountAlreadyExistsException("Account already exists"))
                .when(authService).register(form);

        mockMvc.perform(post("/auth/sign-up")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                        .param("username", form.username())
                        .param("password", form.password()))
                .andExpect(status().isConflict());

        Mockito.verify(authService, Mockito.times(1)).register(form);
    }
}
