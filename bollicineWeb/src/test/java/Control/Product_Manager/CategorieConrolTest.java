package Control.Product_Manager;

import static org.junit.jupiter.api.Assertions.*;
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

class CategorieConrolTest {
    private CategorieConrol servlet;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private MockHttpSession session;

    @BeforeEach
    public void setUp() {
        servlet = new CategorieConrol();
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
    public void TestCategorie() throws ServletException, IOException {
        request.setParameter("cat","bi");
        servlet.doPost(request,response);
        List<ProdottoDTO> products = (List<ProdottoDTO>) request.getAttribute("products");
        for (int i = 0; i<products.size(); i++){
            assertEquals("bianchi",products.get(i).getCategoria());
        }
    }
}