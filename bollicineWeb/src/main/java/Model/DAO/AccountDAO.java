package Model.DAO;

import Model.DTO.AccountDTO;
import Model.DatabaseConnection;

import java.sql.*;

public class AccountDAO {
    static Connection con = DatabaseConnection.getConnection();
    private static final String TABLE_NAME = "Account";


    public synchronized int doSaveAcount(AccountDTO ac) throws SQLException {
        PreparedStatement ps = null;

        String query="INSERT INTO "+AccountDAO.TABLE_NAME+" (Nome,Cognome,Email,Password,Stato,Tipo) VALUES (?,?,?,?,?,?)";
        ps=con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        ps.setString(1,ac.getNome());
        ps.setString(2,ac.getCognome());
        ps.setString(3,ac.getEmail());
        ps.setString(4,ac.getPassword());
        ps.setString(5,ac.getStato());
        ps.setString(6,ac.getTipo());

        ps.executeUpdate();

        ResultSet rs = ps.getGeneratedKeys();
        rs.next();
        int key= rs.getInt(1);

        return key;
    }


    public void removeAccount(int id) throws SQLException {
        /*DELETE FROM `Bollicine`.`Account` WHERE (`idAccount` = '2');*/

        PreparedStatement ps = null;

        String query = "DELETE FROM "+AccountDAO.TABLE_NAME+" WHERE(idAccount = ?)";
        ps=con.prepareStatement(query);

        ps.setInt(1,id);

        ps.executeUpdate();
    }


    public synchronized AccountDTO doRetriveById(int id) throws SQLException {
        PreparedStatement ps = null;

        AccountDTO ac =new AccountDTO();

        String query="SELECT * " + "FROM "+AccountDAO.TABLE_NAME+ " WHERE idAccount=?";

        ps= con.prepareStatement(query);

        ps.setInt(1,id);

        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            ac.setId(rs.getInt("idAccount"));
            ac.setNome(rs.getString("Nome"));
            ac.setCognome(rs.getString("Cognome"));
            ac.setEmail(rs.getString("Email"));
            ac.setPassword(rs.getString("Password"));
            ac.setStato(rs.getString("Stato"));
            ac.setTipo(rs.getString("Tipo"));

        }

        return ac;
    }


    public synchronized AccountDTO doRetriveByEmail(String email) throws SQLException{
        PreparedStatement ps = null;

        AccountDTO ac =new AccountDTO();


        String query="SELECT * " + "FROM "+AccountDAO.TABLE_NAME+ " WHERE Email=?";

        ps= con.prepareStatement(query);


        ps.setString(1,email);

        ResultSet rs = ps.executeQuery();



        while(rs.next()){
            ac.setId(rs.getInt("idAccount"));
            ac.setNome(rs.getString("Nome"));
            ac.setCognome(rs.getString("Cognome"));
            ac.setEmail(rs.getString("Email"));
            ac.setPassword(rs.getString("Password"));
            ac.setStato(rs.getString("Stato"));
            ac.setTipo(rs.getString("Tipo"));

        }

        return ac;
    }


    public int controlEmail(String email) throws SQLException {

        String query="SELECT * FROM "+AccountDAO.TABLE_NAME+" WHERE  Email like ?";

        PreparedStatement st=con.prepareStatement(query);

        st.setString(1,email);

        ResultSet rs= st.executeQuery();

        //se la query non produce risultati allora non ci sono email uguali
        if(!rs.next()){
            return 0;
        }

        return 1;
    }

}
