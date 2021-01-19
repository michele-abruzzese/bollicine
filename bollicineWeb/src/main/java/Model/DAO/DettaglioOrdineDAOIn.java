package Model.DAO;

import java.sql.SQLException;
import java.util.List;

public interface DettaglioOrdineDAOIn {
    public void doSaveDettaglioOrdine(DettaglioOrdineDTO dett)throws SQLException;
    public List<DettaglioOrdineDTO> doRetruveByOrdine(int idOrdine)throws SQLException;
}
