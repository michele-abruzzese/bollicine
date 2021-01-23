package Control.Product_Manager;

import Model.Beans.ProdottoBean;
import Model.DAO.ProdottoDTO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class DeleteProductControl extends HttpServlet {
    static ProdottoBean bean = new ProdottoBean();

    public DeleteProductControl() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idPrduct= Integer.parseInt(req.getParameter("id"));



        try {
            bean.deleteProductFromCatalog(idPrduct);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/View/Catalogo/GestioneProdottiView.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
