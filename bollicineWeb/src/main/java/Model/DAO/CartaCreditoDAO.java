package Model.DAO;

import Model.DTO.CartaCreditoDTO;
import Model.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartaCreditoDAO {
    static Connection con = DatabaseConnection.getConnection();
    private static final String TABLE_NAME = "cartadicredito";


    public synchronized int doSaveCartaCredito(CartaCreditoDTO cc)throws SQLException {
        PreparedStatement ps = null;

        String query="INSERT INTO "+CartaCreditoDAO.TABLE_NAME+" (Nome,Cognome,Numero,CCV,Scadenza,idAccount) VALUES (?,?,?,?,?,?)";

        ps= con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        ps.setString(1,cc.getNome());
        ps.setString(2,cc.getCognome());
        ps.setLong(3,cc.getNumero());
        ps.setInt(4,cc.getCcv());
        ps.setString(5,cc.getScandenza());
        ps.setInt(6,cc.getIdAccount());

        ps.executeUpdate();

        ResultSet rs = ps.getGeneratedKeys();
        rs.next();
        int key= rs.getInt(1);

        return key;
    }


    public synchronized List<CartaCreditoDTO> doRetriveByAccount(int idAccount)throws SQLException {
        List <CartaCreditoDTO> carte=new ArrayList<CartaCreditoDTO>();

        PreparedStatement ps = null;

        String query="SELECT * FROM "+CartaCreditoDAO.TABLE_NAME+" WHERE idAccount=?";

        ps= con.prepareStatement(query);

        ps.setInt(1,idAccount);

        ResultSet rs= ps.executeQuery();

        while(rs.next()){
            CartaCreditoDTO cc=new CartaCreditoDTO();
            cc.setIdCartaCredito(rs.getInt("idCartaDiCredito"));
            cc.setNome(rs.getString("Nome"));
            cc.setCognome(rs.getString("Cognome"));
            cc.setNumero(rs.getLong("Numero"));
            cc.setCcv(rs.getInt("CCV"));
            cc.setScandenza(rs.getString("Scadenza"));
            cc.setIdAccount(rs.getInt("idAccount"));

            carte.add(cc);
        }



        return carte;
    }


    public CartaCreditoDTO doRetriveById(int idCarta) throws SQLException {
        CartaCreditoDTO carta= new CartaCreditoDTO();


        String query="SELECT * FROM "+CartaCreditoDAO.TABLE_NAME+" WHERE idCartaDiCredito=?";

        PreparedStatement st=con.prepareStatement(query);

        st.setInt(1,idCarta);

        ResultSet rs= st.executeQuery();

        while (rs.next()){
            carta.setIdCartaCredito(rs.getInt("idCartaDiCredito"));
            carta.setNome(rs.getString("Nome"));
            carta.setCognome(rs.getString("Cognome"));
            carta.setNumero(rs.getLong("Numero"));
            carta.setCcv(rs.getInt("CCV"));
            carta.setScandenza(rs.getString("Scadenza"));
            carta.setIdAccount(rs.getInt("idAccount"));
        }


        return carta;
    }

    public void doDelete(int id) throws SQLException{

        String query = "DELETE FROM "+CartaCreditoDAO.TABLE_NAME+" WHERE (idCartaDiCredito=?)";

        PreparedStatement ps = null;
        ps= con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        ps.setInt(1,id);

        ps.executeUpdate();
    }
}
