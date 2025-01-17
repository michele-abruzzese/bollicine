package Control.User_Manager;
import Model.Services.AccountService;
import Model.Services.CarrelloService;
import Model.DTO.AccountDTO;
import Model.DTO.CartaCreditoDTO;
import Model.DTO.IndirizzoSpedDTO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

    static AccountService bean = new AccountService();

    private static final long serialVersionUID = 1L;

    public LoginControl() {
        super();
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            String email = request.getParameter("email");
            String password = request.getParameter("password");

            String redirectedPage;

            try {
                //se l'utente è un admin
                if(checkLogin(email, password,request).equals("admin")) {
                    request.getSession().setAttribute("adminRoles", "admin");
                    RequestDispatcher dispatcher= getServletContext().getRequestDispatcher("/View/Login_Logout/RegistrazioneGestoriView.jsp");
                    dispatcher.forward(request, response);

                }else if(checkLogin(email, password,request).equals("utente")){
                    //se l'utente è un cliente
                    redirectedPage = "/Prodotto";
                    response.sendRedirect(request.getContextPath() + redirectedPage);

                }else if(checkLogin(email, password,request).equals("gestCat")){
                    //se l'utente è un gestore catalogo

                    request.getSession().setAttribute("adminRoles", "gestCat");
                    redirectedPage = "/VisualizzaProdotti";
                    response.sendRedirect(request.getContextPath() + redirectedPage);

                }else if(checkLogin(email, password,request).equals("gestOrd")){
                    //se l'utente è un gestore ordine

                    request.getSession().setAttribute("adminRoles", "gestOrd");
                    redirectedPage = "/VisualizzaOrdini";
                    response.sendRedirect(request.getContextPath() + redirectedPage);

                }
            } catch (Exception e) {
                request.setAttribute("invalidAccess", "true");
                RequestDispatcher dispatcher= getServletContext().getRequestDispatcher("/View/Login_Logout/LoginView.jsp");
                dispatcher.forward(request, response);
            }

    }

    private String checkLogin(String email, String password,HttpServletRequest request) throws Exception {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO= bean.doRetriveByEmail(email);

        if (accountDTO.getEmail().equals(email) && accountDTO.getPassword().equals(password) && accountDTO.getTipo().equals("admin")) {

            request.getSession().invalidate();
            return "admin";


        } else if(accountDTO.getEmail().equals(email) && accountDTO.getPassword().equals(password) && accountDTO.getTipo().equals("utente")){
            //prendo il carrello dalla sessione
            CarrelloService carrello= (CarrelloService) request.getSession().getAttribute("cart");

            //indirizzi di spedizione
            List<IndirizzoSpedDTO> indirizzi = new ArrayList<>();
            indirizzi= bean.doRetriveIndirizzi(accountDTO.getId());

            //carte di credito
            List<CartaCreditoDTO> carte = new ArrayList<>();
            carte= bean.doRetriveCarte(accountDTO.getId());

            request.getSession().invalidate();
            request.getSession().setAttribute("cart",carrello);
            request.getSession().setAttribute("utente",accountDTO);
            request.getSession().setAttribute("indirizzi",indirizzi);
            request.getSession().setAttribute("carte",carte);


            return "utente";

        }else if(accountDTO.getEmail().equals(email) && accountDTO.getPassword().equals(password) && accountDTO.getTipo().equalsIgnoreCase("gestore catalogo")){


            request.getSession().invalidate();

            return "gestCat";

        }else if(accountDTO.getEmail().equals(email) && accountDTO.getPassword().equals(password) && accountDTO.getTipo().equalsIgnoreCase("gestore ordini")){

            request.getSession().invalidate();
            return "gestOrd";
        }


        throw new Exception("Invalid login and password");

    }



    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }


}