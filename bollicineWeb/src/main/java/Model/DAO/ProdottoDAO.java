package Model.DAO;

import Model.DTO.ProdottoDTO;
import Model.DatabaseConnection;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdottoDAO {
    static Connection con = DatabaseConnection.getConnection();
    private static final String TABLE_NAME = "prodotto";


    public synchronized void doSaveProdotto(ProdottoDTO prod) throws SQLException, IOException {

        String query="INSERT INTO "+ProdottoDAO.TABLE_NAME+" (Nome,Categoria,Descrizione,Immagine,Tipo,Annata,Prezzo,Disponibilità) VALUES (?,?,?,?,?,?,?,?)";

        PreparedStatement ps=con.prepareStatement(query);


        ps.setString(1,prod.getNome());
        ps.setString(2,prod.getCategoria());
        ps.setString(3,prod.getDescrizione());

        //in immagine abbiamo il path mandato dalla servlet
        File file = new File(prod.getImmagine());
        FileInputStream fis = new FileInputStream(file);

        ps.setBinaryStream(4,fis,fis.available());

        //continuo con altri campi
        ps.setString(5,prod.getTipo());
        ps.setInt(6, prod.getAnnata());
        ps.setDouble(7,prod.getPrezzo());
        ps.setInt(8,prod.getDisponibilità());

        ps.executeUpdate();
    }


    public void doUpdateProdotto(ProdottoDTO prod) throws SQLException, IOException {
        String query="UPDATE "+ProdottoDAO.TABLE_NAME+" SET Nome=?, Categoria=?, Descrizione=?, Immagine=?, Tipo=?, Annata=?, Prezzo=?, Disponibilità=? WHERE idProdotto=?";

        PreparedStatement st = con.prepareStatement(query);

        st.setString(1,prod.getNome());
        st.setString(2,prod.getCategoria());
        st.setString(3,prod.getDescrizione());

        //in immagine abbiamo il path mandato dalla servlet
        File file = new File(prod.getImmagine());
        FileInputStream fis = new FileInputStream(file);

        st.setBinaryStream(4,fis,fis.available());

        //continuo con altri campi
        st.setString(5,prod.getTipo());
        st.setInt(6, prod.getAnnata());
        st.setDouble(7,prod.getPrezzo());
        st.setInt(8,prod.getDisponibilità());
        st.setInt(9,prod.getIdProdotto());

        st.executeUpdate();

    }


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


    public ProdottoDTO doRetriveById(int id) throws SQLException {
        ProdottoDTO prod=new ProdottoDTO();

        String query="SELECT * FROM "+ProdottoDAO.TABLE_NAME+" WHERE idProdotto=?";

        PreparedStatement st= con.prepareStatement(query);
        st.setInt(1,id);

        ResultSet rs= st.executeQuery();

        while(rs.next()){
            prod.setIdProdotto(rs.getInt("idProdotto"));
            prod.setNome(rs.getString("Nome"));
            prod.setCategoria(rs.getString("Categoria"));
            prod.setDescrizione(rs.getString("Descrizione"));
            prod.setTipo(rs.getString("Tipo"));
            prod.setAnnata(rs.getInt("Annata"));
            prod.setPrezzo(rs.getFloat("Prezzo"));
            prod.setDisponibilità(rs.getInt("Disponibilità"));
        }

        return prod;
    }


    public void updateDispo(ProdottoDTO prodotto, int qt) throws SQLException {
        int id=prodotto.getIdProdotto();

        String query="UPDATE "+ProdottoDAO.TABLE_NAME+" SET Disponibilità=? WHERE idProdotto=?";

        PreparedStatement st=con.prepareStatement(query);
        st.setInt(1,qt);
        st.setInt(2,id);

        st.executeUpdate();


    }


}
