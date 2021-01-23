package Model.DAO;

import java.sql.SQLException;
import java.util.List;

public interface CartaCreditoDAOIn {
    public int doSaveCartaCredito(CartaCreditoDTO cc)throws SQLException;
    public List<CartaCreditoDTO> doRetriveByAccount(int idAccount)throws SQLException;
    public CartaCreditoDTO doRetriveById(int idCarta)throws SQLException;
}
