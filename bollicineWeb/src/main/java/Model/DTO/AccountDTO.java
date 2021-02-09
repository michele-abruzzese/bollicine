package Model.DTO;

import java.util.Objects;

public class AccountDTO {
    int id;
    String nome;
    String cognome;
    String email;
    String password;
    String stato;
    String tipo;

    public AccountDTO() {
    }

    public AccountDTO(int id, String nome, String cognome, String email, String password, String stato, String tipo) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.password = password;
        this.stato = stato;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountDTO that = (AccountDTO) o;
        return id == that.id && nome.equals(that.nome) && cognome.equals(that.cognome) && email.equals(that.email) && password.equals(that.password) && stato.equals(that.stato) && tipo.equals(that.tipo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, cognome, email, password, stato, tipo);
    }
}


