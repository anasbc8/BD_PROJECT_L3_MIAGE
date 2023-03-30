package fr.uga.l3miage.photonum.data.repo;

import fr.uga.l3miage.photonum.data.domain.Calendrier;
import fr.uga.l3miage.photonum.data.domain.Image;
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

    public void updateCalendrier(String calendrierId ,Set<Page> pages ) {
        Calendrier calendrierToUpdate = entityManager.find(Calendrier.class,calendrierId );
        calendrierToUpdate.setPages(pages);
        entityManager.persist(calendrierToUpdate);
    }

    @Override
    public List<Calendrier> all() {
        String query = "SELECT a FROM Calendrier a";
        return entityManager.createQuery(query, Calendrier.class).getResultList();
    }
    public void deleteCalendrierById(String calendrierID) {
        // create and execute the JPQL delete query
        String query = "DELETE FROM Calendrier i WHERE i.id = :calendrierID ";
        entityManager.createQuery(query)
                .setParameter("calendrierID", calendrierID)
                .executeUpdate();
    }

}