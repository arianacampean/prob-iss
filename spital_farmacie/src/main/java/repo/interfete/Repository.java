package repo.interfete;

import domain.Entity;

public interface Repository<E extends Entity> {
    Iterable<E> findAll();
    void add(E entity);
    void update(E entity);
    void delete (E entity);
}
