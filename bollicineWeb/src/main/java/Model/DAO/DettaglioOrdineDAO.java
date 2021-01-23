package Model.DAO;

import Model.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DettaglioOrdineDAO implements DettaglioOrdineDAOIn{
    static Connection con = DatabaseConnection.getConnection();
    private static final String TABLE_NAME = "dettaglioordine";

    @Override
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

    @Override
    public List<DettaglioOrdineDTO> doRetriveByOrdine(int idOrdine)throws SQLException {
        List<DettaglioOrdineDTO> dettagli=new ArrayList<DettaglioOrdineDTO>();

        PreparedStatement ps=null;

        String query="SELECT * FROM "+DettaglioOrdineDAO.TABLE_NAME+" WHERE idOrdine=?";

        ps=con.prepareStatement(query);

        ps.setInt(1,idOrdine);

        ResultSet rs= ps.executeQuery();

        while(rs.next()){
            DettaglioOrdineDTO dettaglio=new DettaglioOrdineDTO();
            dettaglio.setIdPodotto(rs.getInt("idProdotto"));
            dettaglio.setIdOrdine(rs.getInt("idOrdine"));
            dettaglio.setQuantità(rs.getInt("Quantità"));
            dettaglio.setPrezzoUnit(rs.getFloat("PrezzoUnitario"));
            dettaglio.setIva(rs.getInt("IVA"));

            dettagli.add(dettaglio);
        }

        return dettagli;
    }
}
