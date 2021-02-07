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

class AddNewIndirizzoControlTest {
    private AddNewIndirizzoControl servlet;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private MockHttpSession session;

    @BeforeEach
    public void setUp() {
        servlet = new AddNewIndirizzoControl();
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
    public void TestAddNewIndirizzo() throws SQLException, ServletException, IOException {
        AccountDTO account = new AccountDTO(0,"Giulio","Costante","alfre@gmail.com","password","confermato","utente");
        AccountDAO a= new AccountDAO();
        int idAc=a.doSaveAcount(account);

        request.setParameter("idCliente", String.valueOf(idAc));
        request.setParameter("nome","Giulio");
        request.setParameter("cognome","Costante");
        request.setParameter("indirizzo","Via Roma");
        request.setParameter("citta","Trevico");
        request.setParameter("cap","83045");
        request.setParameter("provincia","Avellino");
        request.setParameter("alias","ufficio");

        servlet.doPost(request,response);

        assertEquals(true, request.getAttribute("insertFirstIndirizzo"));

        request.setParameter("idCliente", String.valueOf(idAc));
        request.setParameter("nome","Giulio");
        request.setParameter("cognome","Costante");
        request.setParameter("indirizzo","Via Buongiorno");
        request.setParameter("citta","Grottaminarda");
        request.setParameter("cap","83049");
        request.setParameter("provincia","Avellino");
        request.setParameter("alias","casa");

        servlet.doPost(request,response);

        a.removeAccount(idAc);

    }

}