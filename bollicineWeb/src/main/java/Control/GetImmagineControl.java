package Control;

import Beans.ProdottoBean;
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
    static ProdottoBean bean=new ProdottoBean();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("sono nella servlet");
        int id = Integer.parseInt(request.getParameter("id"));

        if (id != 0)
        {
            System.out.println("sono nell'if");
            byte[] bt=null;
            try {
                bt = bean.doRetriveImgById(id);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }


            ServletOutputStream out = response.getOutputStream();
            if(bt != null)
            {
                System.out.println("ho preso i byte");
                out.write(bt);
                response.setContentType("image/jpeg");
                System.out.println("ho scritto");
            }
            out.close();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
