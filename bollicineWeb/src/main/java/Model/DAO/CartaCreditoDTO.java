package Model.DAO;

public class CartaCreditoDTO {
    int idCartaCredito;
    String nome;
    String cognome;
    int numero;
    int ccv;
    String scandenza;
    int idAccount;

    public CartaCreditoDTO() {
    }

    public CartaCreditoDTO(String nome, String cognome, int numero, int ccv, String scandenza, int idAccount) {
        this.idCartaCredito=-1;
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

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
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
    public String toString() {
        return "CartaCreditoDTO{" +
                "idCartaCredito=" + idCartaCredito +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", numero='" + numero + '\'' +
                ", ccv='" + ccv + '\'' +
                ", scandenza='" + scandenza + '\'' +
                ", idAccount=" + idAccount +
                '}';
    }
}
