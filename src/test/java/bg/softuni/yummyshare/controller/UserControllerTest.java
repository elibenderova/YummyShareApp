package bg.softuni.yummyshare.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {
    private Long userTestId;

    @Autowired
    private MockMvc mockMvc;


    @Test
    @WithMockUser(username = "test", authorities = {"USER"})
    void testLoginPageReturnValidStatusOK() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/login")).
                andExpect(status().isOk()).
                andExpect(view().name("login"));
    }

    @Test
    @WithMockUser(username = "test", authorities = {"USER"})
    void testRegisterPageReturnValidStatusOK() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/register")).
                andExpect(status().isOk()).
                andExpect(view().name("register"));
    }

    @Test
    void testLoginPageReturnRedirectStatus() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/users/login-error").
                param("username", "test").
                param("password", "1").
                with(csrf())).
                andExpect(status().is3xxRedirection());
    }

    @Test
    void testRegisterPageReturnRedirectStatus() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/users/register").
                param("username", "test").
                param("firstName", "Test").
                param("lastName", "Testov").
                param("email", "test@gmail.com").
                param("password", "123456").
                param("confirmPassword", "1234567").
                with(csrf())).
                andExpect(status().is3xxRedirection());
    }

    @Test
    public void testPostUserRegisterPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/users/register")
                .param("username", "user")
                .param("email", "user@gmail.com")
                .param("password", "123456")
                .param("confirmPassword", "123456")
                .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(mvcResult -> {
                    "/users/login".equals(mvcResult.getModelAndView().getViewName());
                });
    }
}
