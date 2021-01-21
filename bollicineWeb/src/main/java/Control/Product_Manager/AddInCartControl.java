package Control.Product_Manager;

import Model.Beans.CarrelloBean;
import Model.Beans.ProdottoBean;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

//servlet per aggiungere prodotti al carrello
public class AddInCartControl extends HttpServlet {
    static ProdottoBean bean= new ProdottoBean();

    public AddInCartControl() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CarrelloBean cart = (CarrelloBean) req.getSession().getAttribute("cart");
        if(cart == null) {
            cart = new CarrelloBean();
            req.getSession().setAttribute("cart", cart);
        }

        int id = Integer.parseInt(req.getParameter("id"));
        int quantita = Integer.parseInt(req.getParameter("quantita"));

        try {
            //mando al bean l'id del prodotto, la quantità da aggiungere e il carrello
            bean.addProductInCart(id,quantita,cart);

            req.removeAttribute("products");
            req.setAttribute("products",bean.doRetriveAll());
            req.setAttribute("inserito",Boolean.TRUE);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/View/Catalogo/CatalogoView.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
