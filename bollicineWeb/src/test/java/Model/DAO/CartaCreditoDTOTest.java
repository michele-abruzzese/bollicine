package Model.DAO;

import Model.DTO.CartaCreditoDTO;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class CartaCreditoDTOTest {

    /*
     * CONSTRUCTORS TESTING
     * */

    @Test
    void testCartaCreditoEmptyConstructors(){
        CartaCreditoDTO carta = new CartaCreditoDTO();
        assertNotNull(carta);
    }

    @Test
    void testCartaCreditoConstructor(){
        CartaCreditoDTO carta = new CartaCreditoDTO(1,"Alfredo","Cornacchia",1234567890123456L,123,"01/01/2020",1);
        assertNotNull(carta);
    }

    /*
     * GETTER METHOD TESTING
     * */

    @Test
    void getIdCartaCredito() {
        CartaCreditoDTO carta = new CartaCreditoDTO(1,"Alfredo","Cornacchia",1234567890123456L,123,"01/01/2020",1);
        assertEquals(1,carta.getIdCartaCredito());
    }

    @Test
    void getNome() {
        CartaCreditoDTO carta = new CartaCreditoDTO(1,"Alfredo","Cornacchia",1234567890123456L,123,"01/01/2020",1);
        assertEquals("Alfredo",carta.getNome());
    }

    @Test
    void getCognome() {
        CartaCreditoDTO carta = new CartaCreditoDTO(1,"Alfredo","Cornacchia",1234567890123456L,123,"01/01/2020",1);
        assertEquals("Cornacchia",carta.getCognome());
    }

    @Test
    void getNumero() {
        CartaCreditoDTO carta = new CartaCreditoDTO(1,"Alfredo","Cornacchia",1234567890123456L,123,"01/01/2020",1);
        assertEquals(1234567890123456L,carta.getNumero());
    }

    @Test
    void getCcv() {
        CartaCreditoDTO carta = new CartaCreditoDTO(1,"Alfredo","Cornacchia",1234567890123456L,123,"01/01/2020",1);
        assertEquals(123,carta.getCcv());
    }

    @Test
    void getScandenza() {
        CartaCreditoDTO carta = new CartaCreditoDTO(1,"Alfredo","Cornacchia",1234567890123456L,123,"01/01/2020",1);
        assertEquals("01/01/2020",carta.getScandenza());
    }

    @Test
    void getIdAccount() {
        CartaCreditoDTO carta = new CartaCreditoDTO(1,"Alfredo","Cornacchia",1234567890123456L,123,"01/01/2020",1);
        assertEquals(01,carta.getIdAccount());
    }

    /*
    * SETTING METHOD TESTING
    * */

    @Test
    void setIdCartaCredito() throws SQLException {
        CartaCreditoDTO carta = new CartaCreditoDTO();
        carta.setIdCartaCredito(1);

        assertEquals(1,carta.getIdCartaCredito());
    }

    @Test
    void setNome() throws SQLException{
        CartaCreditoDTO carta = new CartaCreditoDTO();
        carta.setNome("Alfredo");

        assertEquals("Alfredo",carta.getNome());
    }

    @Test
    void setCognome() throws SQLException{
        CartaCreditoDTO carta = new CartaCreditoDTO();
        carta.setCognome("Cornacchia");


        assertEquals("Cornacchia",carta.getCognome());    }

    @Test
    void setNumero() throws SQLException{
        CartaCreditoDTO carta = new CartaCreditoDTO();
        carta.setNumero(123456L);

        assertEquals(123456L,carta.getNumero());
    }

    @Test
    void setCcv() throws SQLException {
        CartaCreditoDTO carta = new CartaCreditoDTO();
        carta.setCcv(123);
        assertEquals(123,carta.getCcv());
    }

    @Test
    void setScandenza() throws SQLException {
        CartaCreditoDTO carta = new CartaCreditoDTO();
        carta.setScandenza("01/01/2020");

        assertEquals("01/01/2020",carta.getScandenza());
    }

    @Test
    void setIdAccount() throws SQLException {
        CartaCreditoDTO carta = new CartaCreditoDTO();
        carta.setIdAccount(1);

        assertEquals(1,carta.getIdAccount());
    }

}