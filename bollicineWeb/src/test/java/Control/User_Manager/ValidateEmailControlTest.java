package Control.User_Manager;

import static org.junit.jupiter.api.Assertions.*;

import Model.DAO.AccountDAO;
import Model.DTO.AccountDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockServletConfig;
import org.springframework.http.MediaType;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.SQLException;

class ValidateEmailControlTest {
    private ValidateEmailControl servlet;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private MockHttpSession session;

    @BeforeEach
    public void setUp() {
        servlet = new ValidateEmailControl();
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
    public void TestValidateEmailEsistente() throws ServletException, IOException, SQLException {
        AccountDTO account = new AccountDTO(0,"Giulio","Costante","alfre@prova.com","password","confermato","utente");
        AccountDAO a= new AccountDAO();
        int idAc=a.doSaveAcount(account);

        request.setParameter("email","alfre@prova.com");
        servlet.doPost(request,response);
        assertEquals("1",response.getContentAsString());
        a.removeAccount(idAc);
    }

    @Test
    public void TestValidateEmailNonEsistente() throws SQLException, ServletException, IOException {
        AccountDTO account = new AccountDTO(0,"Giulio","Costante","alfre@prova.com","password","confermato","utente");
        AccountDAO a= new AccountDAO();
        int idAc=a.doSaveAcount(account);

        request.setParameter("email","alf@prova.com");
        servlet.doPost(request,response);
        assertEquals("0",response.getContentAsString());
        a.removeAccount(idAc);
    }

}