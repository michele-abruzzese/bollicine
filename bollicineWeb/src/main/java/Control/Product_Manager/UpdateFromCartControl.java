package Control.Product_Manager;

import Model.Services.CarrelloService;
import Model.Services.ProdottoService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class UpdateFromCartControl extends HttpServlet {
    static ProdottoService bean= new ProdottoService();

    public UpdateFromCartControl() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CarrelloService cart = (CarrelloService) req.getSession().getAttribute("cart");

        int id = Integer.parseInt(req.getParameter("id"));
        int quantita = Integer.parseInt(req.getParameter("quantita"));

        try {
            bean.aggiornaQtProdottoDalCarrello(id,quantita,cart);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/View/Carrello/CarrelloView.jsp");
        dispatcher.forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
