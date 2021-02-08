package Control.Product_Manager;

import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import Model.DAO.*;
import Model.DTO.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockServletConfig;

class InsertUpdateProductControlTest {
    private InsertUpdateProductControl servlet;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private MockHttpSession session;

    @BeforeEach
    public void setUp() {
        servlet = new InsertUpdateProductControl();
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
    public void InsertProduct() throws ServletException, IOException, SQLException {
        request.setParameter("action","ins");
        request.setParameter("nome","Tavernello");
        request.setParameter("categoria","Bianco");
        request.setParameter("descrizione","Vino in cartone");
        request.setParameter("tipo","Bianco");
        request.setParameter("annata","2020");
        request.setParameter("prezzo","1.2");
        request.setParameter("disponibilita","5");
        servlet.doGet(request,response);

        ProdottoDAO p = new ProdottoDAO();
        List<ProdottoDTO> prods=p.doRetriveAll();

        assertEquals("Tavernello",prods.get(prods.size()-1).getNome());

        p.removeProdotto(p.doRetriveAll().get(p.doRetriveAll().size()-1).getIdProdotto());

    }

}