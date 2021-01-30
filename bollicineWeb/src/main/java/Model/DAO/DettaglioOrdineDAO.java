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

        ps=con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        ps.setInt(1,dett.getIdPodotto());
        ps.setInt(2,dett.getIdOrdine());
        ps.setInt(3,dett.getQuantità());
        ps.setDouble(4,dett.getPrezzoUnit());
        ps.setInt(5,dett.getIva());

        ps.executeUpdate();

    }

}
