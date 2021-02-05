package Model.Services;

import Model.DAO.ProdottoDAO;
import Model.DTO.ProdottoDTO;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProdottoServiceTest {

    @Test
    void tuttiIProdotti() throws SQLException {
        ProdottoService p = new ProdottoService();
        List<ProdottoDTO> products = p.tuttiIProdotti();
        assertNotNull(products);
    }

    @Test
    void immaginePerId() throws SQLException {
        ProdottoService p = new ProdottoService();
        List<ProdottoDTO> products = p.tuttiIProdotti();
        byte[] img = p.immaginePerId(products.get(0).getIdProdotto());
        assertNotNull(img);
    }

    @Test
    void prodottiPerCategoria() throws SQLException {
        ProdottoService p = new ProdottoService();

        List<ProdottoDTO> prodBi= p.prodottiPerCategoria("bi");
        assertEquals("bianchi",prodBi.get(0).getCategoria());

        List<ProdottoDTO> prodRo= p.prodottiPerCategoria("ro");
        assertEquals("rossi",prodRo.get(0).getCategoria());

        List<ProdottoDTO> prodSpu= p.prodottiPerCategoria("spu");
        assertEquals("spumanti",prodSpu.get(0).getCategoria());

        List<ProdottoDTO> prodNull= p.prodottiPerCategoria("mi");
        assertNull(prodNull);

    }

    @Test
    void prodottoPerId() throws SQLException {
        ProdottoService p = new ProdottoService();
        List<ProdottoDTO> products = p.tuttiIProdotti();
        ProdottoDTO prod= p.prodottoPerId(products.get(0).getIdProdotto());
        assertEquals(products.get(0).getIdProdotto(),prod.getIdProdotto());
    }

    @Test
    void aggiungiProdottoAlCarrello() throws SQLException {
        ProdottoService p = new ProdottoService();
        CarrelloService c = new CarrelloService();
        List<ProdottoDTO> products = p.tuttiIProdotti();
        p.aggiungiProdottoAlCarrello(products.get(0).getIdProdotto(),2,c);
        p.aggiungiProdottoAlCarrello(products.get(0).getIdProdotto(),3,c);
        assertEquals(false, c.noProduct());
    }

    @Test
    void rimuoviProdottoDalCarrello() throws SQLException {
        ProdottoService p = new ProdottoService();
        CarrelloService c = new CarrelloService();
        List<ProdottoDTO> products = p.tuttiIProdotti();
        p.aggiungiProdottoAlCarrello(products.get(0).getIdProdotto(),2,c);
        p.rimuoviProdottoDalCarrello(products.get(0).getIdProdotto(),c);
        assertEquals(true,c.noProduct());
    }

    @Test
    void aggiornaQtProdottoDalCarrello() throws SQLException {
        ProdottoService p = new ProdottoService();
        CarrelloService c = new CarrelloService();
        List<ProdottoDTO> products = p.tuttiIProdotti();
        p.aggiungiProdottoAlCarrello(products.get(0).getIdProdotto(),1,c);
        p.aggiornaQtProdottoDalCarrello(products.get(0).getIdProdotto(),2,c);
        assertEquals(2,c.getQ(products.get(0)));
    }

    @Test
    void inserisciProdottoNelCatalogo() throws IOException, SQLException {
        ProdottoService p = new ProdottoService();
        p.inserisciProdottoNelCatalogo("Tavernello","Bianco","Vino in cartone","src/main/webapp/imgs/vino-bianco.jpg","Bianco",2020,1.2,5);
        List<ProdottoDTO> products = p.tuttiIProdotti();
        assertEquals("Tavernello",products.get(products.size()-1).getNome());

        ProdottoDAO pr = new ProdottoDAO();
        pr.removeProdotto(products.get(products.size()-1).getIdProdotto());

    }

    @Test
    void aggiornaProdotto() throws IOException, SQLException {
        ProdottoService p = new ProdottoService();

        ProdottoDTO prod = new ProdottoDTO(0,"Tavernello","Bianco","Vino in cartone","src/main/webapp/imgs/vino-bianco.jpg","Bianco",2020,1.2,5);
        ProdottoDAO pr = new ProdottoDAO();
        int key=pr.doSaveProdotto(prod);

        p.aggiornaProdotto(key,"Tavernello","Bianco","Vino in cartone","src/main/webapp/imgs/vino-bianco.jpg","Bianco",2020,2,5);

        assertEquals(2,p.prodottoPerId(key).getPrezzo());

        pr.removeProdotto(key);

    }

    @Test
    void rimuoviProdottoDalCatalogo() throws IOException, SQLException {
        ProdottoService p = new ProdottoService();
        p.inserisciProdottoNelCatalogo("Tavernello","Bianco","Vino in cartone","src/main/webapp/imgs/vino-bianco.jpg","Bianco",2020,1.2,5);
        List<ProdottoDTO> products1 = p.tuttiIProdotti();
        p.rimuoviProdottoDalCatalogo(products1.get(products1.size()-1).getIdProdotto());

        List<ProdottoDTO> products2 = p.tuttiIProdotti();

        assertEquals(0,products2.get(products2.size()-1).getDisponibilità());

        ProdottoDAO pr = new ProdottoDAO();
        pr.removeProdotto(products1.get(products1.size()-1).getIdProdotto());
    }
}