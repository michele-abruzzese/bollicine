package Control.User_Manager;

import Model.Beans.AccountBean;
import Model.DAO.AccountDTO;

import javax.mail.MessagingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

public class AddNewClientControl extends HttpServlet {

    static AccountBean bean=new AccountBean();
    public AddNewClientControl() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int action = Integer.parseInt(req.getParameter("action"));
        if(action==0) {
            String cognome = req.getParameter("cognome");
            String nome = req.getParameter("nome");
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            String stato = "non confermato";
            String tipo = "utente";

            AccountDTO account = new AccountDTO();
            account.setCognome(cognome);
            account.setNome(nome);
            account.setEmail(email);
            account.setPassword(password);
            account.setStato(stato);
            account.setTipo(tipo);

            //metto l'account creato nella sessione con stato non confermato
            req.getSession().setAttribute("account", account);


            try {
                //invio l'email al cliente con il link per confermare l'account
                bean.sandEmail(email, "http://localhost:8080/bollicineSito_war_exploded/AddNewClient?action=1");

                //data e ora dell'invio dell'email
                LocalDateTime oraInvio = LocalDateTime.now();

                //metto nella sessione l'orario massimo nel quale si può confermare
                req.getSession().setAttribute("orarioMax",oraInvio.plus(10, ChronoUnit.MINUTES));

                req.getSession().setAttribute("confermaEmail",Boolean.TRUE);
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/View/Login_Logout/LoginView.jsp");
                dispatcher.forward(req, resp);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }else {

            //prendo dalla sessione l'orario massimo
            LocalDateTime oraMax= (LocalDateTime) req.getSession().getAttribute("orarioMax");

            //prendo l'orario attuale
            LocalDateTime oraAtt=LocalDateTime.now();

            //se non è trrascorso il tempo massimo
            if(oraAtt.isBefore(oraMax)) {

                AccountDTO account = new AccountDTO();
                account = (AccountDTO) req.getSession().getAttribute("account");

                account.setStato("confermato");

                try {
                    //salvo l'account nel db
                    bean.doSaveAcount(account);

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                //rimuovo l'account dalla sessione
                req.getSession().removeAttribute("account");

                req.getSession().setAttribute("confermato",Boolean.TRUE);
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/View/Login_Logout/LoginView.jsp");
                dispatcher.forward(req, resp);

            }else {
                //rimuovo l'account dalla sessione
                req.getSession().removeAttribute("account");

                req.getSession().setAttribute("nonConfermato",Boolean.TRUE);
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/View/Login_Logout/LoginView.jsp");
                dispatcher.forward(req, resp);
            }

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
