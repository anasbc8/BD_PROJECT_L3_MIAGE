package fr.uga.l3miage.photonum.data.repo;

import java.util.List;

/**
 * Base interface to having consistent naming.
 * @param <I>
 * @param <E>
 */
public interface CRUDRepository<I,E> {
     E save(E enitiy);

    E get(I id);

    void delete(E entity);

    List<E> all();
}
