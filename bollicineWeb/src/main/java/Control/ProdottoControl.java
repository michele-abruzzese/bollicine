package Control;

import Beans.CarrelloBean;
import Model.DAO.ProdottoDAO;
import Model.DAO.ProdottoDAOIn;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


public class ProdottoControl extends HttpServlet {

    static ProdottoDAOIn model=new ProdottoDAO();
    public ProdottoControl() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("servlet");
        CarrelloBean cart = (CarrelloBean) req.getSession().getAttribute("cart");
        if(cart == null) {
            cart = new CarrelloBean();
            req.getSession().setAttribute("cart", cart);
        }
        req.removeAttribute("products");
        try {
            req.setAttribute("products", model.doRetriveAll());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/View/CatalogoView.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
