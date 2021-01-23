package Model.DAO;

public class DettaglioOrdineDTO {
    int idPodotto;
    int idOrdine;
    int quantità;
    double prezzoUnit;
    int iva;

    public DettaglioOrdineDTO() {
    }

    public int getIdPodotto() {
        return idPodotto;
    }

    public void setIdPodotto(int idPodotto) {
        this.idPodotto = idPodotto;
    }

    public int getIdOrdine() {
        return idOrdine;
    }

    public void setIdOrdine(int idOrdine) {
        this.idOrdine = idOrdine;
    }

    public int getQuantità() {
        return quantità;
    }

    public void setQuantità(int quantità) {
        this.quantità = quantità;
    }

    public double getPrezzoUnit() {
        return prezzoUnit;
    }

    public void setPrezzoUnit(double prezzoUnit) {
        this.prezzoUnit = prezzoUnit;
    }

    public int getIva() {
        return iva;
    }

    public void setIva(int iva) {
        this.iva = iva;
    }
}
