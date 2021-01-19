package Model.DAO;

import Model.DatabaseConnection;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdottoDAO implements ProdottoDAOIn{
    static Connection con = DatabaseConnection.getConnection();
    private static final String TABLE_NAME = "prodotto";

    @Override
    public synchronized void doSaveProdotto(ProdottoDTO prod) throws SQLException, IOException {

        String query="INSERT INTO "+ProdottoDAO.TABLE_NAME+" (Nome,Categoria,Descrizione,Immagine,Tipo,Annata,Prezzo,Disponibilità) VALUES (?,?,?,?,?,?,?,?)";

        PreparedStatement ps=con.prepareStatement(query);


        ps.setString(1,prod.getNome());
        ps.setString(2,prod.getCategoria());
        ps.setString(3,prod.getCategoria());

        //in immagine abbiamo il path mandato dalla servlet
        File file = new File(prod.getImmagine());
        FileInputStream fis = new FileInputStream(file);

        ps.setBinaryStream(4,fis,fis.available());

        //continuo con altri campi
        ps.setString(5,prod.getTipo());
        ps.setInt(6,prod.getAnnata());
        ps.setFloat(7,prod.getPrezzo());
        ps.setInt(8,prod.getDisponibilità());

        ps.executeUpdate();
    }

    @Override
    public synchronized List<ProdottoDTO> doRetriveAll() throws SQLException {
        List<ProdottoDTO> prodotti = new ArrayList<ProdottoDTO>();

        String query="SELECT * FROM "+ProdottoDAO.TABLE_NAME;

        PreparedStatement ps=con.prepareStatement(query);

        ResultSet rs=ps.executeQuery();

        while(rs.next()){
            ProdottoDTO prod=new ProdottoDTO();
            prod.setIdProdotto(rs.getInt("idProdotto"));
            prod.setNome(rs.getString("Nome"));
            prod.setCategoria(rs.getString("Categoria"));
            prod.setDescrizione(rs.getString("Descrizione"));
            prod.setTipo(rs.getString("Tipo"));
            prod.setAnnata(rs.getInt("Annata"));
            prod.setPrezzo(rs.getFloat("Prezzo"));
            prod.setDisponibilità(rs.getInt("Disponibilità"));

            prodotti.add(prod);
        }

        return prodotti;
    }

    @Override
    public synchronized List<ProdottoDTO> doRetriveByCat(String cat) throws SQLException {
        List<ProdottoDTO> prodotti = new ArrayList<ProdottoDTO>();

        PreparedStatement stm=null;

        String query="SELECT * FROM "+ProdottoDAO.TABLE_NAME+" WHERE Categoria=?";

        stm=con.prepareStatement(query);

        stm.setString(1,cat);

        ResultSet rs=stm.executeQuery();

        while(rs.next()){
            ProdottoDTO prod=new ProdottoDTO();
            prod.setIdProdotto(rs.getInt("idProdotto"));
            prod.setNome(rs.getString("Nome"));
            prod.setCategoria(rs.getString("Categoria"));
            prod.setDescrizione(rs.getString("Descrizione"));
            prod.setTipo(rs.getString("Tipo"));
            prod.setAnnata(rs.getInt("Annata"));
            prod.setPrezzo(rs.getFloat("Prezzo"));
            prod.setDisponibilità(rs.getInt("Disponibilità"));

            prodotti.add(prod);
        }

        return prodotti;
    }

    @Override
    public synchronized byte[] doRetriveImgById(int id) throws SQLException {

        byte[] bt=null;

        String query="SELECT Immagine FROM "+ProdottoDAO.TABLE_NAME+" WHERE idProdotto=?";

        PreparedStatement st= con.prepareStatement(query);
        st.setInt(1,id);

        ResultSet rs= st.executeQuery();

        if(rs.next()){
            bt= rs.getBytes("Immagine");
        }
        return bt;
    }
}