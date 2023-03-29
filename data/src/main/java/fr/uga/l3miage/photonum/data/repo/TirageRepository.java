package fr.uga.l3miage.photonum.data.repo;

import fr.uga.l3miage.photonum.data.domain.Tirage;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TirageRepository implements CRUDRepository<String,Tirage> {

    private final EntityManager entityManager;

    @Autowired
    public TirageRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Tirage save(Tirage entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public Tirage get(String id) {
        return entityManager.find(Tirage.class, id);

    }

    @Override
    public void delete(Tirage entity) {
        entityManager.remove(entity);
    }

    @Override
    public List<Tirage> all() {
        return entityManager.createQuery("from Tirage", Tirage.class).getResultList();
    }
}
