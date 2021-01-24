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

            req.getSession().setAttribute("account", account);
            System.out.println("accountunt prima dell'email: "+account);
            try {
                bean.sandEmail(email, "http://localhost:8080/bollicineSito_war_exploded/AddNewClient?action=1");

                req.getSession().setAttribute("confermaEmail",Boolean.TRUE);
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/View/Login_Logout/LoginView.jsp");
                dispatcher.forward(req, resp);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }else {
            AccountDTO account = new AccountDTO();
            account= (AccountDTO) req.getSession().getAttribute("account");

            account.setStato("confermato");

            System.out.println("accountunt dopo dell'email: "+account);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/View/Login_Logout/LoginView.jsp");
            dispatcher.forward(req, resp);

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
