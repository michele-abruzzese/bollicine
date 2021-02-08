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

class AddNewClientControlTest {
    private AddNewClientControl servlet;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private MockHttpSession session;

    @BeforeEach
    public void setUp() {
        servlet = new AddNewClientControl();
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
    public void TestAddNewClientDaConfermare() throws ServletException, IOException, SQLException {
        request.setParameter("action","0");
        request.setParameter("cognome","Salerno");
        request.setParameter("nome","Daniele");
        request.setParameter("email","daniele@prova.it");
        request.setParameter("password","password");

        servlet.doPost(request,response);

        assertEquals(true,request.getSession().getAttribute("confermaEmail"));

        request.setParameter("action","1");
        servlet.doPost(request,response);
        assertEquals(true,request.getSession().getAttribute("confermato"));

        AccountDAO a = new AccountDAO();
        a.removeAccount(a.doRetriveByEmail("daniele@prova.it").getId());
    }

    @Test
    public void TestConfermaAccount(){
    }
}