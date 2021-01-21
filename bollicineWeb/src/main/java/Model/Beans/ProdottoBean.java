package Model.Beans;

import Model.DAO.ProdottoDAO;
import Model.DAO.ProdottoDAOIn;
import Model.DAO.ProdottoDTO;
import java.sql.SQLException;
import java.util.List;

public class ProdottoBean {
    static ProdottoDAOIn model=new ProdottoDAO();

    public List<ProdottoDTO> doRetriveAll() throws SQLException {
        return model.doRetriveAll();
    }

    public byte[] doRetriveImgById(int id)throws SQLException {
        return model.doRetriveImgById(id);
    }

    public List<ProdottoDTO> doRetriveByCat(String cat)throws SQLException {
        String categoria=null;

        if(cat.equalsIgnoreCase("bi")){

            categoria="bianchi";
            return model.doRetriveByCat(categoria);

        }else if(cat.equalsIgnoreCase("ro")){

            categoria="rossi";
            return model.doRetriveByCat(categoria);

        }else if(cat.equalsIgnoreCase("spu")){

            categoria="spumanti";
            return model.doRetriveByCat(categoria);
        }

        return null;
    }

    public ProdottoDTO doRetriveById(int id) throws SQLException {
        return model.doRetriveById(id);
    }

    public void addProductInCart(int id, int qt, CarrelloBean cart) throws SQLException {
        ProdottoDTO prod= model.doRetriveById(id);

        if(cart.getIfExists(prod)){
            cart.setQ(id,qt);
        }else cart.addProduct(prod,qt);

    }
}
