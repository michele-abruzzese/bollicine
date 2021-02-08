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

//servlet per aggiungere prodotti al carrello
public class AddInCartControl extends HttpServlet {
    static ProdottoService bean= new ProdottoService();

    public AddInCartControl() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CarrelloService cart = (CarrelloService) req.getSession().getAttribute("cart");
        if(cart == null) {
            cart = new CarrelloService();
            req.getSession().setAttribute("cart", cart);
        }

        int id = Integer.parseInt(req.getParameter("id"));
        int quantita = Integer.parseInt(req.getParameter("quantita"));

        try {
            //mando al bean l'id del prodotto, la quantità da aggiungere e il carrello
            bean.aggiungiProdottoAlCarrello(id,quantita,cart);


            req.getSession().setAttribute("addInCart",Boolean.TRUE);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        req.removeAttribute("id");
        req.removeAttribute("quantita");

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/View/Catalogo/CatalogoView.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
