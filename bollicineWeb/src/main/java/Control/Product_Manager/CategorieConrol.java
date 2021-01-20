package Control.Product_Manager;

import Beans.ProdottoBean;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class CategorieConrol extends HttpServlet {

    static ProdottoBean bean= new ProdottoBean();

    public CategorieConrol() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cat = req.getParameter("cat");

        if(cat!=null) {
            req.removeAttribute("products");
            try {
                req.setAttribute("products", bean.doRetriveByCat(cat));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/View/Catalogo/CatalogoView.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
