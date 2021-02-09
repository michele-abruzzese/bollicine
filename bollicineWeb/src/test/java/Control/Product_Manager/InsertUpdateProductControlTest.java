package Control.Product_Manager;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.Part;

import Model.DAO.*;
import Model.DTO.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.commons.CommonsFileUploadSupport;

class InsertUpdateProductControlTest {
    private InsertUpdateProductControl servlet;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private MockHttpSession session;
    private MockMvc mockMvc;
    private WebApplicationContext wac;

    @BeforeEach
    public void setUp() {
        servlet = new InsertUpdateProductControl();
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        request.setSession(session);
        Part file = mock(Part.class);
    }

    @BeforeEach
    public void oneWaySetup() throws ServletException {
        ServletConfig sg = new MockServletConfig();
        servlet.init(sg);
    }

    @Test
    public void InsertProduct() throws Exception {
        request.setParameter("action","ins");
        request.setParameter("nome","Tavernello");
        request.setParameter("categoria","Bianco");
        request.setParameter("descrizione","Vino in cartone");
        request.setParameter("tipo","Bianco");
        request.setParameter("annata","2020");
        request.setParameter("prezzo","1.2");
        request.setParameter("disponibilita","5");

//        Image
        byte[] b = new byte[10000];
        Part pa = new MockPart("immagine","immagine.jpg",b);
        request.addPart(pa);

        servlet.doGet(request,response);

        ProdottoDAO p = new ProdottoDAO();
        List<ProdottoDTO> prods=p.doRetriveAll();

        assertEquals("Tavernello",prods.get(prods.size()-1).getNome());

        p.removeProdotto(p.doRetriveAll().get(p.doRetriveAll().size()-1).getIdProdotto());

    }

}