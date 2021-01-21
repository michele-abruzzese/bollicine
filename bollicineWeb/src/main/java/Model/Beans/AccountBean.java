package Model.Beans;

import Model.DAO.AccountDAO;
import Model.DAO.AccountDAOIn;
import Model.DAO.AccountDTO;

import java.sql.SQLException;

public class AccountBean {
    static AccountDAOIn model = new AccountDAO();

    public int doSaveAcount(AccountDTO ac) throws SQLException {
        return model.doSaveAcount(ac);
    }

    public void removeAccount(AccountDTO ac) throws SQLException{
        model.removeAccount(ac);
    }

    public AccountDTO doRetriveById(int id) throws SQLException{
        return model.doRetriveById(id);
    }
}
