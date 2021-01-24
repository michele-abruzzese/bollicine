package Model.DAO;

public class ProdottoDTO {
    int idProdotto;
    String nome;
    String categoria;
    String descrizione;
    //path e file name mandato dalla servelet
    String immagine;
    String tipo;
    int annata;
    double prezzo;
    int disponibilità;

    public ProdottoDTO() {
    }

    public ProdottoDTO(int idProdotto, String nome, String categoria, String descrizione, String immagine, String tipo, int annata, double prezzo, int disponibilità) {
        this.idProdotto = idProdotto;
        this.nome = nome;
        this.categoria = categoria;
        this.descrizione = descrizione;
        this.immagine = immagine;
        this.tipo = tipo;
        this.annata = annata;
        this.prezzo = prezzo;
        this.disponibilità = disponibilità;
    }

    public int getIdProdotto() {
        return idProdotto;
    }

    public void setIdProdotto(int idProdotto) {
        this.idProdotto = idProdotto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getImmagine() {
        return immagine;
    }

    public void setImmagine(String immagine) {
        this.immagine = immagine;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getAnnata() {
        return annata;
    }

    public void setAnnata(int annata) {
        this.annata = annata;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public int getDisponibilità() {
        return disponibilità;
    }

    public void setDisponibilità(int disponibilità) {
        this.disponibilità = disponibilità;
    }
}
