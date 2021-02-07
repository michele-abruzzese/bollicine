package Control.Order_Manager;

import static org.junit.jupiter.api.Assertions.assertEquals;


import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import Model.DAO.*;
import Model.DTO.*;
import Model.Services.CarrelloService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockServletConfig;


class AddNewOrdineControlTest {
    private AddNewOrdineControl servlet;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private MockHttpSession session;

    @BeforeEach
    public void setUp() {
        servlet = new AddNewOrdineControl();
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        request.setSession(session);

    }

    @BeforeEach
    public void oneWaySetup() throws ServletException {
        ServletConfig sg = new MockServletConfig();
        servlet.init(sg);
    }

    @Test
    public void TestAddNewOrdineConCartaValida() throws SQLException, IOException, ServletException {
        AccountDTO account = new AccountDTO(0,"Giulio","Costante","alfre@gmail.com","password","confermato","utente");
        AccountDAO a= new AccountDAO();
        int idAc=a.doSaveAcount(account);

        IndirizzoSpedDTO ind = new IndirizzoSpedDTO(0,"Giulio","Costante","Via Roma",84084,"Fisciano","Salerno","ufficio",idAc);
        IndirizzoSpedDAO i=new IndirizzoSpedDAO();
        int idInd =i.doSaveIndirizzo(ind);

        CartaCreditoDTO card = new CartaCreditoDTO(0,"Giulio","Costante",123456L,123,"2021-03-03",idAc);
        CartaCreditoDAO c = new CartaCreditoDAO();
        int idCard=c.doSaveCartaCredito(card);

        ProdottoDTO prod = new ProdottoDTO(0,"Tavernello","Bianco","Vino in cartone","src/main/webapp/imgs/vino-bianco.jpg","Bianco",2020,1.2,5);
        ProdottoDAO p = new ProdottoDAO();
        int idP=p.doSaveProdotto(prod);

        CarrelloService cart = new CarrelloService();
        cart.addProduct(p.doRetriveById(idP),2);

        request.getSession().setAttribute("cart",cart);
        request.setParameter("idCarta", String.valueOf(idCard));
        request.setParameter("idIndirizzo", String.valueOf(idInd));
        request.getSession().setAttribute("utente",a.doRetriveById(idAc));

        servlet.doPost(request,response);

        assertEquals(true , request.getSession().getAttribute("ordineOk"));

        DettaglioOrdineDAO dett = new DettaglioOrdineDAO();
        dett.removeDettaglioOrdine(idP,dett.doRetriveAll().get(dett.doRetriveAll().size()-1).getIdOrdine());
        OrdineDAO o = new OrdineDAO();
        o.removeOrder(o.doRetriveAll().get(o.doRetriveAll().size()-1).getIdOrdine());
        i.removeIndirizzo(idInd);
        c.doDelete(idCard);
        a.removeAccount(idAc);
        p.removeProdotto(idP);
    }

    @Test
    public void TestAddNewOrdineConCartaNonValida() throws SQLException, IOException, ServletException {
        AccountDTO account = new AccountDTO(0,"Giulio","Costante","alfre@gmail.com","password","confermato","utente");
        AccountDAO a= new AccountDAO();
        int idAc=a.doSaveAcount(account);

        IndirizzoSpedDTO ind = new IndirizzoSpedDTO(0,"Giulio","Costante","Via Roma",84084,"Fisciano","Salerno","ufficio",idAc);
        IndirizzoSpedDAO i=new IndirizzoSpedDAO();
        int idInd =i.doSaveIndirizzo(ind);

        CartaCreditoDTO card = new CartaCreditoDTO(0,"Giulio","Costante",123456L,123,"2020-03-03",idAc);
        CartaCreditoDAO c = new CartaCreditoDAO();
        int idCard=c.doSaveCartaCredito(card);

        ProdottoDTO prod = new ProdottoDTO(0,"Tavernello","Bianco","Vino in cartone","src/main/webapp/imgs/vino-bianco.jpg","Bianco",2020,1.2,5);
        ProdottoDAO p = new ProdottoDAO();
        int idP=p.doSaveProdotto(prod);

        CarrelloService cart = new CarrelloService();
        cart.addProduct(p.doRetriveById(idP),2);

        request.getSession().setAttribute("cart",cart);
        request.setParameter("idCarta", String.valueOf(idCard));
        request.setParameter("idIndirizzo", String.valueOf(idInd));
        request.getSession().setAttribute("utente",a.doRetriveById(idAc));

        servlet.doPost(request,response);

        assertEquals(true , request.getSession().getAttribute("ordineNone"));


        i.removeIndirizzo(idInd);
        c.doDelete(idCard);
        a.removeAccount(idAc);
        p.removeProdotto(idP);
    }
}