package repo.interfete;

import domain.Utilizator;
import jdk.jshell.execution.Util;

public interface RepoUtilizator extends Repository<Utilizator>
{
    Utilizator get_oneUtil(String username,String parola);
}
