package Control.User_Manager;

import Model.Beans.AccountBean;
import Model.DAO.IndirizzoSpedDTO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class AddNewIndirizzoControl extends HttpServlet {
    AccountBean account = new AccountBean();
    IndirizzoSpedDTO indirizzo = new IndirizzoSpedDTO();

    public AddNewIndirizzoControl() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idAccount= Integer.parseInt(req.getParameter("idCliente"));

        //dati dell'indirizzo
        indirizzo.setNome(req.getParameter("nome"));
        indirizzo.setCognome(req.getParameter("cognome"));
        indirizzo.setIndirizzo(req.getParameter("indirizzo"));
        indirizzo.setCittà(req.getParameter("citta"));
        indirizzo.setCap(Integer.parseInt(req.getParameter("cap")));
        indirizzo.setProvincia(req.getParameter("provincia"));
        indirizzo.setAlias(req.getParameter("alias"));
        indirizzo.setIdAccount(idAccount);

        try {
            //se sto salvando il primo indirizzo
            if(account.doSaveIndirizzo(indirizzo,idAccount)==0){

                //dico alla jsp che ho inserito il primo indirizzo
                req.setAttribute("insertFirstIndirizzo",Boolean.TRUE);
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/View/Carrello/CartCheckoutView.jsp");
                dispatcher.forward(req, resp);
            }else {
                //se l'utente ha già altri indirizzi
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/View/Carrello/CartCheckoutView.jsp");
                dispatcher.forward(req, resp);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
