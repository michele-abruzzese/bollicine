package Model.DAO;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface ProdottoDAOIn {
    public void doSaveProdotto(ProdottoDTO prod) throws SQLException, IOException;
    public List<ProdottoDTO> doRetriveAll()throws SQLException;
    public List<ProdottoDTO> doRetriveByCat(String cat)throws SQLException;
    public byte[] doRetriveImgById(int id)throws SQLException;
    public ProdottoDTO doRetriveById(int id)throws SQLException;
}
