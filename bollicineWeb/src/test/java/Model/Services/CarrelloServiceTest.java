package Model.Services;

import Model.DTO.ProdottoDTO;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CarrelloServiceTest {

    @Test
    void addProduct() throws SQLException {
        ProdottoService p = new ProdottoService();
        CarrelloService c = new CarrelloService();

        List<ProdottoDTO> products = p.tuttiIProdotti();

        c.addProduct(products.get(0),2);

        assertEquals(false,c.noProduct());


    }

    @Test
    void deleteProduct() throws SQLException {
        ProdottoService p = new ProdottoService();
        CarrelloService c = new CarrelloService();

        List<ProdottoDTO> products = p.tuttiIProdotti();

        c.addProduct(products.get(0),2);
        c.deleteProduct(products.get(0));

        assertEquals(true,c.noProduct());
    }

    @Test
    void getIfExists() throws SQLException {
        ProdottoService p = new ProdottoService();
        CarrelloService c = new CarrelloService();

        List<ProdottoDTO> products = p.tuttiIProdotti();

        c.addProduct(products.get(0),2);
        boolean ris = c.getIfExists(products.get(0));
        assertEquals(true,ris);

        c.deleteProduct(products.get(0));
        ris= c.getIfExists(products.get(0));
        assertEquals(false,ris);

    }

    @Test
    void getProducts() throws SQLException {
        ProdottoService p = new ProdottoService();
        CarrelloService c = new CarrelloService();

        List<ProdottoDTO> products = p.tuttiIProdotti();
        c.addProduct(products.get(0),2);

        List<ProdottoDTO> prodC = c.getProducts();
        assertNotNull(prodC);
    }

    @Test
    void getQ() throws SQLException {
        ProdottoService p = new ProdottoService();
        CarrelloService c = new CarrelloService();

        List<ProdottoDTO> products = p.tuttiIProdotti();

        int ris= c.getQ(products.get(0));
        assertEquals(0,ris);

        c.addProduct(products.get(0),2);
        ris= c.getQ(products.get(0));
        assertEquals(2,ris);
    }

    @Test
    void updateQ() throws SQLException {
        ProdottoService p = new ProdottoService();
        CarrelloService c = new CarrelloService();

        List<ProdottoDTO> products = p.tuttiIProdotti();
        c.addProduct(products.get(0),1);
        c.updateQ(products.get(0).getIdProdotto(),2);

        assertEquals(2,c.getQ(products.get(0)));
    }

    @Test
    void setQ() throws SQLException {
        ProdottoService p = new ProdottoService();
        CarrelloService c = new CarrelloService();

        List<ProdottoDTO> products = p.tuttiIProdotti();
        c.addProduct(products.get(0),1);

        c.setQ(products.get(0).getIdProdotto(),1);

        assertEquals(2,c.getQ(products.get(0)));
    }

    @Test
    void getTotal() throws SQLException {
        ProdottoService p = new ProdottoService();
        CarrelloService c = new CarrelloService();

        List<ProdottoDTO> products = p.tuttiIProdotti();
        c.addProduct(products.get(0),1);

        double ris=c.getTotal();

        assertEquals(products.get(0).getPrezzo(),ris);

        c.deleteProduct(products.get(0));
    }

    @Test
    void noProduct() throws SQLException {
        ProdottoService p = new ProdottoService();
        CarrelloService c = new CarrelloService();

        assertEquals(true ,c.noProduct());

        List<ProdottoDTO> products = p.tuttiIProdotti();
        c.addProduct(products.get(0),1);
        assertEquals(false ,c.noProduct());

    }
}