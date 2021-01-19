package Control;

import Model.DAO.ProdottoDAO;
import Model.DAO.ProdottoDAOIn;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class GetImmagineControl extends HttpServlet {
    static ProdottoDAOIn model=new ProdottoDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        if (id != 0)
        {
            byte[] bt=null;
            try {
                bt = model.doRetriveImgById(id);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }


            ServletOutputStream out = response.getOutputStream();
            if(bt != null)
            {
                out.write(bt);
                response.setContentType("image/jpeg");
            }
            out.close();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
