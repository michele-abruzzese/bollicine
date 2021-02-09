package Model.DTO;

import java.util.Objects;

public class DettaglioOrdineDTO {
    int idPodotto;
    int idOrdine;
    int quantità;
    double prezzoUnit;
    int iva;

    public DettaglioOrdineDTO() {
    }

    public DettaglioOrdineDTO(int idPodotto, int idOrdine, int quantità, double prezzoUnit, int iva) {
        this.idPodotto = idPodotto;
        this.idOrdine = idOrdine;
        this.quantità = quantità;
        this.prezzoUnit = prezzoUnit;
        this.iva = iva;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DettaglioOrdineDTO that = (DettaglioOrdineDTO) o;
        return idPodotto == that.idPodotto && idOrdine == that.idOrdine && quantità == that.quantità && Double.compare(that.prezzoUnit, prezzoUnit) == 0 && iva == that.iva;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPodotto, idOrdine, quantità, prezzoUnit, iva);
    }
}
