package Control.Product_Manager;

import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import Model.DAO.*;
import Model.DTO.*;
import Model.Services.CarrelloService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockServletConfig;

class ProdottoSingoloControlTest {
    private ProdottoSingoloControl servlet;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private MockHttpSession session;

    @BeforeEach
    public void setUp() {
        servlet = new ProdottoSingoloControl();
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
    public void testProdottoSingolo() throws IOException, SQLException, ServletException {
        ProdottoDTO prod = new ProdottoDTO(0,"Tavernello","Bianco","Vino in cartone","src/main/webapp/imgs/vino-bianco.jpg","Bianco",2020,1.2,5);
        ProdottoDAO p = new ProdottoDAO();
        int idP=p.doSaveProdotto(prod);

        request.setParameter("id", String.valueOf(idP));

        servlet.doPost(request,response);

        ProdottoDTO prod2 = (ProdottoDTO) request.getAttribute("product");

        assertEquals(idP,prod2.getIdProdotto());

        p.removeProdotto(idP);
    }
}