package Model.DAO;

import Model.DTO.ProdottoDTO;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProdottoDAOTest {

    @Test
    void doSaveProdotto() throws IOException, SQLException {
        ProdottoDTO prod = new ProdottoDTO(0,"Tavernello","Bianco","Vino in cartone","/Users/roccopagliarulo/IdeaProjects/bollicine/bollicineWeb/src/main/webapp/imgs/vino-bianco.jpg","Bianco",2020,1.2,5);
        ProdottoDAO p = new ProdottoDAO();
        int key=p.doSaveProdotto(prod);

        assertEquals(key,p.doRetriveById(key).getIdProdotto());

        p.removeProdotto(key);

    }

    @Test
    void doUpdateProdotto() throws IOException, SQLException {
        ProdottoDTO prod = new ProdottoDTO(0,"Tavernello","Bianco","Vino in cartone","/Users/roccopagliarulo/IdeaProjects/bollicine/bollicineWeb/src/main/webapp/imgs/vino-bianco.jpg","Bianco",2020,1.2,5);
        ProdottoDAO p = new ProdottoDAO();
        int key=p.doSaveProdotto(prod);
        prod.setIdProdotto(key);
        prod.setPrezzo(15);
        p.doUpdateProdotto(prod);

        assertEquals(15,p.doRetriveById(key).getPrezzo());

        p.removeProdotto(key);
    }

    @Test
    void doRetriveAll() throws IOException, SQLException {
        ProdottoDTO prod = new ProdottoDTO(0,"Tavernello","Bianco","Vino in cartone","/Users/roccopagliarulo/IdeaProjects/bollicine/bollicineWeb/src/main/webapp/imgs/vino-bianco.jpg","Bianco",2020,1.2,5);
        ProdottoDAO p = new ProdottoDAO();
        int key=p.doSaveProdotto(prod);

        List<ProdottoDTO> prods =p.doRetriveAll();

        assertEquals(key,prods.get(prods.size()-1).getIdProdotto());

        p.removeProdotto(key);
    }

    @Test
    void doRetriveByCat() throws SQLException, IOException {
        ProdottoDTO prod = new ProdottoDTO(0,"Tavernello","Bianco","Vino in cartone","/Users/roccopagliarulo/IdeaProjects/bollicine/bollicineWeb/src/main/webapp/imgs/vino-bianco.jpg","Bianco",2020,1.2,5);
        ProdottoDAO p = new ProdottoDAO();
        int key=p.doSaveProdotto(prod);

        List<ProdottoDTO> prods =p.doRetriveByCat("Bianco");

        assertEquals("Bianco",prods.get(prods.size()-1).getCategoria());

        p.removeProdotto(key);
    }

    @Test
    void doRetriveImgById() throws IOException, SQLException {
        ProdottoDTO prod = new ProdottoDTO(0,"Tavernello","Bianco","Vino in cartone","/Users/roccopagliarulo/IdeaProjects/bollicine/bollicineWeb/src/main/webapp/imgs/vino-bianco.jpg","Bianco",2020,1.2,5);
        ProdottoDAO p = new ProdottoDAO();
        int key=p.doSaveProdotto(prod);



        assertNotNull(p.doRetriveImgById(key));

        p.removeProdotto(key);
    }

    @Test
    void doRetriveById() throws IOException, SQLException {
        ProdottoDTO prod = new ProdottoDTO(0,"Tavernello","Bianco","Vino in cartone","/Users/roccopagliarulo/IdeaProjects/bollicine/bollicineWeb/src/main/webapp/imgs/vino-bianco.jpg","Bianco",2020,1.2,5);
        ProdottoDAO p = new ProdottoDAO();
        int key=p.doSaveProdotto(prod);

        assertEquals(key,p.doRetriveById(key).getIdProdotto());

        p.removeProdotto(key);
    }

    @Test
    void updateDispo() throws IOException, SQLException {
        ProdottoDTO prod = new ProdottoDTO(0,"Tavernello","Bianco","Vino in cartone","/Users/roccopagliarulo/IdeaProjects/bollicine/bollicineWeb/src/main/webapp/imgs/vino-bianco.jpg","Bianco",2020,1.2,5);
        ProdottoDAO p = new ProdottoDAO();
        int key=p.doSaveProdotto(prod);

        p.updateDispo(p.doRetriveById(key),2);

        assertEquals(2,p.doRetriveById(key).getDisponibilità());

        p.removeProdotto(key);
    }

    @Test
    void removeProdotto() throws IOException, SQLException {
        ProdottoDTO prod = new ProdottoDTO(0,"Tavernello","Bianco","Vino in cartone","/Users/roccopagliarulo/IdeaProjects/bollicine/bollicineWeb/src/main/webapp/imgs/vino-bianco.jpg","Bianco",2020,1.2,5);
        ProdottoDAO p = new ProdottoDAO();
        int key=p.doSaveProdotto(prod);

        p.removeProdotto(key);

        assertNotEquals(key,p.doRetriveById(key).getIdProdotto());
    }
}