package Model.DAO;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DettaglioOrdineDTOTest {

    @Test
    void dettaglioOrdineEmptyConstructors(){
        DettaglioOrdineDTO dettaglio = new DettaglioOrdineDTO();
        assertNotNull(dettaglio);
    }

    @Test
    void dettaglioOrdineConstructors(){
        DettaglioOrdineDTO dettaglio = new DettaglioOrdineDTO(1,1,2,12.2,22);
        assertNotNull(dettaglio);
    }

    @Test
    void getIdPodotto() {
        DettaglioOrdineDTO dettaglio = new DettaglioOrdineDTO(1,1,2,12.2,22);
        assertEquals(1,dettaglio.getIdPodotto());
    }

    @Test
    void setIdPodotto() {
        DettaglioOrdineDTO dettaglio = new DettaglioOrdineDTO();
        dettaglio.setIdPodotto(1);

        assertEquals(1,dettaglio.getIdPodotto());
    }

    @Test
    void getIdOrdine() {
        DettaglioOrdineDTO dettaglio = new DettaglioOrdineDTO(1,1,2,12.2,22);
        assertEquals(1,dettaglio.getIdOrdine());
    }

    @Test
    void setIdOrdine() {
        DettaglioOrdineDTO dettaglio = new DettaglioOrdineDTO();
        dettaglio.setIdOrdine(1);

        assertEquals(1,dettaglio.getIdOrdine());
    }

    @Test
    void getQuantità() {
        DettaglioOrdineDTO dettaglio = new DettaglioOrdineDTO(1,1,2,12.2,22);
        assertEquals(2,dettaglio.getQuantità());
    }

    @Test
    void setQuantità() {
        DettaglioOrdineDTO dettaglio = new DettaglioOrdineDTO();
        dettaglio.setQuantità(2);

        assertEquals(2,dettaglio.getQuantità());
    }

    @Test
    void getPrezzoUnit() {
        DettaglioOrdineDTO dettaglio = new DettaglioOrdineDTO(1,1,2,12.2,22);
        assertEquals(12.2,dettaglio.getPrezzoUnit());
    }

    @Test
    void setPrezzoUnit() {
        DettaglioOrdineDTO dettaglio = new DettaglioOrdineDTO();
        dettaglio.setPrezzoUnit(1);

        assertEquals(1,dettaglio.getPrezzoUnit());
    }

    @Test
    void getIva() {
        DettaglioOrdineDTO dettaglio = new DettaglioOrdineDTO(1,1,2,12.2,22);
        assertEquals(22,dettaglio.getIva());
    }

    @Test
    void setIva() {
        DettaglioOrdineDTO dettaglio = new DettaglioOrdineDTO();
        dettaglio.setIva(1);

        assertEquals(1,dettaglio.getIva());
    }
}