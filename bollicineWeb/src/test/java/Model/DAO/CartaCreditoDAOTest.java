package Model.DAO;

import Model.DTO.AccountDTO;
import Model.DTO.CartaCreditoDTO;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CartaCreditoDAOTest {

    @Test
    void doSaveCartaCredito() throws SQLException{
        AccountDTO account = new AccountDTO(0,"Alfredo","Cornacchia","alfre@gmail.com","password","confermato","utente");
        AccountDAO ac= new AccountDAO();
        int idAc= ac.doSaveAcount(account);

        CartaCreditoDTO card = new CartaCreditoDTO(0,"Alfredo","Cornacchia",123456L,123,"2020-01-01",idAc);
        CartaCreditoDAO c = new CartaCreditoDAO();

        int id=c.doSaveCartaCredito(card);
        card.setIdCartaCredito(id);

        assertEquals(card,c.doRetriveById(id));

        c.doDelete(id);
        ac.removeAccount(idAc);
    }

    @Test
    void doRetriveByAccount() throws SQLException{
        AccountDTO account = new AccountDTO(0,"Alfredo","Cornacchia","alfre@gmail.com","password","confermato","utente");
        AccountDAO ac= new AccountDAO();
        int idAc= ac.doSaveAcount(account);

        CartaCreditoDTO card = new CartaCreditoDTO(0,"Alfredo","Cornacchia",123456L,123,"2020-01-01",idAc);
        CartaCreditoDAO c = new CartaCreditoDAO();

        int id=c.doSaveCartaCredito(card);
        card.setIdCartaCredito(id);

        List<CartaCreditoDTO> carte=c.doRetriveByAccount(card.getIdAccount());

        assertEquals(card,carte.get(0));

        c.doDelete(id);
        ac.removeAccount(idAc);
    }

    @Test
    void doRetriveById() throws SQLException{
        AccountDTO account = new AccountDTO(0,"Alfredo","Cornacchia","alfre@gmail.com","password","confermato","utente");
        AccountDAO ac= new AccountDAO();
        int idAc= ac.doSaveAcount(account);

        CartaCreditoDTO card = new CartaCreditoDTO(0,"Alfredo","Cornacchia",123456L,123,"2020-01-01",idAc);
        CartaCreditoDAO c = new CartaCreditoDAO();

        int id=c.doSaveCartaCredito(card);
        card.setIdCartaCredito(id);

        assertEquals(card,c.doRetriveById(id));

        c.doDelete(id);
        ac.removeAccount(idAc);

    }

    @Test
    void doDelete() throws SQLException {
        AccountDTO account = new AccountDTO(0,"Alfredo","Cornacchia","alfre@gmail.com","password","confermato","utente");
        AccountDAO ac= new AccountDAO();
        int idAc= ac.doSaveAcount(account);

        CartaCreditoDTO card = new CartaCreditoDTO(0,"Alfredo","Cornacchia",123456L,123,"2020-01-01",idAc);
        CartaCreditoDAO c = new CartaCreditoDAO();

        int id=c.doSaveCartaCredito(card);
        card.setIdCartaCredito(id);
        c.doDelete(id);

        List <CartaCreditoDTO> cards=c.doRetriveByAccount(idAc);

        assertEquals(0,cards.size());

        ac.removeAccount(idAc);
    }
}