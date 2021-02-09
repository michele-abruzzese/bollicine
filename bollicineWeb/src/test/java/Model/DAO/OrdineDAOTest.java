package Model.DAO;

import Model.DTO.AccountDTO;
import Model.DTO.CartaCreditoDTO;
import Model.DTO.IndirizzoSpedDTO;
import Model.DTO.OrdineDTO;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrdineDAOTest {

    @Test
    void doSaveOrdine() throws SQLException {


        AccountDTO account = new AccountDTO(0,"Giulio","Costante","alfre@gmail.com","password","confermato","utente");
        AccountDAO a= new AccountDAO();
        int idAc=a.doSaveAcount(account);

        IndirizzoSpedDTO ind = new IndirizzoSpedDTO(0,"Giulio","Costante","Via Roma",84084,"Fisciano","Salerno","ufficio",idAc);
        IndirizzoSpedDAO i=new IndirizzoSpedDAO();
        int idInd =i.doSaveIndirizzo(ind);

        CartaCreditoDTO card = new CartaCreditoDTO(0,"Giulio","Costante",123456L,123,"2020-03-03",idAc);
        CartaCreditoDAO c = new CartaCreditoDAO();
        int idCard=c.doSaveCartaCredito(card);

        OrdineDTO order = new OrdineDTO(0,12.5,"2021-01-01","paypal",idCard,idInd,idAc);
        OrdineDAO o = new OrdineDAO();
        int idOr=o.doSaveOrdine(order);
        order.setIdOrdine(idOr);

        List<OrdineDTO> orders = o.doRetriveAll();

        assertEquals(order,orders.get(orders.size()-1));

        o.removeOrder(idOr);
        i.removeIndirizzo(idInd);
        c.doDelete(idCard);
        a.removeAccount(idAc);


    }

    @Test
    void doRetriveAll() throws SQLException {
        AccountDTO account = new AccountDTO(0,"Giulio","Costante","alfre@gmail.com","password","confermato","utente");
        AccountDAO a= new AccountDAO();
        int idAc=a.doSaveAcount(account);

        IndirizzoSpedDTO ind = new IndirizzoSpedDTO(0,"Giulio","Costante","Via Roma",84084,"Fisciano","Salerno","ufficio",idAc);
        IndirizzoSpedDAO i=new IndirizzoSpedDAO();
        int idInd =i.doSaveIndirizzo(ind);

        CartaCreditoDTO card = new CartaCreditoDTO(0,"Giulio","Costante",123456L,123,"2020-03-03",6);
        CartaCreditoDAO c = new CartaCreditoDAO();
        int idCard=c.doSaveCartaCredito(card);

        OrdineDTO order = new OrdineDTO(0,12.5,"2020-01-01","paypal",idCard,idInd,idAc);
        OrdineDAO o = new OrdineDAO();
        int idOr=o.doSaveOrdine(order);
        order.setIdOrdine(idOr);

        List<OrdineDTO> orders = o.doRetriveAll();

        assertEquals(order,orders.get(orders.size()-1));



        o.removeOrder(idOr);
        i.removeIndirizzo(idInd);
        c.doDelete(idCard);
        a.removeAccount(idAc);
    }

    @Test
    void removeOrder() throws SQLException {
        AccountDTO account = new AccountDTO(0,"Giulio","Costante","alfre@gmail.com","password","confermato","utente");
        AccountDAO a= new AccountDAO();
        int idAc=a.doSaveAcount(account);

        IndirizzoSpedDTO ind = new IndirizzoSpedDTO(0,"Giulio","Costante","Via Roma",84084,"Fisciano","Salerno","ufficio",idAc);
        IndirizzoSpedDAO i=new IndirizzoSpedDAO();
        int idInd =i.doSaveIndirizzo(ind);

        CartaCreditoDTO card = new CartaCreditoDTO(0,"Giulio","Costante",123456L,123,"2020-03-03",6);
        CartaCreditoDAO c = new CartaCreditoDAO();
        int idCard=c.doSaveCartaCredito(card);

        OrdineDTO order = new OrdineDTO(0,12.5,"2020-01-01","paypal",idCard,idInd,idAc);
        OrdineDAO o = new OrdineDAO();
        int idOr=o.doSaveOrdine(order);

        List<OrdineDTO> orders = o.doRetriveAll();

        o.removeOrder(idOr);
        i.removeIndirizzo(idInd);
        c.doDelete(idCard);
        a.removeAccount(idAc);

        List<OrdineDTO> orders2 = o.doRetriveAll();

        assertEquals(orders.size()-1,orders2.size());

    }
}