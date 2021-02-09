package Model.DTO;

import java.util.Objects;

public class OrdineDTO {
    int idOrdine;
    double totOrdine;
    String data;
    String metodoPag;
    int idCarta;
    int idIndirizzo;
    int idAccount;

    public OrdineDTO() {
    }

    public OrdineDTO(int idOrdine, double totOrdine, String data, String metodoPag, int idCarta, int idIndirizzo, int idAccount) {
        this.idOrdine = idOrdine;
        this.totOrdine = totOrdine;
        this.data = data;
        this.metodoPag = metodoPag;
        this.idCarta = idCarta;
        this.idIndirizzo = idIndirizzo;
        this.idAccount = idAccount;
    }

    public int getIdOrdine() {
        return idOrdine;
    }

    public void setIdOrdine(int idOrdine) {
        this.idOrdine = idOrdine;
    }

    public double getTotOrdine() {
        return totOrdine;
    }

    public void setTotOrdine(float totOrdine) {
        this.totOrdine = totOrdine;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMetodoPag() {
        return metodoPag;
    }

    public void setMetodoPag(String metodoPag) {
        this.metodoPag = metodoPag;
    }

    public int getIdCarta() {
        return idCarta;
    }

    public void setIdCarta(int idCarta) {
        this.idCarta = idCarta;
    }

    public int getIdIndirizzo() {
        return idIndirizzo;
    }

    public void setIdIndirizzo(int idIndirizzo) {
        this.idIndirizzo = idIndirizzo;
    }

    public int getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(int idAccount) {
        this.idAccount = idAccount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrdineDTO ordineDTO = (OrdineDTO) o;
        return idOrdine == ordineDTO.idOrdine && Double.compare(ordineDTO.totOrdine, totOrdine) == 0 && idCarta == ordineDTO.idCarta && idIndirizzo == ordineDTO.idIndirizzo && idAccount == ordineDTO.idAccount && data.equals(ordineDTO.data) && metodoPag.equals(ordineDTO.metodoPag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOrdine, totOrdine, data, metodoPag, idCarta, idIndirizzo, idAccount);
    }
}
