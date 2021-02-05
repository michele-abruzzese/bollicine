package Model.Services;

import Model.DAO.AccountDAO;
import Model.DTO.AccountDTO;
import Model.DTO.CartaCreditoDTO;
import Model.DTO.IndirizzoSpedDTO;
import org.junit.jupiter.api.Test;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AccountServiceTest {

    @Test
    void registraAccount() throws SQLException {
        AccountService a = new AccountService();
        int key = a.registraAccount("Alfredo","Cornacchia","alfre@gmail.com","password","confermato","utente");
        assertNotNull(key);
        a.removeAccount(key);
    }

    @Test
    void removeAccount() throws SQLException {
        AccountService a = new AccountService();
        int key = a.registraAccount("Alfredo","Cornacchia","alfre@gmail.com","password","confermato","utente");
        a.removeAccount(key);
        assertNotEquals(key,a.doRetriveById(key).getId());
    }

    @Test
    void doRetriveById() throws SQLException {
        AccountService a = new AccountService();
        int key = a.registraAccount("Alfredo","Cornacchia","alfre@gmail.com","password","confermato","utente");

        assertEquals(key,a.doRetriveById(key).getId());
        a.removeAccount(key);
    }

    @Test
    void doRetriveByEmail() throws SQLException {
        AccountService a = new AccountService();
        int key = a.registraAccount("Alfredo","Cornacchia","alfre@gmail.com","password","confermato","utente");

        assertEquals(key,a.doRetriveByEmail("alfre@gmail.com").getId());
        a.removeAccount(key);
    }

    @Test
    void salvaIndirizzo() throws SQLException {
        AccountService a = new AccountService();
        int key = a.registraAccount("Alfredo","Cornacchia","alfre@gmail.com","password","confermato","utente");

        int ris = a.salvaIndirizzo("Alfredo", "Cornacchia","Via roma",84084,"Fisciano","Salerno","ufficio",key);
        assertEquals(0,ris);

        ris = a.salvaIndirizzo("Alfredo", "Cornacchia","Via Bruno",83048,"Grottaminarda","Avellino","casa",key);
        assertEquals(1,ris);

        List<IndirizzoSpedDTO> indirizzi =a.doRetriveIndirizzi(key);
        a.removeIndirizzo(indirizzi.get(0).getIdIndirizzo());
        a.removeIndirizzo(indirizzi.get(1).getIdIndirizzo());
        a.removeAccount(key);
    }

    @Test
    void salvaCarta() throws SQLException {
        AccountService a = new AccountService();
        int key = a.registraAccount("Alfredo","Cornacchia","alfre@gmail.com","password","confermato","utente");

        int ris = a.salvaCarta("Alfredo","Cornacchia",123456789L,123,"2020-03-03",key);
        assertEquals(0,ris);

        ris =  a.salvaCarta("Alfredo","Cornacchia",987654321L,123,"2020-03-03",key);
        assertEquals(1,ris);

        List<CartaCreditoDTO> carte =a.doRetriveCarte(key);
        a.removeCarta(carte.get(0).getIdCartaCredito());
        a.removeCarta(carte.get(1).getIdCartaCredito());
        a.removeAccount(key);
    }

    @Test
    void controlEmail() throws SQLException {
        AccountService a = new AccountService();

        int ris=a.controlEmail("alfre@gmail.com");
        assertEquals(0,ris);

        int key = a.registraAccount("Alfredo","Cornacchia","alfre@gmail.com","password","confermato","utente");

        ris= a.controlEmail("alfre@gmail.com");
        assertEquals(1,ris);
        a.removeAccount(key);
    }

    @Test
    void doRetriveIndirizzi() throws SQLException {
        AccountService a = new AccountService();
        int key = a.registraAccount("Alfredo","Cornacchia","alfre@gmail.com","password","confermato","utente");

        int ris = a.salvaIndirizzo("Alfredo", "Cornacchia","Via roma",84084,"Fisciano","Salerno","ufficio",key);

        List<IndirizzoSpedDTO> indirizzi =a.doRetriveIndirizzi(key);

        assertEquals(1,indirizzi.size());

        a.removeIndirizzo(indirizzi.get(0).getIdIndirizzo());
        a.removeAccount(key);
    }

    @Test
    void doRetriveCarte() throws SQLException {
        AccountService a = new AccountService();
        int key = a.registraAccount("Alfredo","Cornacchia","alfre@gmail.com","password","confermato","utente");

        int ris = a.salvaCarta("Alfredo","Cornacchia",123456789L,123,"2020-03-03",key);
        assertEquals(0,ris);

        List<CartaCreditoDTO> carte =a.doRetriveCarte(key);

        assertEquals(1,carte.size());

        a.removeCarta(carte.get(0).getIdCartaCredito());
        a.removeAccount(key);
    }

    @Test
    void creaAccountDaConfermare() throws SQLException {
        AccountService a = new AccountService();

        AccountDTO account = a.creaAccountDaConfermare("Giulio","Costante","giulio@prova.com","password","non confermato","utente");
        assertNotNull(account);

        a.removeAccount(account.getId());
    }

    @Test
    void confermaAccount() throws SQLException {
        AccountService a = new AccountService();

        AccountDTO account = a.creaAccountDaConfermare("Giulio","Costante","giulio@prova.com","password","non confermato","utente");
        a.confermaAccount(account);
        assertEquals("confermato",account.getStato());

        a.removeAccount(account.getId());
    }

    @Test
    void sandEmail() throws UnsupportedEncodingException, MessagingException {
        AccountService a = new AccountService();
        int ris=a.sandEmail("rocco.99@icloud.com","Testing");
        assertEquals(1,ris);

        ris=a.sandEmail("rocco.99","Testing");
        assertEquals(0,ris);
    }
}