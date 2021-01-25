package Model.DAO;

import Model.DTO.OrdineDTO;
import Model.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrdineDAO {
    static Connection con = DatabaseConnection.getConnection();
    private static final String TABLE_NAME = "ordine";


    public int doSaveOrdine(OrdineDTO ordine) throws SQLException {
        PreparedStatement st=null;

        String query="INSERT INTO "+OrdineDAO.TABLE_NAME+" (TotaleOrdine,Data,MetodoPagamento,idCarta,idIndirizzo,idAccount) VALUES (?,?,?,?,?,?)";

        st=con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        st.setFloat(1,ordine.getTotOrdine());
        st.setString(2,ordine.getData());
        st.setString(3,ordine.getMetodoPag());
        st.setInt(4,ordine.getIdCarta());
        st.setInt(5,ordine.getIdIndirizzo());
        st.setInt(6,ordine.getIdAccount());

        st.executeUpdate();

        ResultSet rs = st.getGeneratedKeys();
        rs.next();
        int key= rs.getInt(1);

        return key;
    }


    public List<OrdineDTO> doRetriveAllByAccount(int idAccount) throws SQLException {
        List<OrdineDTO> ordini=new ArrayList<OrdineDTO>();

        String query="SELECT * FROM "+OrdineDAO.TABLE_NAME+" WHERE idAccount=?";

        PreparedStatement st=con.prepareStatement(query);
        st.setInt(1,idAccount);

        ResultSet rs=st.executeQuery();

        while(rs.next()){
            OrdineDTO ordine = new OrdineDTO();
            ordine.setIdOrdine(rs.getInt("idOrdine"));
            ordine.setTotOrdine(rs.getFloat("TotaleOrdine"));
            ordine.setData(rs.getString("Data"));
            ordine.setIdCarta(rs.getInt("idCarta"));
            ordine.setIdIndirizzo(rs.getInt("idIndirizzo"));
            ordine.setIdAccount(rs.getInt("idAccount"));

            ordini.add(ordine);
        }
        return ordini;
    }


    public List<OrdineDTO> doRetriveAll() throws SQLException {
        List<OrdineDTO> ordini=new ArrayList<OrdineDTO>();

        String query="SELECT * FROM "+OrdineDAO.TABLE_NAME;

        PreparedStatement st=con.prepareStatement(query);
        ResultSet rs=st.executeQuery();

        while(rs.next()){
            OrdineDTO ordine = new OrdineDTO();
            ordine.setIdOrdine(rs.getInt("idOrdine"));
            ordine.setTotOrdine(rs.getFloat("TotaleOrdine"));
            ordine.setData(rs.getString("Data"));
            ordine.setIdCarta(rs.getInt("idCarta"));
            ordine.setIdIndirizzo(rs.getInt("idIndirizzo"));
            ordine.setIdAccount(rs.getInt("idAccount"));

            ordini.add(ordine);
        }
        return ordini;
    }
}
