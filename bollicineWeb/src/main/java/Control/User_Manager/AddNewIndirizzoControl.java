package Control.User_Manager;

import Model.Beans.AccountBean;
import Model.DAO.IndirizzoSpedDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddNewIndirizzoControl extends HttpServlet {
    AccountBean account = new AccountBean();
    IndirizzoSpedDTO indirizzo = new IndirizzoSpedDTO();

    public AddNewIndirizzoControl() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idAccount= Integer.parseInt(req.getParameter("idCliente"));


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
