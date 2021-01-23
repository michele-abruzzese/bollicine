package Model.DAO;

import Model.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IndirizzoSpedDAO implements IndirizzoSpedDAOIn{
    static Connection con = DatabaseConnection.getConnection();
    private static final String TABLE_NAME = "indirizzospedizione";

    @Override
    public synchronized int doSaveIndirizzo(IndirizzoSpedDTO indirizzo) throws SQLException {
        PreparedStatement ps=null;

        String query="INSERT INTO "+IndirizzoSpedDAO.TABLE_NAME+" (Nome,Cognome,Indirizzo,CAP,Città,Provincia,Alias,idAccount) VALUES (?,?,?,?,?,?,?,?)";

        ps= con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        ps.setString(1,indirizzo.getNome());
        ps.setString(2,indirizzo.getCognome());
        ps.setString(3,indirizzo.getIndirizzo());
        ps.setInt(4,indirizzo.getCap());
        ps.setString(5,indirizzo.getCittà());
        ps.setString(6,indirizzo.getProvincia());
        ps.setString(7,indirizzo.getAlias());
        ps.setInt(8,indirizzo.getIdAccount());

        ps.executeUpdate();

        ResultSet rs = ps.getGeneratedKeys();
        rs.next();
        int key= rs.getInt(1);

        return key;
    }

    @Override
    public synchronized List<IndirizzoSpedDTO> doRetriveByAcount(int idAccount) throws SQLException {
        List<IndirizzoSpedDTO> indirizzi=new ArrayList<IndirizzoSpedDTO>();

        PreparedStatement ps=null;

        String query="SELECT * FROM "+IndirizzoSpedDAO.TABLE_NAME+" WHERE idAccount=?";

        ps=con.prepareStatement(query);

        ps.setInt(1,idAccount);

        ResultSet rs=ps.executeQuery();

        while(rs.next()){
            IndirizzoSpedDTO indirizzo=new IndirizzoSpedDTO();
            indirizzo.setIdIndirizzo(rs.getInt("idIndirizzoSpedizione"));
            indirizzo.setNome(rs.getString("Nome"));
            indirizzo.setCognome(rs.getString("Cognome"));
            indirizzo.setIndirizzo(rs.getString("Indirizzo"));
            indirizzo.setCap(rs.getInt("CAP"));
            indirizzo.setCittà(rs.getString("Città"));
            indirizzo.setProvincia(rs.getString("Provincia"));
            indirizzo.setAlias(rs.getString("Alias"));
            indirizzo.setIdAccount(rs.getInt("idAccount"));

            indirizzi.add(indirizzo);
        }

        return indirizzi;
    }

    @Override
    public IndirizzoSpedDTO doRetriveById(int idIndirizzo) throws SQLException {
        IndirizzoSpedDTO indirizzo= new IndirizzoSpedDTO();

        String query="SELECT * FROM "+IndirizzoSpedDAO.TABLE_NAME+" WHERE idIndirizzoSpedizione=?";

        PreparedStatement ps= con.prepareStatement(query);

        ps.setInt(1,idIndirizzo);

        ResultSet rs= ps.executeQuery();

        while(rs.next()){
            indirizzo.setIdIndirizzo(rs.getInt("idIndirizzoSpedizione"));
            indirizzo.setNome(rs.getString("Nome"));
            indirizzo.setCognome(rs.getString("Cognome"));
            indirizzo.setIndirizzo(rs.getString("Indirizzo"));
            indirizzo.setCap(rs.getInt("CAP"));
            indirizzo.setCittà(rs.getString("Città"));
            indirizzo.setProvincia(rs.getString("Provincia"));
            indirizzo.setAlias(rs.getString("Alias"));
            indirizzo.setIdAccount(rs.getInt("idAccount"));
        }

        return indirizzo;
    }


}
