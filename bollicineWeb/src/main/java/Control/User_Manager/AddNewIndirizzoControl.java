package Control.User_Manager;

import Model.Services.AccountService;
import Model.DTO.IndirizzoSpedDTO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class AddNewIndirizzoControl extends HttpServlet {
    AccountService account = new AccountService();

    public AddNewIndirizzoControl() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idAccount= Integer.parseInt(req.getParameter("idCliente"));

        //dati dell'indirizzo
        String nome=(req.getParameter("nome"));
        String cognome=(req.getParameter("cognome"));
        String indirizzo=(req.getParameter("indirizzo"));
        String città=(req.getParameter("citta"));
        int cap=(Integer.parseInt(req.getParameter("cap")));
        String provincia=(req.getParameter("provincia"));
        String alis=(req.getParameter("alias"));


        try {
            //se sto salvando il primo indirizzo
            if(account.salvaIndirizzo(nome,cognome,indirizzo,cap,città,provincia,alis,idAccount)==0){
                //reimposto la lista di indirizzi nella sessione
                req.getSession().removeAttribute("indirizzi");
                req.getSession().setAttribute("indirizzi",account.doRetriveIndirizzi(idAccount));

                //dico alla jsp che ho inserito il primo indirizzo
                req.setAttribute("insertFirstIndirizzo",Boolean.TRUE);
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/View/Carrello/CartCheckoutView.jsp");
                dispatcher.forward(req, resp);
            }else {
                //reimposto la lista di indirizzi nella sessione
                req.getSession().removeAttribute("indirizzi");
                req.getSession().setAttribute("indirizzi",account.doRetriveIndirizzi(idAccount));

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
