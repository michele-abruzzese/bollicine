package Model.DAO;

import Model.DTO.DettaglioOrdineDTO;
import Model.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DettaglioOrdineDAO {
    static Connection con = DatabaseConnection.getConnection();
    private static final String TABLE_NAME = "dettaglioordine";


    public void doSaveDettaglioOrdine(DettaglioOrdineDTO dett)throws SQLException {
        PreparedStatement ps = null;

        String query="INSERT INTO "+DettaglioOrdineDAO.TABLE_NAME+" (idProdotto,idOrdine,Quantità,PrezzoUnitario,IVA) VALUES (?,?,?,?,?)";

        ps=con.prepareStatement(query);

        ps.setInt(1,dett.getIdPodotto());
        ps.setInt(2,dett.getIdOrdine());
        ps.setInt(3,dett.getQuantità());
        ps.setDouble(4,dett.getPrezzoUnit());
        ps.setInt(5,dett.getIva());

        ps.executeUpdate();

    }

    public List<DettaglioOrdineDTO> doRetriveAll() throws SQLException {
        List<DettaglioOrdineDTO> orders = new ArrayList<DettaglioOrdineDTO>();
        PreparedStatement ps = null;
        String query ="SELECT * FROM "+DettaglioOrdineDAO.TABLE_NAME;
        ps=con.prepareStatement(query);

        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            DettaglioOrdineDTO ord = new DettaglioOrdineDTO();
            ord.setIdPodotto(rs.getInt("idProdotto"));
            ord.setIdOrdine(rs.getInt("idOrdine"));
            ord.setQuantità(rs.getInt("Quantità"));
            ord.setPrezzoUnit(rs.getDouble("PrezzoUnitario"));
            ord.setIva(rs.getInt("IVA"));

            orders.add(ord);
        }

        return orders;

    }

    public void removeDettaglioOrdine(int idProd, int idOrd) throws SQLException {
        PreparedStatement ps= null;
        String query ="DELETE FROM "+DettaglioOrdineDAO.TABLE_NAME+" WHERE (idProdotto=?) AND (idOrdine=?)";
        ps=con.prepareStatement(query);

        ps.setInt(1,idProd);
        ps.setInt(2,idOrd);

        ps.executeUpdate();

    }

}
