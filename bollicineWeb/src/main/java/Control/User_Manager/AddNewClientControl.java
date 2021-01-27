package Control.User_Manager;

import Model.Services.AccountService;
import Model.DTO.AccountDTO;

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

public class AddNewClientControl extends HttpServlet {

    static AccountService bean=new AccountService();
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


            //metto l'account creato nella sessione con stato non confermato
            req.getSession().setAttribute("account", bean.creaAccountDaConfermare(nome,cognome,email,password,stato,tipo));


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

            //se non è trascorso il tempo massimo
            if(oraAtt.isBefore(oraMax)) {


                bean.confermaAccount((AccountDTO) req.getSession().getAttribute("account"));

                //setto i campi da mandare al service per salvare l'account
                String cognome = ((AccountDTO) req.getSession().getAttribute("account")).getCognome();
                String nome = ((AccountDTO) req.getSession().getAttribute("account")).getNome();
                String email = ((AccountDTO) req.getSession().getAttribute("account")).getEmail();
                String password = ((AccountDTO) req.getSession().getAttribute("account")).getPassword();
                String stato = ((AccountDTO) req.getSession().getAttribute("account")).getStato();
                String tipo = ((AccountDTO) req.getSession().getAttribute("account")).getTipo();

                try {
                    //salvo l'account nel db
                    bean.registraAccount(nome,cognome,email,password,stato,tipo);

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
