package Model.DTO;

import java.util.Objects;

public class CartaCreditoDTO {
    int idCartaCredito;
    String nome;
    String cognome;
    Long numero;
    int ccv;
    String scandenza;
    int idAccount;

    public CartaCreditoDTO() {
    }

    public CartaCreditoDTO(int idCartaCredito, String nome, String cognome, Long numero, int ccv, String scandenza, int idAccount) {
        this.idCartaCredito = idCartaCredito;
        this.nome = nome;
        this.cognome = cognome;
        this.numero = numero;
        this.ccv = ccv;
        this.scandenza = scandenza;
        this.idAccount = idAccount;
    }

    public int getIdCartaCredito() {
        return idCartaCredito;
    }

    public void setIdCartaCredito(int idCartaCredito) {
        this.idCartaCredito = idCartaCredito;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public int getCcv() {
        return ccv;
    }

    public void setCcv(int ccv) {
        this.ccv = ccv;
    }

    public String getScandenza() {
        return scandenza;
    }

    public void setScandenza(String scandenza) {
        this.scandenza = scandenza;
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
        CartaCreditoDTO that = (CartaCreditoDTO) o;
        return idCartaCredito == that.idCartaCredito && ccv == that.ccv && idAccount == that.idAccount && nome.equals(that.nome) && cognome.equals(that.cognome) && numero.equals(that.numero) && scandenza.equals(that.scandenza);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCartaCredito, nome, cognome, numero, ccv, scandenza, idAccount);
    }
}
