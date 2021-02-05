package Model.DAO;

import Model.DTO.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DettaglioOrdineDAOTest {

    @Test
    void doSaveDettaglioOrdine() throws SQLException, IOException {
        ProdottoDTO prod = new ProdottoDTO(0,"Tavernello","Bianco","Vino in cartone","/Users/roccopagliarulo/IdeaProjects/bollicine/bollicineWeb/src/main/webapp/imgs/vino-bianco.jpg","Bianco",2020,1.2,5);
        ProdottoDAO p = new ProdottoDAO();
        int idP=p.doSaveProdotto(prod);

        AccountDTO account = new AccountDTO(0,"Giulio","Costante","alfre@gmail.com","password","confermato","utente");
        AccountDAO a= new AccountDAO();
        int idAc=a.doSaveAcount(account);

        IndirizzoSpedDTO ind = new IndirizzoSpedDTO(0,"Giulio","Costante","Via Roma",84084,"Fisciano","Salerno","ufficio",idAc);
        IndirizzoSpedDAO i=new IndirizzoSpedDAO();
        int idInd =i.doSaveIndirizzo(ind);

        CartaCreditoDTO card = new CartaCreditoDTO(0,"Giulio","Costante",123456L,123,"2020-03-03",idAc);
        CartaCreditoDAO c = new CartaCreditoDAO();
        int idCard=c.doSaveCartaCredito(card);

        OrdineDTO order = new OrdineDTO(0,12.5,"2020-01-01","paypal",idCard,idInd,idAc);
        OrdineDAO o = new OrdineDAO();
        int idOr=o.doSaveOrdine(order);

        DettaglioOrdineDTO dett=new DettaglioOrdineDTO(idP,idOr,2,15,22);
        DettaglioOrdineDAO d = new DettaglioOrdineDAO();
        d.doSaveDettaglioOrdine(dett);

        List<DettaglioOrdineDTO> dettagli = d.doRetriveAll();

        assertEquals(idOr,dettagli.get(dettagli.size()-1).getIdOrdine());

        d.removeDettaglioOrdine(idP,idOr);
        o.removeOrder(idOr);
        i.removeIndirizzo(idInd);
        c.doDelete(idCard);
        a.removeAccount(idAc);
        p.removeProdotto(idP);

    }

    @Test
    void doRetriveAll() throws SQLException, IOException {
        ProdottoDTO prod = new ProdottoDTO(0,"Tavernello","Bianco","Vino in cartone","/Users/roccopagliarulo/IdeaProjects/bollicine/bollicineWeb/src/main/webapp/imgs/vino-bianco.jpg","Bianco",2020,1.2,5);
        ProdottoDAO p = new ProdottoDAO();
        int idP=p.doSaveProdotto(prod);

        AccountDTO account = new AccountDTO(0,"Giulio","Costante","alfre@gmail.com","password","confermato","utente");
        AccountDAO a= new AccountDAO();
        int idAc=a.doSaveAcount(account);

        IndirizzoSpedDTO ind = new IndirizzoSpedDTO(0,"Giulio","Costante","Via Roma",84084,"Fisciano","Salerno","ufficio",idAc);
        IndirizzoSpedDAO i=new IndirizzoSpedDAO();
        int idInd =i.doSaveIndirizzo(ind);

        CartaCreditoDTO card = new CartaCreditoDTO(0,"Giulio","Costante",123456L,123,"2020-03-03",idAc);
        CartaCreditoDAO c = new CartaCreditoDAO();
        int idCard=c.doSaveCartaCredito(card);

        OrdineDTO order = new OrdineDTO(0,12.5,"2020-01-01","paypal",idCard,idInd,idAc);
        OrdineDAO o = new OrdineDAO();
        int idOr=o.doSaveOrdine(order);

        DettaglioOrdineDTO dett=new DettaglioOrdineDTO(idP,idOr,2,15,22);
        DettaglioOrdineDAO d = new DettaglioOrdineDAO();
        List<DettaglioOrdineDTO> dettagli1 = d.doRetriveAll();
        d.doSaveDettaglioOrdine(dett);

        List<DettaglioOrdineDTO> dettagli2 = d.doRetriveAll();

        assertEquals(dettagli1.size()+1,dettagli2.size());

        d.removeDettaglioOrdine(idP,idOr);
        o.removeOrder(idOr);
        i.removeIndirizzo(idInd);
        c.doDelete(idCard);
        a.removeAccount(idAc);
        p.removeProdotto(idP);
    }

    @Test
    void removeDettaglioOrdine() throws IOException, SQLException {
        ProdottoDTO prod = new ProdottoDTO(0,"Tavernello","Bianco","Vino in cartone","/Users/roccopagliarulo/IdeaProjects/bollicine/bollicineWeb/src/main/webapp/imgs/vino-bianco.jpg","Bianco",2020,1.2,5);
        ProdottoDAO p = new ProdottoDAO();
        int idP=p.doSaveProdotto(prod);

        AccountDTO account = new AccountDTO(0,"Giulio","Costante","alfre@gmail.com","password","confermato","utente");
        AccountDAO a= new AccountDAO();
        int idAc=a.doSaveAcount(account);

        IndirizzoSpedDTO ind = new IndirizzoSpedDTO(0,"Giulio","Costante","Via Roma",84084,"Fisciano","Salerno","ufficio",idAc);
        IndirizzoSpedDAO i=new IndirizzoSpedDAO();
        int idInd =i.doSaveIndirizzo(ind);

        CartaCreditoDTO card = new CartaCreditoDTO(0,"Giulio","Costante",123456L,123,"2020-03-03",idAc);
        CartaCreditoDAO c = new CartaCreditoDAO();
        int idCard=c.doSaveCartaCredito(card);

        OrdineDTO order = new OrdineDTO(0,12.5,"2020-01-01","paypal",idCard,idInd,idAc);
        OrdineDAO o = new OrdineDAO();
        int idOr=o.doSaveOrdine(order);

        DettaglioOrdineDTO dett=new DettaglioOrdineDTO(idP,idOr,2,15,22);
        DettaglioOrdineDAO d = new DettaglioOrdineDAO();
        d.doSaveDettaglioOrdine(dett);

        d.removeDettaglioOrdine(idP,idOr);
        o.removeOrder(idOr);
        i.removeIndirizzo(idInd);
        c.doDelete(idCard);
        a.removeAccount(idAc);
        p.removeProdotto(idP);


        List<DettaglioOrdineDTO> dettagli = d.doRetriveAll();

        assertNotEquals(idOr,dettagli.get(dettagli.size()-1).getIdOrdine());
    }
}