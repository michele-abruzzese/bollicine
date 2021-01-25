package Model.Beans;

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

public class AccountBean {
    //DAO account
    static AccountDAO model = new AccountDAO();

    //DAO indirizzi
    static IndirizzoSpedDAO modelInd = new IndirizzoSpedDAO();

    //DAO carte
    static CartaCreditoDAO modelCar = new CartaCreditoDAO();

    public int doSaveAcount(AccountDTO ac) throws SQLException {
        return model.doSaveAcount(ac);
    }

    public void removeAccount(int id) throws SQLException{
        model.removeAccount(id);
    }

    public AccountDTO doRetriveById(int id) throws SQLException{
        return model.doRetriveById(id);
    }

    public AccountDTO doRetriveByEmail(String email) throws SQLException{
        return model.doRetriveByEmail(email);
    }

    public int doSaveIndirizzo(IndirizzoSpedDTO indirizzo, int idAccount) throws SQLException{
        List<IndirizzoSpedDTO> indirizzi= modelInd.doRetriveByAcount(idAccount);

        //se sto inserendo il primo indirizzo
        if(indirizzi.size()==0){
            modelInd.doSaveIndirizzo(indirizzo);
            return 0;
        }else{
            modelInd.doSaveIndirizzo(indirizzo);
            return 1;
        }
    }

    public int doSaveCarta(CartaCreditoDTO carta, int idAccount) throws  SQLException{
        List<CartaCreditoDTO> carte = modelCar.doRetriveByAccount(idAccount);

        //se sto inserendo la prima carta
        if (carte.size()==0){
            modelCar.doSaveCartaCredito(carta);
            return 0;
        }else {
            modelCar.doSaveCartaCredito(carta);
            return 1;
        }
    }

    public int controlEmail(String email)throws SQLException{

        return model.controlEmail(email);
    }

    public List<IndirizzoSpedDTO> doRetriveIndirizzi(int idAccount) throws SQLException {
        List<IndirizzoSpedDTO> indirizzi = new ArrayList<>();

        indirizzi=modelInd.doRetriveByAcount(idAccount);

        return indirizzi;
    }

    public List<CartaCreditoDTO> doRetriveCarte(int idAccount) throws SQLException {
        List<CartaCreditoDTO> carte= new ArrayList<>();

        carte=modelCar.doRetriveByAccount(idAccount);

        return carte;
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
