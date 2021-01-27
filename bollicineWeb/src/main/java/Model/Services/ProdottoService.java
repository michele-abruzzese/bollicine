package Model.Services;

import Model.DAO.ProdottoDAO;
import Model.DTO.ProdottoDTO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ProdottoService {
    static ProdottoDAO prodottoDAO =new ProdottoDAO();

    public List<ProdottoDTO> tuttiIProdotti() throws SQLException {
        return prodottoDAO.doRetriveAll();
    }

    public byte[] immaginePerId(int id)throws SQLException {
        return prodottoDAO.doRetriveImgById(id);
    }

    public List<ProdottoDTO> prodottiPerCategoria(String cat)throws SQLException {
        String categoria=null;

        if(cat.equalsIgnoreCase("bi")){

            categoria="bianchi";
            return prodottoDAO.doRetriveByCat(categoria);

        }else if(cat.equalsIgnoreCase("ro")){

            categoria="rossi";
            return prodottoDAO.doRetriveByCat(categoria);

        }else if(cat.equalsIgnoreCase("spu")){

            categoria="spumanti";
            return prodottoDAO.doRetriveByCat(categoria);
        }

        return null;
    }

    public ProdottoDTO prodottoPerId(int id) throws SQLException {
        return prodottoDAO.doRetriveById(id);
    }

    public void aggiungiProdottoAlCarrello(int id, int qt, CarrelloService cart) throws SQLException {
        ProdottoDTO prod= prodottoDAO.doRetriveById(id);

        if(cart.getIfExists(prod)){
            cart.setQ(id,qt);
        }else cart.addProduct(prod,qt);

    }

    public void rimuoviProdottoDalCarrello(int id, CarrelloService cart)throws SQLException {
        ProdottoDTO prod= prodottoDAO.doRetriveById(id);

        cart.deleteProduct(prod);
    }

    public void aggiornaQtProdottoDalCarrello(int id, int qt, CarrelloService cart)throws SQLException{
        cart.updateQ(id,qt);
    }

    public void inserisciProdottoNelCatalogo(String nome,String categoria, String descrizione,String immagine,String tipo,int annata,double prezzo,int disponibilità) throws SQLException, IOException {
        ProdottoDTO prod = new ProdottoDTO();

        prod.setNome(nome);
        prod.setCategoria(categoria);
        prod.setDescrizione(descrizione);
        prod.setTipo(tipo);
        prod.setAnnata(annata);
        prod.setPrezzo(prezzo);
        prod.setDisponibilità(disponibilità);
        prod.setImmagine(immagine);

        prodottoDAO.doSaveProdotto(prod);
    }

    public void aggiornaProdotto(int idProdotto,String nome,String categoria, String descrizione,String immagine,String tipo,int annata,double prezzo,int disponibilità)throws SQLException, IOException {
        ProdottoDTO prod = new ProdottoDTO();

        prod.setIdProdotto(idProdotto);
        prod.setNome(nome);
        prod.setCategoria(categoria);
        prod.setDescrizione(descrizione);
        prod.setTipo(tipo);
        prod.setAnnata(annata);
        prod.setPrezzo(prezzo);
        prod.setDisponibilità(disponibilità);
        prod.setImmagine(immagine);
        prodottoDAO.doUpdateProdotto(prod);
    }

    public void rimuoviProdottoDalCatalogo(int idProdotto)throws  SQLException {
        prodottoDAO.updateDispo(prodottoDAO.doRetriveById(idProdotto),0 );
    }
}
