package Model.DAO;

public class OrdineDTO {
    int idOrdine;
    float totOrdine;
    String data;
    String metodoPag;
    int idCarta;
    int idIndirizzo;
    int idAccount;

    public OrdineDTO() {
    }

    public int getIdOrdine() {
        return idOrdine;
    }

    public void setIdOrdine(int idOrdine) {
        this.idOrdine = idOrdine;
    }

    public float getTotOrdine() {
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
}
