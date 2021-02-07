package Control.Order_Manager;

import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import Model.DAO.*;
import Model.DTO.*;
import Model.Services.CarrelloService;
import Model.Services.OrdineService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockServletConfig;

class VisualizzaOrdiniControlTest {
    private VisualizzaOrdiniControl servlet;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private MockHttpSession session;

    @BeforeEach
    public void setUp() {
        servlet = new VisualizzaOrdiniControl();
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
    public void TestVisualizzaOrdiniControl() throws ServletException, IOException, SQLException {
        OrdineService o = new OrdineService();
        List<OrdineDTO> orders = o.tuttiGliOrdini();
        List<IndirizzoSpedDTO> indirizzi = o.indirizziDegliOrdini();
        List<CartaCreditoDTO> cards = o.carteDegliOrdini();

        servlet.doPost(request,response);

        List<OrdineDTO> orders2 = (List<OrdineDTO>) request.getAttribute("ordini");
        List<IndirizzoSpedDTO> indirizzi2 = (List<IndirizzoSpedDTO>) request.getAttribute("indirizzi");
        List<CartaCreditoDTO> cards2 = (List<CartaCreditoDTO>) request.getAttribute("carte");

        assertEquals(orders.size(),orders2.size());
        assertEquals(indirizzi.size(),indirizzi2.size());
        assertEquals(cards.size(),cards2.size());

    }
}