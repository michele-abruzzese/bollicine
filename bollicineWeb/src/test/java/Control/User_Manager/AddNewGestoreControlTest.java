package Control.User_Manager;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import Control.Order_Manager.AddNewOrdineControl;
import Model.DAO.*;
import Model.DTO.*;
import Model.Services.CarrelloService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockServletConfig;

class AddNewGestoreControlTest {
    private AddNewGestoreControl servlet;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private MockHttpSession session;

    @BeforeEach
    public void setUp() {
        servlet = new AddNewGestoreControl();
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
    public void TestAddGestore() throws ServletException, IOException, SQLException {
        request.setParameter("cognome","Salerno");
        request.setParameter("nome","Daniele");
        request.setParameter("email","daniele@prova.com");
        request.setParameter("password","password");
        request.setParameter("tipo","gestore catalogo");

        servlet.doPost(request,response);

        assertEquals(true,request.getSession().getAttribute("confermato"));

        AccountDAO a = new AccountDAO();
        a.removeAccount(a.doRetriveByEmail("daniele@prova.com").getId());
    }
}