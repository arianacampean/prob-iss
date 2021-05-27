package repo.interfete;

import domain.Comanda;

public interface RepoComanda extends Repository<Comanda> {
    Comanda get_oneCom(Long id);

}
