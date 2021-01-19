package Model.DAO;

import Model.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrdineDAO implements OrdineDAOIn{
    static Connection con = DatabaseConnection.getConnection();
    private static final String TABLE_NAME = "ordine";

    @Override
    public void doSaveOrdine(OrdineDTO ordine) throws SQLException {
        PreparedStatement st=null;

        String query="INSERT INTO "+OrdineDAO.TABLE_NAME+" (TotaleOrdine,Data,MetodoPagamento,idCarta,idIndirizzo,idAccount) VALUES (?,?,?,?,?,?,?)";

        st=con.prepareStatement(query);

        st.setFloat(1,ordine.getTotOrdine());
        st.setString(2,ordine.getData());
        st.setString(3,ordine.getMetodoPag());
        st.setInt(4,ordine.getIdCarta());
        st.setInt(5,ordine.getIdIndirizzo());
        st.setInt(6,ordine.getIdAccount());

        st.executeUpdate();

    }

    @Override
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

    @Override
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
