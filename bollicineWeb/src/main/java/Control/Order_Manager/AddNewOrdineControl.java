package Control.Order_Manager;

import Model.DTO.AccountDTO;
import Model.Services.CarrelloService;
import Model.Services.OrdineService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class AddNewOrdineControl extends HttpServlet {
    static OrdineService bean = new OrdineService();

    public AddNewOrdineControl() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        CarrelloService carrello= (CarrelloService) req.getSession().getAttribute("cart");
        int idCarta = Integer.parseInt(req.getParameter("idCarta"));
        int idIndirizzo= Integer.parseInt(req.getParameter("idIndirizzo"));

        int result=-1;
        try {
            result=bean.salvaOdine(((AccountDTO) req.getSession().getAttribute("utente")).getId(),carrello,idCarta,idIndirizzo);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        if(result==1) {
            //rimuovo il carrello dalla sessione
            req.getSession().removeAttribute("cart");

            //dico alla jsp che l'ordine è stato salvato

            req.getSession().setAttribute("ordineOk", Boolean.TRUE);

            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/View/Catalogo/CatalogoView.jsp");
            dispatcher.forward(req, resp);
        }else if(result==0) {

            //dico alla jsp che l'ordine non è stato salvato
            req.getSession().setAttribute("ordineNone", Boolean.TRUE);

            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/View/Catalogo/CatalogoView.jsp");
            dispatcher.forward(req, resp);
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
