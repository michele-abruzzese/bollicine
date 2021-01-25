package Control.Order_Manager;

import Model.Beans.OrdineBean;
import Model.DTO.CartaCreditoDTO;
import Model.DTO.IndirizzoSpedDTO;
import Model.DTO.OrdineDTO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VisualizzaOrdiniControl extends HttpServlet {
    static OrdineBean bean = new OrdineBean();

    public VisualizzaOrdiniControl() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<OrdineDTO> ordini = new ArrayList<>();
        List<IndirizzoSpedDTO> indirizzi= new ArrayList<>();
        List<CartaCreditoDTO> carte = new ArrayList<>();

        try {
            ordini=bean.doRetriveAll();

            for(OrdineDTO ordine : ordini){
                indirizzi.add(bean.doRetriveIdirizzo(ordine));
                carte.add(bean.doRetriveCarta(ordine));
            }

            req.setAttribute("ordini",ordini);
            req.setAttribute("indirizzi",indirizzi);
            req.setAttribute("carte",carte);

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
