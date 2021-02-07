package Control.User_Manager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import Model.DAO.AccountDAO;
import Model.DTO.AccountDTO;
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
    public void testLoginAdmin() throws ServletException, IOException, SQLException {

        AccountDTO account = new AccountDTO(0,"Giulio","Costante","alfre@prova.com","password","confermato","admin");
        AccountDAO a= new AccountDAO();
        int idAc=a.doSaveAcount(account);


        request.addParameter("email","alfre@prova.com");
        request.addParameter("password","password");

        servlet.doGet(request,response);

        assertEquals("admin",request.getSession().getAttribute("adminRoles"));

        a.removeAccount(idAc);
    }

   @Test
    public void testLoginUtente() throws ServletException, IOException, SQLException {

       AccountDTO account = new AccountDTO(0,"Giulio","Costante","alfre@prova.com","password","confermato","utente");
       AccountDAO a= new AccountDAO();
       int idAc=a.doSaveAcount(account);

       request.addParameter("email","alfre@prova.com");
        request.addParameter("password","password");

        servlet.doGet(request,response);

        assertNotNull(request.getSession().getAttribute("utente"));

        a.removeAccount(idAc);
    }

    @Test
    public void testLoginGestoreCat() throws SQLException, ServletException, IOException {
        AccountDTO account = new AccountDTO(0,"Giulio","Costante","alfre@prova.com","password","confermato","gestore catalogo");
        AccountDAO a= new AccountDAO();
        int idAc=a.doSaveAcount(account);


        request.addParameter("email","alfre@prova.com");
        request.addParameter("password","password");

        servlet.doGet(request,response);

        assertEquals("gestCat",request.getSession().getAttribute("adminRoles"));

        a.removeAccount(idAc);
    }

    @Test
    public void testLoginGestoreOrd() throws SQLException, ServletException, IOException {
        AccountDTO account = new AccountDTO(0,"Giulio","Costante","alfre@prova.com","password","confermato","gestore ordini");
        AccountDAO a= new AccountDAO();
        int idAc=a.doSaveAcount(account);


        request.addParameter("email","alfre@prova.com");
        request.addParameter("password","password");

        servlet.doGet(request,response);

        assertEquals("gestOrd",request.getSession().getAttribute("adminRoles"));

        a.removeAccount(idAc);
    }

    @Test
    public  void testLoginInvalidEmail() throws SQLException, ServletException, IOException {
        AccountDTO account = new AccountDTO(0,"Giulio","Costante","alfre@prova.com","password","confermato","utente");
        AccountDAO a= new AccountDAO();
        int idAc=a.doSaveAcount(account);

        request.addParameter("email","alfr@prova.com");
        request.addParameter("password","password");

        servlet.doGet(request,response);

        assertEquals("true",request.getAttribute("invalidAccess"));

        a.removeAccount(idAc);
    }

}