import Model.DAO.AccountDTO;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class AccountDTOTest {

    @Test
    void testAccountEmptyConstructor(){
        AccountDTO account = new AccountDTO();
        assertNotNull(account);
    }

    @Test
    void setId() throws SQLException {

        AccountDTO account= new AccountDTO();
        account.setId(1);

        assertEquals(1, account.getId());

    }



}
