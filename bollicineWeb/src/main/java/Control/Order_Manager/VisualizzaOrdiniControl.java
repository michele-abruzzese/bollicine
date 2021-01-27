package Control.Order_Manager;

import Model.Services.OrdineService;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


public class VisualizzaOrdiniControl extends HttpServlet {
    static OrdineService bean = new OrdineService();

    public VisualizzaOrdiniControl() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        try {

            req.setAttribute("ordini",bean.tuttiGliOrdini());
            req.setAttribute("indirizzi",bean.indirizziDegliOrdini());
            req.setAttribute("carte",bean.carteDegliOrdini());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/View/Ordine/GestioneOrdiniView.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
