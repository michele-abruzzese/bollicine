package Model.DAO;

import java.sql.SQLException;

public interface AccountDAOIn {
    public int doSaveAcount(AccountDTO ac) throws SQLException;
    public void removeAccount(int id) throws SQLException;
    public AccountDTO doRetriveById(int id) throws SQLException;
    public AccountDTO doRetriveByEmail(String email) throws SQLException;
    public int controlEmail(String email)throws SQLException;
}
