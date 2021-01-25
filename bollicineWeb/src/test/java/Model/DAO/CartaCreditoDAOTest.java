package Model.DAO;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CartaCreditoDAOTest {

    @Test
    void doSaveCartaCredito() throws SQLException{
        CartaCreditoDTO card = new CartaCreditoDTO(0,"Alfredo","Cornacchia",123456L,123,"01/01/2020",1);
        CartaCreditoDAO c = new CartaCreditoDAO();

        int id=c.doSaveCartaCredito(card);

        CartaCreditoDTO card2 = c.doRetriveById(id);

        assertNotNull(card2);

        c.doDelete(id);

    }

    @Test
    void doRetriveByAccount() throws SQLException{
        CartaCreditoDTO card = new CartaCreditoDTO(0,"Alfredo","Cornacchia",123456L,123,"01/01/2020",1);
        CartaCreditoDAO c = new CartaCreditoDAO();

        int id=c.doSaveCartaCredito(card);

        List<CartaCreditoDTO> carte=c.doRetriveByAccount(card.getIdAccount());

        assertEquals(1,carte.get(0).getIdAccount());

        c.doDelete(id);
    }

    @Test
    void doRetriveById() throws SQLException{
        CartaCreditoDTO card = new CartaCreditoDTO(0,"Alfredo","Cornacchia",123456L,123,"01/01/2020",1);
        CartaCreditoDAO c = new CartaCreditoDAO();

        int id=c.doSaveCartaCredito(card);

        CartaCreditoDTO card2 = c.doRetriveById(id);

        assertEquals(id,card2.getIdCartaCredito());

        c.doDelete(id);

    }

    @Test
    void doDelete() throws SQLException {
        CartaCreditoDTO card = new CartaCreditoDTO(0,"Alfredo","Cornacchia",123456L,123,"01/01/2020",1);
        CartaCreditoDAO c = new CartaCreditoDAO();

        int id=c.doSaveCartaCredito(card);
        c.doDelete(id);

        CartaCreditoDTO card2 = c.doRetriveById(id);

        assertNotEquals(card,card2);
    }
}