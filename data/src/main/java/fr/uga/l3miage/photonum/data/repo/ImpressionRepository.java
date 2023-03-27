package fr.uga.l3miage.photonum.data.repo;

import fr.uga.l3miage.photonum.data.domain.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public class ImpressionRepository implements CRUDRepository<Long, Impression> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Impression save(Impression impression) {
        entityManager.persist(impression);
        return impression;
    }

    @Override
    public Impression get(Long id) {

        return entityManager.find(Impression.class, id);
    }

    @Override
    public void delete(Impression impression) {

        entityManager.remove(impression);
    }


    @Override
    public List<Impression> all() {
        //obtenir les references des impressions et leur prix  triés par reference
        String query = "select reference,price  FROM Impression order by reference";

        // retourne la liste des impressions
        return entityManager.createQuery(query, Impression.class).getResultList();
    }

    
}
