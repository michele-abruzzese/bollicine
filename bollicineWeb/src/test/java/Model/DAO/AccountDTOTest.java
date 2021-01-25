package Model.DAO;

import Model.DTO.AccountDTO;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class AccountDTOTest {

    /*CONSTRUCTORS TESTING*/

    @Test
    void testAccountEmptyConstructor(){
        AccountDTO account = new AccountDTO();
        assertNotNull(account);
    }

    @Test
    void testAccountConstructor(){
        AccountDTO account = new AccountDTO(1,"Alfredo","Cornacchia","alfre@gmail.com","password","confermato","utente");
        assertNotNull(account);
    }

    /*GETTER METHOD TESTING*/

    @Test
    void testGetId(){
        AccountDTO account = new AccountDTO(1,"Alfredo","Cornacchia","alfre@gmail.com","password","confermato","utente");
        assertEquals(1,account.getId());
    }

    @Test
    void testGetNome(){
        AccountDTO account = new AccountDTO(1,"Alfredo","Cornacchia","alfre@gmail.com","password","confermato","utente");
        assertEquals("Alfredo",account.getNome());
    }

    @Test
    void testGetCognome(){
        AccountDTO account = new AccountDTO(1,"Alfredo","Cornacchia","alfre@gmail.com","password","confermato","utente");
        assertEquals("Cornacchia",account.getCognome());
    }

    @Test
    void testGetEmail(){
        AccountDTO account = new AccountDTO(1,"Alfredo","Cornacchia","alfre@gmail.com","password","confermato","utente");
        assertEquals("alfre@gmail.com",account.getEmail());
    }

    @Test
    void testGetPassword(){
        AccountDTO account = new AccountDTO(1,"Alfredo","Cornacchia","alfre@gmail.com","password","confermato","utente");
        assertEquals("password",account.getPassword());
    }

    @Test
    void testGetStato(){
        AccountDTO account = new AccountDTO(1,"Alfredo","Cornacchia","alfre@gmail.com","password","confermato","utente");
        assertEquals("confermato",account.getStato());
    }

    @Test
    void testGetTipo(){
        AccountDTO account = new AccountDTO(1,"Alfredo","Cornacchia","alfre@gmail.com","password","confermato","utente");
        assertEquals("utente",account.getTipo());
    }

    /*SETTER METHOD TESTING*/

    @Test
    void testSetId() throws SQLException {

        AccountDTO account= new AccountDTO();
        account.setId(1);

        assertEquals(1, account.getId());

    }

    @Test
    void testSetNome() throws SQLException {

        AccountDTO account= new AccountDTO();
        account.setNome("Alfredo");

        assertEquals("Alfredo", account.getNome());

    }

    @Test
    void testSetCognome() throws SQLException {

        AccountDTO account= new AccountDTO();
        account.setCognome("Cornacchia");

        assertEquals("Cornacchia", account.getCognome());

    }

    @Test
    void testSetEmail() throws SQLException {

        AccountDTO account= new AccountDTO();
        account.setEmail("alfredo@mail.it");

        assertEquals("alfredo@mail.it", account.getEmail());

    }

    @Test
    void testSetPassword() throws SQLException {

        AccountDTO account= new AccountDTO();
        account.setPassword("password");

        assertEquals("password", account.getPassword());

    }

    @Test
    void testSetStato() throws SQLException {

        AccountDTO account= new AccountDTO();
        account.setStato("confermato");

        assertEquals("confermato", account.getStato());

    }

    @Test
    void testSetTipo() throws SQLException {

        AccountDTO account= new AccountDTO();
        account.setTipo("utente");

        assertEquals("utente", account.getTipo());

    }


}
