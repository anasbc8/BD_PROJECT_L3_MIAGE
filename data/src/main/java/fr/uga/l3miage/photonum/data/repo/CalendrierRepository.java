package fr.uga.l3miage.photonum.data.repo;

import fr.uga.l3miage.photonum.data.domain.Calendrier;
import fr.uga.l3miage.photonum.data.domain.Page;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public class CalendrierRepository implements CRUDRepository<String, Calendrier> {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Calendrier save(Calendrier cal) {
        entityManager.persist(cal);
        return cal;
    }

    @Override
    public Calendrier get(String id) {

        return entityManager.find(Calendrier.class, id);
    }

    @Override
    public void delete(Calendrier cal) {
        entityManager.remove(cal);
    }

    public void update(Set<Page> pages  ) {
        String query = "UPDATE Calendrier i SET i.pages=:pages ";
        entityManager.createQuery(query).
                setParameter("pages", pages).
                executeUpdate();

    }

    @Override
    public List<Calendrier> all() {
        String query = "SELECT a FROM Calendrier a";
        return entityManager.createQuery(query, Calendrier.class).getResultList();
    }


}