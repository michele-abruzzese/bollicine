package Model.Services;

import Model.DAO.*;
import Model.DTO.AccountDTO;
import Model.DTO.CartaCreditoDTO;
import Model.DTO.IndirizzoSpedDTO;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.UnsupportedEncodingException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class AccountService {
    //DAO account
    static AccountDAO accountDao = new AccountDAO();

    //DAO indirizzi
    static IndirizzoSpedDAO indirizzoDao = new IndirizzoSpedDAO();

    //DAO carte
    static CartaCreditoDAO cartaDao = new CartaCreditoDAO();

    public int registraAccount(String nome, String cognome, String email, String password, String stato, String tipo) throws SQLException {
        AccountDTO account = new AccountDTO();
        account.setCognome(cognome);
        account.setNome(nome);
        account.setEmail(email);
        account.setPassword(password);
        account.setStato(stato);
        account.setTipo(tipo);

        return accountDao.doSaveAcount(account);
    }

    public void removeAccount(int id) throws SQLException{
        accountDao.removeAccount(id);
    }

    public AccountDTO doRetriveById(int id) throws SQLException{
        return accountDao.doRetriveById(id);
    }

    public AccountDTO doRetriveByEmail(String email) throws SQLException{
        return accountDao.doRetriveByEmail(email);
    }

    public int salvaIndirizzo(String nome,String cognome,String indirizzo,int cap,String città,String provincia,String alias, int idAccount) throws SQLException{
        IndirizzoSpedDTO indirizzoSped = new IndirizzoSpedDTO();

        indirizzoSped.setIndirizzo(indirizzo);
        indirizzoSped.setNome(nome);
        indirizzoSped.setCognome(cognome);
        indirizzoSped.setCap(cap);
        indirizzoSped.setCittà(città);
        indirizzoSped.setProvincia(provincia);
        indirizzoSped.setAlias(alias);
        indirizzoSped.setIdAccount(idAccount);

        List<IndirizzoSpedDTO> indirizzi= indirizzoDao.doRetriveByAcount(idAccount);

        //se sto inserendo il primo indirizzo
        if(indirizzi.size()==0){
            indirizzoDao.doSaveIndirizzo(indirizzoSped);
            return 0;
        }else{
            indirizzoDao.doSaveIndirizzo(indirizzoSped);
            return 1;
        }
    }

    public int salvaCarta(String nome,String cognome,Long numero,int ccv,String scadenza, int idAccount) throws  SQLException{
        CartaCreditoDTO carta = new CartaCreditoDTO();

        carta.setNome(nome);
        carta.setCognome(cognome);
        carta.setNumero(numero);
        carta.setCcv(ccv);
        carta.setScandenza(scadenza);
        carta.setIdAccount(idAccount);

        List<CartaCreditoDTO> carte = cartaDao.doRetriveByAccount(idAccount);

        //se sto inserendo la prima carta
        if (carte.size()==0){
            cartaDao.doSaveCartaCredito(carta);
            return 0;
        }else {
            cartaDao.doSaveCartaCredito(carta);
            return 1;
        }
    }

    public int controlEmail(String email)throws SQLException{

        return accountDao.controlEmail(email);
    }

    public List<IndirizzoSpedDTO> doRetriveIndirizzi(int idAccount) throws SQLException {
        List<IndirizzoSpedDTO> indirizzi = new ArrayList<>();

        indirizzi= indirizzoDao.doRetriveByAcount(idAccount);

        return indirizzi;
    }

    public List<CartaCreditoDTO> doRetriveCarte(int idAccount) throws SQLException {
        List<CartaCreditoDTO> carte= new ArrayList<>();

        carte= cartaDao.doRetriveByAccount(idAccount);

        return carte;
    }

    public AccountDTO creaAccountDaConfermare(String nome, String cognome, String email, String password,String stato,String tipo) {
        AccountDTO account = new AccountDTO();
        account.setCognome(cognome);
        account.setNome(nome);
        account.setEmail(email);
        account.setPassword(password);
        account.setStato(stato);
        account.setTipo(tipo);

        return account;
    }

    public void confermaAccount(AccountDTO account){
        account.setStato("confermato");
    }

    public void sandEmail(String dest,String testo) throws MessagingException, UnsupportedEncodingException {

        // Sender's email ID needs to be mentioned
        String from = "bollicine.noreply@gmail.com";

        // Assuming you are sending email from through gmails smtp
        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication("bollicine.noreply@gmail.com", "bollicinemail");

            }

        });

        // Used to debug SMTP issues
        //session.setDebug(true);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(dest));

            // Set Subject: header field
            message.setSubject("Conferma email!");

            // Now set the actual message
            message.setText(testo);

            System.out.println("sending...");
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }

    }
}
