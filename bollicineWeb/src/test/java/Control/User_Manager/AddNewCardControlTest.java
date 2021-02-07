package Control.User_Manager;

import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import Control.Product_Manager.UpdateFromCartControl;
import Model.DAO.*;
import Model.DTO.*;
import Model.Services.CarrelloService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockServletConfig;

class AddNewCardControlTest {
    private AddNewCardControl servlet;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private MockHttpSession session;

    @BeforeEach
    public void setUp() {
        servlet = new AddNewCardControl();
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
    public void TestAddNewCard() throws IOException, SQLException, ServletException {
        AccountDTO account = new AccountDTO(0,"Giulio","Costante","alfre@gmail.com","password","confermato","utente");
        AccountDAO a= new AccountDAO();
        int idAc=a.doSaveAcount(account);

        request.setParameter("idCliente", String.valueOf(idAc));
        request.setParameter("nome","Giuseppe");
        request.setParameter("cognome", "Salerno");
        request.setParameter("numero","1234567890");
        request.setParameter("ccv","123");
        request.setParameter("scadenza","2021-07-01");

        servlet.doPost(request,response);

        assertEquals(true, request.getAttribute("insertFirstCarta"));

        request.setParameter("idCliente", String.valueOf(idAc));
        request.setParameter("nome","Giuseppe");
        request.setParameter("cognome", "Salerno");
        request.setParameter("numero","0987654321");
        request.setParameter("ccv","321");
        request.setParameter("scadenza","2021-09-30");

        servlet.doPost(request,response);

        assertNotNull(request.getSession().getAttribute("carte"));

        a.removeAccount(idAc);
    }
}