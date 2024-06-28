package ru.itis.auctionmarketplace;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.itis.auctionmarketplace.service.AccountService;
import ru.itis.auctionmarketplace.service.AuctionService;

import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AdminControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AccountService accountService;

    @Autowired
    private AuctionService auctionService;

    @Test
    @WithMockUser(authorities = "ROLE_ADMIN")
    public void testAdminPageAuthorized() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/accounts"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testAdminPageUnauthorized() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/accounts"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }

    @Test
    @WithMockUser(authorities = "ROLE_USER")
    public void testAdminPageAccessDenied() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/accounts"))
                .andExpect(MockMvcResultMatchers.status().is5xxServerError());
    }

}
