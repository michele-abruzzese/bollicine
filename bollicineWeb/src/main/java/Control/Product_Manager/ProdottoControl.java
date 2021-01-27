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


public class ProdottoControl extends HttpServlet {

    static ProdottoService bean= new ProdottoService();
    public ProdottoControl() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        CarrelloService cart = (CarrelloService) req.getSession().getAttribute("cart");
        if(cart == null) {
            cart = new CarrelloService();
            req.getSession().setAttribute("cart", cart);
        }
        req.removeAttribute("products");
        try {
            req.setAttribute("products", bean.tuttiIProdotti());
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
