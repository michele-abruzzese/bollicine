package Model.Beans;

import Model.DAO.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class OrdineBean {
    //DAO ordine
    static OrdineDAO modelOrder= new OrdineDAO();

    //DAO dettaglio ordine
    static DettaglioOrdineDAO modelDett = new DettaglioOrdineDAO();

    //DAO prodotto
    static  ProdottoDAO modelProd= new ProdottoDAO();

    //DAO indirizzi
    static  IndirizzoSpedDAO modelIndi  = new IndirizzoSpedDAO();

    //DAO crate
    static  CartaCreditoDAO modelCar = new CartaCreditoDAO();

    public void doSaveOrder(int idAccount,CarrelloBean cart,int idCarta,int idIndirizzo) throws SQLException, IOException {

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


        //salvo l'ordine
        int idOrdine=modelOrder.doSaveOrdine(ordine);

        //leggo l'iva da file
        FileReader fr = new FileReader("C:\\Users\\Michele\\IdeaProjects\\bollicine\\bollicineWeb\\src\\main\\webapp\\WEB-INF\\iva.txt");
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

            modelDett.doSaveDettaglioOrdine(dettaglio);

            //aggiorno la quatità del prodotto
            modelProd.updateDispo(prod,(prod.getDisponibilità()-cart.getQ(prod)));
        }


    }

    public List<OrdineDTO> doRetriveAll() throws SQLException{
        return modelOrder.doRetriveAll();
    }

    public IndirizzoSpedDTO doRetriveIdirizzo(OrdineDTO ordine) throws SQLException {
        return modelIndi.doRetriveById(ordine.getIdIndirizzo());
    }

    public CartaCreditoDTO doRetriveCarta(OrdineDTO ordine)throws  SQLException{
        return modelCar.doRetriveById(ordine.getIdCarta());
    }
}
