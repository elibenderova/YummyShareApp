package bg.softuni.yummyshare.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class StatisticsControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
    public void testStatsAccess() throws Exception {
        mockMvc.perform(get("/statistics")).
                andExpect(status().isOk()).
                andExpect(view().name("statistics")).
                andExpect(model().attributeExists("logs"));
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void testStatsAccessDeniedForNormalUser() throws Exception {
        mockMvc.perform(get("/statistics")).
                andExpect(status().isForbidden());
    }
}
