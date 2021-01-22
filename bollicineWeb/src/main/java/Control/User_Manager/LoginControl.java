package Control.User_Manager;
import Model.Beans.AccountBean;
import Model.DAO.AccountDTO;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;

/*

import Cliente.ClientBean;
import Cliente.ClientModel;
import Cliente.ClientModelDM;
import dati_anagrafici.Dati_anagraficiBean;
import dati_anagrafici.Dati_anagraficiModel;
import dati_anagrafici.Dati_anagraficiModelDM;
import indirizzo_spedizione.IndirizzoSpedModel;
import indirizzo_spedizione.IndirizzoSpedModelDM;
*/

public class LoginControl extends HttpServlet {

    static AccountBean bean = new AccountBean();

    /*static ClientModel model = new ClientModelDM();
    static Dati_anagraficiModel modelDati=new Dati_anagraficiModelDM();
    static IndirizzoSpedModel modelIndirizzi= new IndirizzoSpedModelDM();*/

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        {

            String email = request.getParameter("email");
            String password = request.getParameter("password");

            String redirectedPage;

            try {
                //se l'utente � amministratore
                if(checkLogin(email, password,request)) {
                    request.getSession().setAttribute("adminRoles", new Boolean(true));
                    redirectedPage = "/protected.jsp";
                    response.sendRedirect(request.getContextPath() + redirectedPage);

                }else{
                    request.getSession().setAttribute("adminRoles", new Boolean(false));
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/View/Catalogo/CatalogoView.jsp");
                    dispatcher.forward(request, response);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Credenziali errate! Riprova","Exception",JOptionPane.INFORMATION_MESSAGE);
                request.setAttribute("invalidAccess", "true");
                RequestDispatcher dispatcher= getServletContext().getRequestDispatcher("/View/Login_Logout/LoginView.jsp");
                dispatcher.forward(request, response);
            }

        }
    }

    private boolean checkLogin(String email, String password,HttpServletRequest request) throws Exception {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO= bean.doRetriveByEmail(email);


        System.out.println(accountDTO.getEmail()+" "+accountDTO.getPassword()+" "+accountDTO.getTipo());

        if (accountDTO.getEmail().equals(email) && accountDTO.getPassword().equals(password) && accountDTO.getTipo().equals("admin")) {



            request.getSession().invalidate();
            return true;


        } else if(accountDTO.getEmail().equals(email) && accountDTO.getPassword().equals(password) && accountDTO.getTipo().equals("utente")){

            /*Dati_anagraficiBean datiCliente=new Dati_anagraficiBean();*/


            /*datiCliente=modelDati.doRetriveByCliente(bean.getIdcliente());*/


            request.getSession().invalidate();
            request.getSession().setAttribute("utente",bean.doRetriveByEmail(email));
            /*request.getSession().setAttribute("datiUtente",datiCliente);
            request.getSession().setAttribute("indirizzi", modelIndirizzi.doRetriveByCliente(bean.getIdcliente()));
*/

            return false;
        }
        throw new Exception("Invalid login and password");

    }

    private static final long serialVersionUID = 1L;

    public LoginControl() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }


}