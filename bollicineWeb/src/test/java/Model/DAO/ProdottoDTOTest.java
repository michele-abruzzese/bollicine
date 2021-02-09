package Model.DAO;

import Model.DTO.ProdottoDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProdottoDTOTest {

    @Test
    void ProdottoEmptyConstructor(){
        ProdottoDTO prod = new ProdottoDTO();
        assertNotNull(prod);
    }

    @Test
    void ProdottoConstructor() {
        ProdottoDTO prod = new ProdottoDTO(1,"Tavernello","Vino","Vino","img","Bianco",2020,12.2,3);
        assertNotNull(prod);
    }

    @Test
    void getIdProdotto() {
        ProdottoDTO prod = new ProdottoDTO(1,"Tavernello","Vino","Vino","img","Bianco",2020,12.2,3);
        assertEquals(1,prod.getIdProdotto());
    }

    @Test
    void setIdProdotto() {
        ProdottoDTO prod = new ProdottoDTO();
        prod.setIdProdotto(1);

        assertEquals(1,prod.getIdProdotto());
    }

    @Test
    void getNome() {
        ProdottoDTO prod = new ProdottoDTO(1,"Tavernello","Vino","Vino","img","Bianco",2020,12.2,3);
        assertEquals("Tavernello",prod.getNome());

    }

    @Test
    void setNome() {
        ProdottoDTO prod = new ProdottoDTO();
        prod.setNome("Tavernello");

        assertEquals("Tavernello",prod.getNome());
    }

    @Test
    void getCategoria() {
        ProdottoDTO prod = new ProdottoDTO(1,"Tavernello","Vino","Vino","img","Bianco",2020,12.2,3);
        assertEquals("Vino",prod.getCategoria());

    }

    @Test
    void setCategoria() {
        ProdottoDTO prod = new ProdottoDTO();
        prod.setCategoria("Bianco");

        assertEquals("Bianco",prod.getCategoria());
    }

    @Test
    void getDescrizione() {
        ProdottoDTO prod = new ProdottoDTO(1,"Tavernello","Vino","Vino","img","Bianco",2020,12.2,3);
        assertEquals("Vino",prod.getDescrizione());

    }

    @Test
    void setDescrizione() {
        ProdottoDTO prod = new ProdottoDTO();
        prod.setDescrizione("Bianco");

        assertEquals("Bianco",prod.getDescrizione());
    }

    @Test
    void getImmagine() {
        ProdottoDTO prod = new ProdottoDTO(1,"Tavernello","Vino","Vino","img","Bianco",2020,12.2,3);
        assertEquals("img",prod.getImmagine());
    }

    @Test
    void setImmagine() {
        ProdottoDTO prod = new ProdottoDTO();
        prod.setImmagine("img");

        assertEquals("img",prod.getImmagine());
    }

    @Test
    void getTipo() {
        ProdottoDTO prod = new ProdottoDTO(1,"Tavernello","Vino","Vino","img","Bianco",2020,12.2,3);
        assertEquals("Bianco",prod.getTipo());
    }

    @Test
    void setTipo() {
        ProdottoDTO prod = new ProdottoDTO();
        prod.setTipo("Bianco");

        assertEquals("Bianco",prod.getTipo());
    }

    @Test
    void getAnnata() {
        ProdottoDTO prod = new ProdottoDTO(1,"Tavernello","Vino","Vino","img","Bianco",2020,12.2,3);
        assertEquals(2020,prod.getAnnata());
    }

    @Test
    void setAnnata() {
        ProdottoDTO prod = new ProdottoDTO();
        prod.setAnnata(2020);

        assertEquals(2020,prod.getAnnata());
    }

    @Test
    void getPrezzo() {
        ProdottoDTO prod = new ProdottoDTO(1,"Tavernello","Vino","Vino","img","Bianco",2020,12.2,3);
        assertEquals(12.2,prod.getPrezzo());
    }

    @Test
    void setPrezzo() {
        ProdottoDTO prod = new ProdottoDTO();
        prod.setPrezzo(12.2);

        assertEquals(12.2,prod.getPrezzo());
    }

    @Test
    void getDisponibilità() {
        ProdottoDTO prod = new ProdottoDTO(1,"Tavernello","Vino","Vino","img","Bianco",2020,12.2,3);
        assertEquals(3,prod.getDisponibilità());
    }

    @Test
    void setDisponibilità() {
        ProdottoDTO prod = new ProdottoDTO();
        prod.setDisponibilità(1);

        assertEquals(1,prod.getDisponibilità());
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}