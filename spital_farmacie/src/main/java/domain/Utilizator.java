package domain;

import java.util.Objects;

public class Utilizator extends Entity{
    private int id;
    private String nume;
    private String prenume;
    private String username;
    private String parola;
    private Lucreaza luc;

    public Utilizator(){};
    public Utilizator( String nume, String prenume, String username, String parola, Lucreaza luc) {
        this.nume = nume;
        this.prenume = prenume;
        this.username = username;
        this.parola = parola;
        this.luc = luc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }

    public Lucreaza getLuc() {
        return luc;
    }

    public void setLuc(Lucreaza luc) {
        this.luc = luc;
    }

    @Override
    public String toString() {
        return "Utilizator{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                ", prenume='" + prenume + '\'' +
                ", username='" + username + '\'' +
                ", parola='" + parola + '\'' +
                ", luc=" + luc +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Utilizator that = (Utilizator) o;
        return id == that.id &&
                Objects.equals(nume, that.nume) &&
                Objects.equals(prenume, that.prenume) &&
                Objects.equals(username, that.username) &&
                Objects.equals(parola, that.parola) &&
                luc == that.luc;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nume, prenume, username, parola, luc);
    }
}
