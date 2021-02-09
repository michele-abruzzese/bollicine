package Model.Services;

import Model.DAO.*;
import Model.DTO.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Null;
import org.springframework.core.OrderComparator;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

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

        ProdottoDTO prod = new ProdottoDTO(0,"Tavernello","Bianco","Vino in cartone","src/main/webapp/imgs/vino-bianco.jpg","Bianco",2020,22,5);
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

        ProdottoDTO prod = new ProdottoDTO(0,"Tavernello","Bianco","Vino in cartone","src/main/webapp/imgs/vino-bianco.jpg","Bianco",2020,22,5);
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

    /*@Test(expected = MctException.class)
    public void testGetPlacementByIdFail() throws MctException, SQLException {
        when(placementDao.getPlacementById(15)).thenThrow(SQLException.class);
        placementService.getPlacementById(15);
    }*/

    @Test
    void salvaOrdineWE1() {
        OrdineService o = new OrdineService();

        Assertions.assertThrows(NullPointerException.class, () -> {
            o.salvaOdine(-1, null,-1,-1);
        });
    }

    @Test
    void salvaOrdineWE2() throws SQLException {
        AccountDTO account = new AccountDTO(0,"Giulio","Costante","alfre@gmail.com","password","confermato","utente");
        AccountDAO a= new AccountDAO();
        int idAc=a.doSaveAcount(account);


        IndirizzoSpedDTO ind = new IndirizzoSpedDTO(0,"Giulio","Costante","Via Roma",84084,"Fisciano","Salerno","ufficio",idAc);
        IndirizzoSpedDAO i=new IndirizzoSpedDAO();
        int idInd =i.doSaveIndirizzo(ind);



        CartaCreditoDTO card = new CartaCreditoDTO(0,"Giulio","Costante",123456L,123,"2021-07-12",idAc);
        CartaCreditoDAO c = new CartaCreditoDAO();
        int idCard=c.doSaveCartaCredito(card);


        a.removeAccount(idAc);
        i.removeIndirizzo(idInd);
        c.doDelete(idCard);


        CarrelloService cart = new CarrelloService();

        OrdineService o = new OrdineService();

        Assertions.assertThrows(NullPointerException.class, () -> {
            o.salvaOdine(idAc, cart,idCard,idInd);
        });
    }

    @Test
    void salvaOrdineWE3() throws SQLException, IOException {
        AccountDTO account = new AccountDTO(0,"Giulio","Costante","giu@gmail.com","password","confermato","utente");
        AccountDAO a= new AccountDAO();
        int idAc=a.doSaveAcount(account);

        AccountDTO ac2 = new AccountDTO(0,"Alfredo","Cornacchia","alfre@gmail.com","password","condermato","utente");
        int idAc2=a.doSaveAcount(ac2);

        ProdottoDTO prod = new ProdottoDTO(0,"Tavernello","Bianco","Vino in cartone","src/main/webapp/imgs/vino-bianco.jpg","Bianco",2020,22,5);
        ProdottoDAO p = new ProdottoDAO();
        int idP=p.doSaveProdotto(prod);

        CarrelloService cart = new CarrelloService();
        cart.addProduct(prod,2);

        CartaCreditoDTO card = new CartaCreditoDTO(0,"Giulio","Costante",123456L,123,"2021-07-12",idAc);
        CartaCreditoDAO c = new CartaCreditoDAO();
        int idCard=c.doSaveCartaCredito(card);

        IndirizzoSpedDTO ind = new IndirizzoSpedDTO(0,"Giulio","Costante","Via Roma",84084,"Fisciano","Salerno","ufficio",idAc2);
        IndirizzoSpedDAO i=new IndirizzoSpedDAO();
        int idInd=i.doSaveIndirizzo(ind);

        OrdineService o = new OrdineService();

        Assertions.assertThrows(SQLException.class, () -> {
            o.salvaOdine(idAc,cart,idCard,idInd);
        });



        OrdineDAO or = new OrdineDAO();
        or.removeOrder(or.doRetriveAll().get(or.doRetriveAll().size()-1).getIdOrdine());
        p.removeProdotto(idP);
        a.removeAccount(idAc);
        a.removeAccount(idAc2);

    }
}