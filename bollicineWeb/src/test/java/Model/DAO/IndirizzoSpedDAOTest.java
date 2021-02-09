package Model.DAO;

import Model.DTO.AccountDTO;
import Model.DTO.IndirizzoSpedDTO;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IndirizzoSpedDAOTest {

    @Test
    void doSaveIndirizzo() throws SQLException {
        AccountDTO ac = new AccountDTO(0,"Giulio","Costante","giulio@test.com","password","confermato","utente");
        AccountDAO a = new AccountDAO();
        int idAc=a.doSaveAcount(ac);


        IndirizzoSpedDTO ind = new IndirizzoSpedDTO(0,"Giulio","Costante","Via Roma",84084,"Fisciano","Salerno","ufficio",idAc);
        IndirizzoSpedDAO i=new IndirizzoSpedDAO();

        int idInd =i.doSaveIndirizzo(ind);
        ind.setIdIndirizzo(idInd);

        assertEquals(ind,i.doRetriveById(idInd));


        i.removeIndirizzo(idInd);
        a.removeAccount(idAc);
    }

    @Test
    void doRetriveByAcount() throws SQLException {
        AccountDTO ac = new AccountDTO(0,"Giulio","Costante","giulio@test.com","password","confermato","utente");
        AccountDAO a = new AccountDAO();
        int idAc=a.doSaveAcount(ac);

        IndirizzoSpedDTO ind = new IndirizzoSpedDTO(0,"Giulio","Costante","Via Roma",84084,"Fisciano","Salerno","ufficio",idAc);
        IndirizzoSpedDAO i=new IndirizzoSpedDAO();
        int idInd =i.doSaveIndirizzo(ind);
        ind.setIdIndirizzo(idInd);

        List<IndirizzoSpedDTO> indirizzi = i.doRetriveByAcount(idAc);

        assertEquals(ind,indirizzi.get(0));

        a.removeAccount(idAc);
        i.removeIndirizzo(idInd);

    }

    @Test
    void doRetriveById() throws SQLException {
        AccountDTO ac = new AccountDTO(0,"Giulio","Costante","giulio@test.com","password","confermato","utente");
        AccountDAO a = new AccountDAO();
        int idAc=a.doSaveAcount(ac);

        IndirizzoSpedDTO ind = new IndirizzoSpedDTO(0,"Giulio","Costante","Via Roma",84084,"Fisciano","Salerno","ufficio",idAc);
        IndirizzoSpedDAO i=new IndirizzoSpedDAO();
        int idInd =i.doSaveIndirizzo(ind);
        ind.setIdIndirizzo(idInd);

        assertEquals(ind,i.doRetriveById(idInd));

        a.removeAccount(idAc);
        i.removeIndirizzo(idInd);
    }

    @Test
    void removeIndirizzo() throws SQLException {
        AccountDTO ac = new AccountDTO(0,"Giulio","Costante","giulio@test.com","password","confermato","utente");
        AccountDAO a = new AccountDAO();
        int idAc=a.doSaveAcount(ac);

        IndirizzoSpedDTO ind = new IndirizzoSpedDTO(0,"Giulio","Costante","Via Roma",84084,"Fisciano","Salerno","ufficio",idAc);
        IndirizzoSpedDAO i=new IndirizzoSpedDAO();
        int idInd =i.doSaveIndirizzo(ind);

        i.removeIndirizzo(idInd);

        List<IndirizzoSpedDTO> indirizzi = i.doRetriveByAcount(idAc);

        a.removeAccount(idAc);

        assertEquals(0,indirizzi.size());
    }
}