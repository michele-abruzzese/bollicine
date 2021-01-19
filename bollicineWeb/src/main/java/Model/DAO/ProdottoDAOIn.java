package Model.DAO;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface ProdottoDAOIn {
    public void doSaveProdotto(ProdottoDTO prod) throws SQLException, IOException;
    public List<ProdottoDTO> doRetriveAll()throws SQLException;
    public List<ProdottoDTO> doRetriveByCat()throws SQLException;
}
