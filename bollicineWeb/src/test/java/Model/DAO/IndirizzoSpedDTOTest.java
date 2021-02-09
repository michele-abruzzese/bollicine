package Model.DAO;

import Model.DTO.IndirizzoSpedDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IndirizzoSpedDTOTest {

    @Test
    void IndirizzoSpedEmptyConstructor(){
        IndirizzoSpedDTO sped = new IndirizzoSpedDTO();
        assertNotNull(sped);
    }

    @Test
    void IndirizzoSpedConstructor(){
        IndirizzoSpedDTO sped = new IndirizzoSpedDTO(1,"Alfredo","Cornacchia","indirizzo",84084,"città","provincia","casa",1);
        assertNotNull(sped);
    }

    @Test
    void getIdIndirizzo() {
        IndirizzoSpedDTO sped = new IndirizzoSpedDTO(1,"Alfredo","Cornacchia","indirizzo",84084,"città","provincia","casa",1);
        assertEquals(1,sped.getIdIndirizzo());
    }

    @Test
    void setIdIndirizzo() {
        IndirizzoSpedDTO sped = new IndirizzoSpedDTO();
        sped.setIdIndirizzo(1);

        assertEquals(1,sped.getIdIndirizzo());
    }

    @Test
    void getNome() {
        IndirizzoSpedDTO sped = new IndirizzoSpedDTO(1,"Alfredo","Cornacchia","indirizzo",84084,"città","provincia","casa",1);
        assertEquals("Alfredo",sped.getNome());
    }

    @Test
    void setNome() {
        IndirizzoSpedDTO sped = new IndirizzoSpedDTO();
        sped.setNome("Alfredo");

        assertEquals("Alfredo",sped.getNome());
    }

    @Test
    void getCognome() {
        IndirizzoSpedDTO sped = new IndirizzoSpedDTO(1,"Alfredo","Cornacchia","indirizzo",84084,"città","provincia","casa",1);
        assertEquals("Cornacchia",sped.getCognome());
    }

    @Test
    void setCognome() {
        IndirizzoSpedDTO sped = new IndirizzoSpedDTO();
        sped.setCognome("Cornacchia");

        assertEquals("Cornacchia",sped.getCognome());
    }

    @Test
    void getIndirizzo() {
        IndirizzoSpedDTO sped = new IndirizzoSpedDTO(1,"Alfredo","Cornacchia","indirizzo",84084,"città","provincia","casa",1);
        assertEquals("indirizzo",sped.getIndirizzo());
    }

    @Test
    void setIndirizzo() {
        IndirizzoSpedDTO sped = new IndirizzoSpedDTO();
        sped.setIndirizzo("indirizzo");

        assertEquals("indirizzo",sped.getIndirizzo());
    }

    @Test
    void getCap() {
        IndirizzoSpedDTO sped = new IndirizzoSpedDTO(1,"Alfredo","Cornacchia","indirizzo",84084,"città","provincia","casa",1);
        assertEquals(84084,sped.getCap());
    }

    @Test
    void setCap() {
        IndirizzoSpedDTO sped = new IndirizzoSpedDTO();
        sped.setCap(84084);

        assertEquals(84084,sped.getCap());
    }

    @Test
    void getCittà() {
        IndirizzoSpedDTO sped = new IndirizzoSpedDTO(1,"Alfredo","Cornacchia","indirizzo",84084,"città","provincia","casa",1);
        assertEquals("città",sped.getCittà());
    }

    @Test
    void setCittà() {
        IndirizzoSpedDTO sped = new IndirizzoSpedDTO();
        sped.setCittà("città");

        assertEquals("città",sped.getCittà());
    }

    @Test
    void getProvincia() {
        IndirizzoSpedDTO sped = new IndirizzoSpedDTO(1,"Alfredo","Cornacchia","indirizzo",84084,"città","provincia","casa",1);
        assertEquals("provincia",sped.getProvincia());
    }

    @Test
    void setProvincia() {
        IndirizzoSpedDTO sped = new IndirizzoSpedDTO();
        sped.setProvincia("provincia");

        assertEquals("provincia",sped.getProvincia());
    }

    @Test
    void getAlias() {
        IndirizzoSpedDTO sped = new IndirizzoSpedDTO(1,"Alfredo","Cornacchia","indirizzo",84084,"città","provincia","casa",1);
        assertEquals("casa",sped.getAlias());
    }

    @Test
    void setAlias() {
        IndirizzoSpedDTO sped = new IndirizzoSpedDTO();
        sped.setAlias("casa");

        assertEquals("casa",sped.getAlias());
    }

    @Test
    void getIdAccount() {
        IndirizzoSpedDTO sped = new IndirizzoSpedDTO(1,"Alfredo","Cornacchia","indirizzo",84084,"città","provincia","casa",1);
        assertEquals(1,sped.getIdAccount());
    }

    @Test
    void setIdAccount() {
        IndirizzoSpedDTO sped = new IndirizzoSpedDTO();
        sped.setIdAccount(1);

        assertEquals(1,sped.getIdAccount());
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}