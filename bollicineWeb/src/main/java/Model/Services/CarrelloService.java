package Model.Services;

import Model.DAO.ProdottoDAO;
import Model.DTO.ProdottoDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class CarrelloService {
    ProdottoDAO prodDao=new ProdottoDAO();

    private HashMap<Integer,Integer> map;



    public CarrelloService() {
        map= new HashMap<Integer,Integer>();

    }

    public void addProduct(ProdottoDTO product, int quantita) {
        map.put(product.getIdProdotto(),quantita);

    }

    public void deleteProduct(ProdottoDTO product) {
        map.remove(product.getIdProdotto());
    }

    public boolean getIfExists(ProdottoDTO product) {
        boolean flag = false;

        if(map.containsKey(product.getIdProdotto())){
            flag=true;
        }

        return flag;//se true allora presente
    }

    public List<ProdottoDTO> getProducts() throws SQLException {
        List<Integer> chiavi=new ArrayList<>(map.keySet());
        List<ProdottoDTO> prodotti= new ArrayList<>();

        for (Integer id: chiavi){
                prodotti.add(prodDao.doRetriveById(id));
        }
        return  prodotti;
    }

    public int getQ(ProdottoDTO prod) {

        if(getIfExists(prod)) {
            return map.get(prod.getIdProdotto());
        }else return 0;
    }

    public void updateQ(int id,int quantita) {
        map.replace(id,quantita);

    }

    public void setQ(int id,int quantita) throws SQLException {

        if (map.get(id)+quantita<=prodDao.doRetriveById(id).getDisponibilità()){
            map.replace(id,map.get(id)+quantita);
        }

    }

    public double getTotal() throws SQLException {

        double total=0;
        List<ProdottoDTO> prodotti = getProducts();

        for(ProdottoDTO prod : prodotti) {
            total+=prod.getPrezzo()*map.get(prod.getIdProdotto());
        }

        return total;
    }


    public boolean noProduct() {
        if(map.isEmpty()) {
            return true;
        }
        return false;
    }

}
