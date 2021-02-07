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

class DeteleFomCartControlTest {
    private DeteleFomCartControl servlet;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private MockHttpSession session;

    @BeforeEach
    public void setUp() {
        servlet = new DeteleFomCartControl();
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
    public void TestDeleteFromCart() throws IOException, SQLException, ServletException {
        ProdottoDTO prod = new ProdottoDTO(0,"Tavernello","Bianco","Vino in cartone","src/main/webapp/imgs/vino-bianco.jpg","Bianco",2020,1.2,5);
        ProdottoDAO p = new ProdottoDAO();
        int idP=p.doSaveProdotto(prod);

        CarrelloService cart = new CarrelloService();
        cart.addProduct(prod,2);

        request.setParameter("id", String.valueOf(idP));
        request.getSession().setAttribute("cart",cart);

        servlet.doPost(request,response);

        CarrelloService cart2 = (CarrelloService) request.getSession().getAttribute("cart");

        assertEquals(false,cart2.getIfExists(p.doRetriveById(idP)));

        p.removeProdotto(idP);
    }
}