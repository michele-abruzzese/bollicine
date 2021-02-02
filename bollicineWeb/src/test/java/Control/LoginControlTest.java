package Control;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import Control.User_Manager.LoginControl;
import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockServletConfig;

class LoginControlTest {

    private LoginControl servlet;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private MockHttpSession session;

    @BeforeEach
    public void setUp() {
        servlet = new LoginControl();
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        request.setSession(session);

    }

    @BeforeEach
    public void oneWaySetup() throws ServletException {
        ServletConfig sg = new MockServletConfig();
        servlet.init(sg);
    }


    @Test
    public void testLoginAdmin() throws ServletException, IOException {

        request.addParameter("email","email@admin.com");
        request.addParameter("password","admin");

        servlet.doGet(request,response);

        assertEquals("admin",request.getSession().getAttribute("adminRoles"));
    }

   @Test
    public void testLoginUtente() throws ServletException, IOException {

        request.addParameter("email","higelik497@febula.com");
        request.addParameter("password","terra");

        servlet.doGet(request,response);

        assertNotNull(request.getSession().getAttribute("utente"));
    }
}