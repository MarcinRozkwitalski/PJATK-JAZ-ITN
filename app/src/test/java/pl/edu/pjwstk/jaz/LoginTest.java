package pl.edu.pjwstk.jaz;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;


@RunWith(SpringRunner.class)
@IntegrationTest
public class LoginTest {


    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void givenRegisteredAdmin_WhenAdminLogins_ShouldLogin() throws Exception {

        mockMvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\": \"admin\",\"password\" : \"admin\"}"))
                .andExpect(status().isOk());

        mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\": \"admin\",\"password\" : \"admin\"}"))
                .andExpect(status().isOk());
    }

//    @Test
//    public void givenLoggedInAdmin_WhenRequestsRestrictedResource_ShouldBeAuthorized() throws Exception {
//
//        mockMvc.perform(post("/register")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content("{\"username\": \"admin\",\"password\" : \"admin\"}"))
//                .andExpect(status().isOk());
//
//        mockMvc.perform(post("/login")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content("{\"username\": \"admin\",\"password\" : \"admin\"}"))
//                .andExpect(status().isOk());
//
//        mockMvc.perform(post("/login")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content("{\"username\": \"admin\",\"password\" : \"admin\"}"))
//                .andExpect(status().isOk());
//
//    }

//    @Test
//    public void openAdminPage() throws Exception {
//        mockMvc.perform(post("/admin")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content("{\"username\": \"admin\",\"password\" : \"admin\"}"))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void openUserPage() throws Exception {
//        mockMvc.perform(post("/users")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content("{\"username\": \"admin\",\"password\" : \"admin\"}"))
//                .andExpect(status().isForbidden());
//    }
}