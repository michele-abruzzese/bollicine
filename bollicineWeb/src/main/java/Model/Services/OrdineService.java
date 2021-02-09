package Model.Services;

import Model.DAO.*;
import Model.DTO.*;

import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrdineService {
    //DAO ordine
    static OrdineDAO ordineDAO = new OrdineDAO();

    //DAO dettaglio ordine
    static DettaglioOrdineDAO dettaglioOrdineDAO = new DettaglioOrdineDAO();

    //DAO prodotto
    static  ProdottoDAO prodottoDAO = new ProdottoDAO();

    //DAO indirizzi
    static  IndirizzoSpedDAO indirizzoSpedDAO = new IndirizzoSpedDAO();

    //DAO crate
    static  CartaCreditoDAO cartaCreditoDAO = new CartaCreditoDAO();

    public int salvaOdine(int idAccount, CarrelloService cart, int idCarta, int idIndirizzo) throws IOException, SQLException {

        List <CartaCreditoDTO> carteDiAccount = cartaCreditoDAO.doRetriveByAccount(idAccount);
        List <IndirizzoSpedDTO> indirizziDiAccount = indirizzoSpedDAO.doRetriveByAcount(idAccount);

        LocalDate dataScadenza= LocalDate.parse(cartaCreditoDAO.doRetriveById(idCarta).getScandenza());
        //se è scaduta la carta
        if(dataScadenza.isBefore(LocalDate.now()) || dataScadenza.isEqual(LocalDate.now())) {
            return 0;
        }

        //prendo i prodotti dal carrello
        List<ProdottoDTO> prodotti = cart.getProducts();

        //dati dell'ordine
        OrdineDTO ordine = new OrdineDTO();
        ordine.setIdAccount(idAccount);
        ordine.setTotOrdine((float) cart.getTotal());
        ordine.setData(LocalDate.now().toString());
        ordine.setMetodoPag("carta");
        ordine.setIdIndirizzo(idIndirizzo);
        ordine.setIdCarta(idCarta);

        int idOrdine=-1;
        if(carteDiAccount.contains(cartaCreditoDAO.doRetriveById(idCarta)) && indirizziDiAccount.contains(indirizzoSpedDAO.doRetriveById(idIndirizzo))) {
            //salvo l'ordine
            idOrdine= ordineDAO.doSaveOrdine(ordine);
        }


        //leggo l'iva da file
        FileReader fr = new FileReader("src/main/webapp/WEB-INF/iva.txt");
        int next = fr.read();
        String iva = "";
        while(next != -1) {
            char nextc = (char) next;
            iva+=nextc;

            next = fr.read();
        }
        fr.close();

        //dati dettaglio ordine

        DettaglioOrdineDTO dettaglio = new DettaglioOrdineDTO();
        for(ProdottoDTO prod : prodotti){
            dettaglio.setIva(Integer.parseInt(iva));
            dettaglio.setIdPodotto(prod.getIdProdotto());
            dettaglio.setIdOrdine(idOrdine);
            dettaglio.setQuantità(cart.getQ(prod));
            dettaglio.setPrezzoUnit(prod.getPrezzo());

            dettaglioOrdineDAO.doSaveDettaglioOrdine(dettaglio);

            //aggiorno la quatità del prodotto
            prodottoDAO.updateDispo(prod,(prod.getDisponibilità()-cart.getQ(prod)));
        }

        return 1;

    }

    public List<OrdineDTO> tuttiGliOrdini() throws SQLException{
        return ordineDAO.doRetriveAll();
    }


    public List<IndirizzoSpedDTO> indirizziDegliOrdini() throws SQLException {
        List<IndirizzoSpedDTO> indirizzi = new ArrayList<>();

        List<OrdineDTO> ordini=ordineDAO.doRetriveAll();

        for (OrdineDTO ordine : ordini ){
            indirizzi.add(indirizzoSpedDAO.doRetriveById(ordine.getIdIndirizzo()));
        }

        return indirizzi;
    }

    public List<CartaCreditoDTO> carteDegliOrdini() throws SQLException {
        List<CartaCreditoDTO> carte= new ArrayList<>();

        List<OrdineDTO> ordini=ordineDAO.doRetriveAll();

        for(OrdineDTO ordine : ordini){
            carte.add(cartaCreditoDAO.doRetriveById(ordine.getIdCarta()));
        }

        return carte;
    }
}
