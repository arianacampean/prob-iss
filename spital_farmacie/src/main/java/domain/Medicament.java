package domain;

import java.util.Objects;

public class Medicament extends Entity{
    private int id;
    private String nume;
    private int cantitate_pe_stoc;

    public Medicament(){}
    public Medicament(String nume, int cantitate_pe_stoc) {
        this.nume = nume;
        this.cantitate_pe_stoc = cantitate_pe_stoc;
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

    public int getCantitate_pe_stoc() {
        return cantitate_pe_stoc;
    }

    public void setCantitate_pe_stoc(int cantitate_pe_stoc) {
        this.cantitate_pe_stoc = cantitate_pe_stoc;
    }

    @Override
    public String toString() {
        return "Medicament{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                ", cantitate_pe_stoc=" + cantitate_pe_stoc +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Medicament that = (Medicament) o;
        return id == that.id &&
                cantitate_pe_stoc == that.cantitate_pe_stoc &&
                Objects.equals(nume, that.nume);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nume, cantitate_pe_stoc);
    }
}
