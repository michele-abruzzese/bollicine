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

public class DeteleFomCartControl extends HttpServlet {
    static ProdottoBean bean= new ProdottoBean();

    public DeteleFomCartControl() {
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

        try {
            bean.deleteProductFromCart(id,cart);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        req.removeAttribute("id");

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/View/Carrello/CarrelloView.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
