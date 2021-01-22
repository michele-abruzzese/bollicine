package Model.Beans;

import Model.DAO.*;

import java.sql.SQLException;
import java.util.List;

public class AccountBean {
    //DAO account
    static AccountDAOIn model = new AccountDAO();

    //DAO indirizzi
    static IndirizzoSpedDAOIn modelInd = new IndirizzoSpedDAO();

    public int doSaveAcount(AccountDTO ac) throws SQLException {
        return model.doSaveAcount(ac);
    }

    public void removeAccount(AccountDTO ac) throws SQLException{
        model.removeAccount(ac);
    }

    public AccountDTO doRetriveById(int id) throws SQLException{
        return model.doRetriveById(id);
    }

    public AccountDTO doRetriveByEmail(String email) throws SQLException{
        return model.doRetriveByEmail(email);
    }

    public int doSaveIndirizzo(IndirizzoSpedDTO indirizzo, int idAccount) throws SQLException{
        List<IndirizzoSpedDTO> indirizzi= modelInd.doRetriveByAcount(idAccount);

        //se sto inserendo il primo indirizzo
        if(indirizzi.size()==0){
            modelInd.doSaveIndirizzo(indirizzo);
            return 0;
        }else{
            modelInd.doSaveIndirizzo(indirizzo);
            return 1;
        }
    }
}
