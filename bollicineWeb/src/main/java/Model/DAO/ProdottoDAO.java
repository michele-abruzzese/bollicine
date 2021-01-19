package Model.DAO;

import Model.DatabaseConnection;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ProdottoDAO implements ProdottoDAOIn{
    static Connection con = DatabaseConnection.getConnection();
    private static final String TABLE_NAME = "prodotto";

    @Override
    public void doSaveProdotto(ProdottoDTO prod) throws SQLException, IOException {
        PreparedStatement ps=null;

        String query="INSERT INTO "+ProdottoDAO.TABLE_NAME+"(idProdotto,Nome,Categoria,Descrizione,Immagine,Tipo,Annata,Prezzo,Disponibilità) VALUES (?,?,?,?,?,?,?,?,?)";

        ps=con.prepareStatement(query);

        ps.setInt(1,prod.getIdProdotto());
        ps.setString(2,prod.getNome());
        ps.setString(3,prod.getCategoria());
        ps.setString(4,prod.getCategoria());

        //in immagine abbiamo il path mandato dalla servlet
        File file = new File(prod.getImmagine());
        FileInputStream fis = new FileInputStream(file);

        ps.setBinaryStream(5,fis,fis.available());

        //continuo con altri campi
        ps.setString(6,prod.getTipo());
        ps.setInt(7,prod.getAnnata());
        ps.setFloat(8,prod.getPrezzo());
        ps.setInt(9,prod.getDisponibilità());

        ps.executeUpdate();
    }

    @Override
    public List<ProdottoDTO> doRetriveAll() throws SQLException {
        return null;
    }

    @Override
    public List<ProdottoDTO> doRetriveByCat() throws SQLException {
        return null;
    }
}
