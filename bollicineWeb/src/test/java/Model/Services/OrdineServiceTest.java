package Model.Services;

import Model.DAO.*;
import Model.DTO.*;
import org.junit.jupiter.api.Test;
import org.springframework.core.OrderComparator;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrdineServiceTest {

    @Test
    void salvaOdine() throws SQLException, IOException {
        AccountDTO account = new AccountDTO(0,"Giulio","Costante","alfre@gmail.com","password","confermato","utente");
        AccountDAO a= new AccountDAO();
        int idAc=a.doSaveAcount(account);

        IndirizzoSpedDTO ind = new IndirizzoSpedDTO(0,"Giulio","Costante","Via Roma",84084,"Fisciano","Salerno","ufficio",idAc);
        IndirizzoSpedDAO i=new IndirizzoSpedDAO();
        int idInd =i.doSaveIndirizzo(ind);

        CartaCreditoDTO card = new CartaCreditoDTO(0,"Giulio","Costante",123456L,123,"2021-07-12",idAc);
        CartaCreditoDAO c = new CartaCreditoDAO();
        int idCard=c.doSaveCartaCredito(card);

        ProdottoDTO prod = new ProdottoDTO(0,"Tavernello","Bianco","Vino in cartone","/Users/roccopagliarulo/IdeaProjects/bollicine/bollicineWeb/src/main/webapp/imgs/vino-bianco.jpg","Bianco",2020,22,5);
        ProdottoDAO p = new ProdottoDAO();
        int idP=p.doSaveProdotto(prod);

        DettaglioOrdineDAO dett = new DettaglioOrdineDAO();

        OrdineService o = new OrdineService();
        CarrelloService cart = new CarrelloService();
        cart.addProduct(p.doRetriveById(idP),2);

        int ris=o.salvaOdine(idAc,cart,idCard,idInd);

        assertEquals(1,ris);

        List<OrdineDTO> orders = o.tuttiGliOrdini();
        OrdineDAO ordDao = new OrdineDAO();

        dett.removeDettaglioOrdine(idP,orders.get(orders.size()-1).getIdOrdine());
        ordDao.removeOrder(orders.get(orders.size()-1).getIdOrdine());
        i.removeIndirizzo(idInd);
        c.doDelete(idCard);
        a.removeAccount(idAc);
        p.removeProdotto(idP);

    }

    @Test
    void SalvaOrdineCardScaduta() throws SQLException, IOException {
        AccountDTO account = new AccountDTO(0,"Giulio","Costante","alfre@gmail.com","password","confermato","utente");
        AccountDAO a= new AccountDAO();
        int idAc=a.doSaveAcount(account);

        IndirizzoSpedDTO ind = new IndirizzoSpedDTO(0,"Giulio","Costante","Via Roma",84084,"Fisciano","Salerno","ufficio",idAc);
        IndirizzoSpedDAO i=new IndirizzoSpedDAO();
        int idInd =i.doSaveIndirizzo(ind);

        CartaCreditoDTO card = new CartaCreditoDTO(0,"Giulio","Costante",123456L,123,"2020-07-12",idAc);
        CartaCreditoDAO c = new CartaCreditoDAO();
        int idCard=c.doSaveCartaCredito(card);

        ProdottoDTO prod = new ProdottoDTO(0,"Tavernello","Bianco","Vino in cartone","/Users/roccopagliarulo/IdeaProjects/bollicine/bollicineWeb/src/main/webapp/imgs/vino-bianco.jpg","Bianco",2020,22,5);
        ProdottoDAO p = new ProdottoDAO();
        int idP=p.doSaveProdotto(prod);

        OrdineService o = new OrdineService();
        CarrelloService cart = new CarrelloService();
        cart.addProduct(p.doRetriveById(idP),2);

        int ris=o.salvaOdine(idAc,cart,idCard,idInd);

        assertEquals(0,ris);

        i.removeIndirizzo(idInd);
        c.doDelete(idCard);
        a.removeAccount(idAc);
        p.removeProdotto(idP);

    }

    @Test
    void tuttiGliOrdini() throws SQLException {
        OrdineService o = new OrdineService();
        List<OrdineDTO> orders = o.tuttiGliOrdini();
        assertNotNull(orders);
    }

    @Test
    void indirizziDegliOrdini() throws SQLException {
        OrdineService o = new OrdineService();
        List<IndirizzoSpedDTO> indirizzi = o.indirizziDegliOrdini();
        assertNotNull(indirizzi);
    }

    @Test
    void carteDegliOrdini() throws SQLException {
        OrdineService o = new OrdineService();
        List<CartaCreditoDTO> cards = o.carteDegliOrdini();
        assertNotNull(cards);
    }
}