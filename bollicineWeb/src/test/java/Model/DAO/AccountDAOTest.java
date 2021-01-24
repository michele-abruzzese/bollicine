package Model.DAO;

import Model.Beans.AccountBean;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class AccountDAOTest {

    @Test
    void doSaveAcount() throws SQLException {
        AccountDTO account = new AccountDTO(0,"Alfredo","Cornacchia","alfre@gmail.com","password","confermato","utente");
        AccountDAO ac= new AccountDAO();

        int id= ac.doSaveAcount(account);
        System.out.println(id);

        AccountDTO ac1= ac.doRetriveByEmail("alfre@gmail.com");
        System.out.println(ac1.getEmail()+" "+ac1.getId());

        assertEquals(id,ac1.getId());

    }

    @Test
    void removeAccount() {
    }

    @Test
    void doRetriveById() {
    }

    @Test
    void doRetriveByEmail() throws SQLException{
    }
}