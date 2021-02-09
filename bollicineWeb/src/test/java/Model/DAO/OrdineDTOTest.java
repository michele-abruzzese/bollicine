package Model.DAO;

import Model.DTO.OrdineDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrdineDTOTest {

    @Test
    void OrdineEmptyConstructor(){
        OrdineDTO ordine = new OrdineDTO();
        assertNotNull(ordine);
    }

    @Test
    void OrdiniConstructor(){
        OrdineDTO ordine= new OrdineDTO(1, 52,"01/01/2020","metod",1,1,1);
        assertNotNull(ordine);
    }

    @Test
    void getIdOrdine() {
        OrdineDTO ordine = new OrdineDTO(1, 52,"01/01/2020","metod",1,1,1);

        assertEquals(1,ordine.getIdOrdine());
    }

    @Test
    void setIdOrdine() {
        OrdineDTO ordine = new OrdineDTO();
        ordine.setIdOrdine(1);

        assertEquals(1,ordine.getIdOrdine());
    }

    @Test
    void getTotOrdine() {
        OrdineDTO ordine = new OrdineDTO(1, 52,"01/01/2020","metod",1,1,1);

        assertEquals(52,ordine.getTotOrdine());
    }

    @Test
    void setTotOrdine() {
        OrdineDTO ordine = new OrdineDTO();
        ordine.setTotOrdine(20);

        assertEquals(20,ordine.getTotOrdine());
    }

    @Test
    void getData() {
        OrdineDTO ordine = new OrdineDTO(1, 52,"01/01/2020","metod",1,1,1);

        assertEquals("01/01/2020",ordine.getData());
    }

    @Test
    void setData() {
        OrdineDTO ordine = new OrdineDTO();
        ordine.setData("01/01/2020");

        assertEquals("01/01/2020",ordine.getData());
    }

    @Test
    void getMetodoPag() {
        OrdineDTO ordine = new OrdineDTO(1, 52,"01/01/2020","metod",1,1,1);

        assertEquals("metod",ordine.getMetodoPag());
    }

    @Test
    void setMetodoPag() {
        OrdineDTO ordine = new OrdineDTO();
        ordine.setMetodoPag("metod");

        assertEquals("metod",ordine.getMetodoPag());
    }

    @Test
    void getIdCarta() {
        OrdineDTO ordine = new OrdineDTO(1, 52,"01/01/2020","metod",1,1,1);

        assertEquals(1,ordine.getIdCarta());
    }

    @Test
    void setIdCarta() {
        OrdineDTO ordine = new OrdineDTO();
        ordine.setIdCarta(1);

        assertEquals(1,ordine.getIdCarta());
    }

    @Test
    void getIdIndirizzo() {
        OrdineDTO ordine = new OrdineDTO(1, 52,"01/01/2020","metod",1,1,1);

        assertEquals(1,ordine.getIdIndirizzo());
    }

    @Test
    void setIdIndirizzo() {
        OrdineDTO ordine = new OrdineDTO();
        ordine.setIdIndirizzo(1);

        assertEquals(1,ordine.getIdIndirizzo());
    }

    @Test
    void getIdAccount() {
        OrdineDTO ordine = new OrdineDTO(1, 52,"01/01/2020","metod",1,1,1);

        assertEquals(1,ordine.getIdAccount());
    }

    @Test
    void setIdAccount() {
        OrdineDTO ordine = new OrdineDTO();
        ordine.setIdAccount(1);

        assertEquals(1,ordine.getIdAccount());
    }

}