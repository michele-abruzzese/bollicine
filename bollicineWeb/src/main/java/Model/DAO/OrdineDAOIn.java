package Model.DAO;

import java.sql.SQLException;
import java.util.List;

public interface OrdineDAOIn {
    public void doSaveOrdine(OrdineDTO ordine)throws SQLException;
    public List<OrdineDTO> doRetriveAllByAccount(int idAccount)throws SQLException;
    public List<OrdineDTO> doRetriveAll()throws SQLException;
}
