package Control.Order_Manager;

import Model.Beans.CarrelloBean;
import Model.Beans.OrdineBean;
import Model.DAO.AccountDTO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class AddNewOrdineControl extends HttpServlet {
    static OrdineBean bean = new OrdineBean();

    public AddNewOrdineControl() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AccountDTO cliente= (AccountDTO) req.getSession().getAttribute("utente");
        CarrelloBean carrello= (CarrelloBean) req.getSession().getAttribute("cart");
        int idCarta = (int) req.getSession().getAttribute("idCarta");
        int idIndirizzo= (int) req.getSession().getAttribute("idIndirizzo");

        try {
            bean.doSaveOrder(cliente.getId(),carrello,idCarta,idIndirizzo);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        //rimuovo il carrello dalla sessione
        req.getSession().removeAttribute("cart");

        //dico alla jsp che l'ordine è stato salvato
        req.setAttribute("orderOk",Boolean.TRUE);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/View/Catalogo/CatalogoView.jsp");
        dispatcher.forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
