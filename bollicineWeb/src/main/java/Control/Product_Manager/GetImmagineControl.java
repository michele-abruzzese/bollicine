package Control.Product_Manager;

import Model.Services.ProdottoService;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class GetImmagineControl extends HttpServlet {
    static ProdottoService bean=new ProdottoService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        if (id != 0)
        {

            byte[] bt=null;
            try {
                bt = bean.immaginePerId(id);
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
