package Model.DAO;

import java.sql.SQLException;
import java.util.List;

public interface IndirizzoSpedDAOIn {
    public int doSaveIndirizzo(IndirizzoSpedDTO indirizzo)throws SQLException;
    public List<IndirizzoSpedDTO> doRetriveByAcount(int idAccount)throws SQLException;
}
