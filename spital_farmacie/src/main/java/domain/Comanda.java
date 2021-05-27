package domain;

import java.util.Objects;

public class Comanda extends Entity{
    private int id;
    private int id_medicament;
    private int nr_comanda;
    private int cantitate;
    private Stare stare;
    private int sectie;

    public Comanda(){};
    public Comanda(int id_medicament,int nr_comanda, int cantitate, Stare stare, int sectie) {
        this.nr_comanda = nr_comanda;
        this.id_medicament=id_medicament;
        this.cantitate = cantitate;
        this.stare = stare;
        this.sectie = sectie;
    }

    public int getId_medicament() {
        return id_medicament;
    }

    public void setId_medicament(int id_medicament) {
        this.id_medicament = id_medicament;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNr_comanda() {
        return nr_comanda;
    }

    public void setNr_comanda(int nr_comanda) {
        this.nr_comanda = nr_comanda;
    }

    public int getCantitate() {
        return cantitate;
    }

    public void setCantitate(int cantitate) {
        this.cantitate = cantitate;
    }

    public Stare getStare() {
        return stare;
    }

    public void setStare(Stare stare) {
        this.stare = stare;
    }

    public int getSectie() {
        return sectie;
    }

    public void setSectie(int sectie) {
        this.sectie = sectie;
    }

    @Override
    public String toString() {
        return "id_med=" + id_medicament +
                ", nr_com=" + nr_comanda +
                ", cantitate=" + cantitate +
                ", stare=" + stare +
                ", sectie=" + sectie;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comanda comanda = (Comanda) o;
        return id == comanda.id &&
                id_medicament == comanda.id_medicament &&
                nr_comanda == comanda.nr_comanda &&
                cantitate == comanda.cantitate &&
                sectie == comanda.sectie &&
                stare == comanda.stare;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, id_medicament, nr_comanda, cantitate, stare, sectie);
    }
}
