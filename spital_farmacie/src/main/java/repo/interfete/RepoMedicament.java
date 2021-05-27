package repo.interfete;

import domain.Medicament;

public interface RepoMedicament extends Repository<Medicament> {
    Medicament get_oneMed(long id);
}
