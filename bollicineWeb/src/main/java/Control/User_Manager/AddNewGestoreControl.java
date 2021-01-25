package Control.User_Manager;

import Model.Beans.AccountBean;
import Model.DAO.AccountDTO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class AddNewGestoreControl extends HttpServlet {
    static AccountBean bean=new AccountBean();

    public AddNewGestoreControl() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cognome = req.getParameter("cognome");
        String nome = req.getParameter("nome");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String stato = "confermato";
        String tipo = req.getParameter("tipo");

        AccountDTO account = new AccountDTO();
        account.setCognome(cognome);
        account.setNome(nome);
        account.setEmail(email);
        account.setPassword(password);
        account.setStato(stato);
        account.setTipo(tipo);

        try {
            bean.doSaveAcount(account);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        req.getSession().setAttribute("confermato",Boolean.TRUE);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/View/Login_Logout/RegistrazioneGestoriView.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
