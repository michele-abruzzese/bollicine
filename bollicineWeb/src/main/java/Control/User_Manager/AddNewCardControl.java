package Control.User_Manager;

import Model.Beans.AccountBean;
import Model.DAO.CartaCreditoDTO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class AddNewCardControl extends HttpServlet {

    AccountBean account = new AccountBean();
    CartaCreditoDTO carta = new CartaCreditoDTO();

    public AddNewCardControl() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idAccount = Integer.parseInt(req.getParameter("idCliente"));

        //dati della carta
        carta.setNome(req.getParameter("nome"));
        carta.setCognome(req.getParameter("cognome"));
        carta.setNumero(Long.valueOf(req.getParameter("numero")));
        carta.setCcv(Integer.parseInt(req.getParameter("ccv")));
        carta.setScandenza(req.getParameter("scadenza"));
        carta.setIdAccount(idAccount);

        try {
            //se stosalvando la prima carta
            if(account.doSaveCarta(carta,idAccount)==0){

                //dico alla jsp che ho inserito la prima carta
                req.setAttribute("insertFirstCarta",Boolean.TRUE);
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/View/Carrello/CartCheckoutView.jsp");
                dispatcher.forward(req, resp);
            }else{
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
