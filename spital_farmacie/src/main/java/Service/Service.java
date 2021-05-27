package Service;

import domain.Comanda;
import domain.Medicament;
import domain.Utilizator;
import repo.interfete.RepoComanda;
import repo.interfete.RepoMedicament;
import repo.interfete.RepoUtilizator;

public class Service {
    private RepoComanda repo_com;
    private RepoMedicament repo_med;
    private RepoUtilizator repo_util;

    public Service(RepoComanda repo_com, RepoMedicament repo_med, RepoUtilizator repo_util) {
        this.repo_com = repo_com;
        this.repo_med = repo_med;
        this.repo_util = repo_util;
    }
    public Iterable<Comanda>get_allCom(){return repo_com.findAll();}
    public Iterable<Medicament>get_allMed(){return repo_med.findAll();}
    public Iterable<Utilizator>get_allUtil(){return repo_util.findAll();}
    public void add_com(Comanda com){ repo_com.add( com);}
    public Utilizator login(String username,String parola){return repo_util.get_oneUtil(username,parola);}
    public void detele_com(Comanda c){repo_com.delete(c);}
    public void update_com(Comanda c){repo_com.update(c);}
    public void update_med(Medicament m){repo_med.update(m);}

}
