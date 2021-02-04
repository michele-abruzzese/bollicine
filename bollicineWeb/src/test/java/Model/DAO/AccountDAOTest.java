package Model.DAO;

import Model.DTO.AccountDTO;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class AccountDAOTest {

    @Test
    void doSaveAcount() throws SQLException {
        AccountDTO account = new AccountDTO(0,"Alfredo","Cornacchia","alfre@gmail.com","password","confermato","utente");
        AccountDAO ac= new AccountDAO();

        int id= ac.doSaveAcount(account);

        AccountDTO ac1= ac.doRetriveByEmail("alfre@gmail.com");

       assertEquals(id,ac1.getId());


        ac.removeAccount(id);
    }

    @Test
    void removeAccount() throws SQLException {
        AccountDTO account = new AccountDTO(0,"Alfredo","Cornacchia","alfre@gmail.com","password","confermato","utente");
        AccountDAO ac= new AccountDAO();

        int id= ac.doSaveAcount(account);

        ac.removeAccount(id);

        AccountDTO ac1= ac.doRetriveById(id);
        assertNotEquals(ac1,account);
    }

    @Test
    void doRetriveById() throws SQLException {
        AccountDTO account = new AccountDTO(0,"Alfredo","Cornacchia","alfre@gmail.com","password","confermato","utente");
        AccountDAO ac= new AccountDAO();

        int id=ac.doSaveAcount(account);

        AccountDTO ac1=ac.doRetriveById(id);

        assertEquals(id,ac1.getId());

        ac.removeAccount(id);
    }

    @Test
    void doRetriveByEmail() throws SQLException{
        AccountDTO account = new AccountDTO(0,"Alfredo","Cornacchia","alfre@gmail.com","password","confermato","utente");
        AccountDAO ac= new AccountDAO();

        int id= ac.doSaveAcount(account);

        AccountDTO ac1= ac.doRetriveByEmail("alfre@gmail.com");

        assertEquals(account.getEmail(),ac1.getEmail());

        ac.removeAccount(id);

    }

    @Test
    void controlEmail() throws SQLException{
        AccountDTO account = new AccountDTO(0,"Alfredo","Cornacchia","alfre@gmail.com","password","confermato","utente");
        AccountDAO ac= new AccountDAO();

        int id= ac.doSaveAcount(account);

        int control=ac.controlEmail(account.getEmail());

        assertEquals(1,control);
        /*Se controlEmail ritorna il valore 1 allora vuol dire che esiste quella email nel db*/

        ac.removeAccount(id);

        int control1=ac.controlEmail(account.getEmail());

        assertEquals(0,control1);
    }
}