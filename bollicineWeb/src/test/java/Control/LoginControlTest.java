package Control;

import Control.User_Manager.LoginControl;

import org.junit.Before;
import org.junit.Test;

import org.mockito.Mockito;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.SQLException;

public class LoginControlTest extends Mockito {
    private LoginControl servlet;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    /**
     * Before.
     */
    @Before
    public void setUp() {
        servlet = new LoginControl();
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }

    @Test
    public void testLoginUtente() throws ServletException, IOException {

        request.addParameter("email","giuseppe4@live.it");
        request.addParameter("password","password");

        servlet.doGet(request,response);

        assertNotNull(request.getSession().getAttribute("utente"));
    }

    @Test
    public void testLoginAdmin() throws ServletException, IOException{

        request.addParameter("email","email@admin.com");
        request.addParameter("password","admin");

        servlet.doGet(request,response);

        assertEquals("admin",request.getSession().getAttribute("adminRoles"));
    }
}
