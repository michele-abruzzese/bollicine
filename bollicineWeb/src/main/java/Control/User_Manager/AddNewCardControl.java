package Control.User_Manager;

import Model.Services.AccountService;
import Model.DTO.CartaCreditoDTO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class AddNewCardControl extends HttpServlet {

    AccountService account = new AccountService();

    public AddNewCardControl() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idAccount = Integer.parseInt(req.getParameter("idCliente"));

        //dati della carta
        String nome=(req.getParameter("nome"));
        String cognome=(req.getParameter("cognome"));
        Long numero=(Long.valueOf(req.getParameter("numero")));
        int ccv=(Integer.parseInt(req.getParameter("ccv")));
        String scadenza=(req.getParameter("scadenza"));

        try {
            //se stosalvando la prima carta
            if(account.salvaCarta(nome,cognome,numero,ccv,scadenza,idAccount)==0){
                //reimposto la lista di carte nella sessione
                req.getSession().removeAttribute("carte");
                req.getSession().setAttribute("carte",account.doRetriveCarte(idAccount));

                //dico alla jsp che ho inserito la prima carta
                req.setAttribute("insertFirstCarta",Boolean.TRUE);
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/View/Carrello/CartCheckoutView.jsp");
                dispatcher.forward(req, resp);
            }else{
                //reimposto la lista di carte nella sessione
                req.getSession().removeAttribute("carte");
                req.getSession().setAttribute("carte",account.doRetriveCarte(idAccount));

                //se l'utente ha altre carte
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
