package Model.Services;

import Model.DAO.ProdottoDAO;
import Model.DTO.ProdottoDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CarrelloService {
    ProdottoDAO prodDao=new ProdottoDAO();
    private List<ProdottoDTO> products;
    int[] q;



    public CarrelloService() {
        products = new ArrayList<ProdottoDTO>();
        q= new int[200];
    }

    public void addProduct(ProdottoDTO product, int quantita) {
        products.add(product);
        int i=product.getIdProdotto();
        q[i]=quantita;
    }

    public void deleteProduct(ProdottoDTO product) {
        for(ProdottoDTO prod : products) {
            if(prod.getIdProdotto() == product.getIdProdotto()) {
                products.remove(prod);
                break;
            }
        }
    }

    public boolean getIfExists(ProdottoDTO product) {
        boolean flag = false;
        for(ProdottoDTO prod : products) {
            if(product.getIdProdotto()==prod.getIdProdotto()) {
                flag=true;
            }
        }
        return flag;//se true allora presente
    }

    public List<ProdottoDTO> getProducts() {
        return  products;
    }

    public int getQ(ProdottoDTO prod) throws SQLException {
        if(getIfExists(prod)) {
            return q[prod.getIdProdotto()];
        }else return 0;
    }

    public void updateQ(int id,int quantita) {
        q[id]=quantita;
    }

    public void setQ(int id,int quantita) {
        for(ProdottoDTO prod : products) {
            if(prod.getIdProdotto()==id && prod.getDisponibilità()>=q[id]+quantita) {
                q[id]=q[id]+quantita;
            }
        }

    }

    public double getTotal() {

        double total=0;
        for(ProdottoDTO prod : products) {
            total+=prod.getPrezzo()*q[prod.getIdProdotto()];
        }

        return total;
    }

    @Override
    public String toString() {
        return "Cart [products=" + products + ", q=" + Arrays.toString(q) + "]";
    }

    public boolean noProduct() {
        if(products.isEmpty()) {
            return true;
        }
        return false;
    }

}
