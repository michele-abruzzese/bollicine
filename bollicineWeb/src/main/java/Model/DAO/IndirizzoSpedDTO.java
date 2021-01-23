package Model.DAO;

public class IndirizzoSpedDTO {
    int idIndirizzo;
    String nome;
    String cognome;
    String indirizzo;
    int cap;
    String città;
    String provincia;
    String alias;
    int idAccount;

    public IndirizzoSpedDTO() {
    }

    public IndirizzoSpedDTO(int idIndirizzo, String nome, String cognome, String indirizzo, int cap, String città, String provincia, String alias, int idAccount) {
        this.idIndirizzo = idIndirizzo;
        this.nome = nome;
        this.cognome = cognome;
        this.indirizzo = indirizzo;
        this.cap = cap;
        this.città = città;
        this.provincia = provincia;
        this.alias = alias;
        this.idAccount = idAccount;
    }

    public int getIdIndirizzo() {
        return idIndirizzo;
    }

    public void setIdIndirizzo(int idIndirizzo) {
        this.idIndirizzo = idIndirizzo;
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

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public int getCap() {
        return cap;
    }

    public void setCap(int cap) {
        this.cap = cap;
    }

    public String getCittà() {
        return città;
    }

    public void setCittà(String città) {
        this.città = città;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public int getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(int idAccount) {
        this.idAccount = idAccount;
    }
}
