package fr.uga.l3miage.photonum.data.repo;

import fr.uga.l3miage.photonum.data.domain.Cadre;
import fr.uga.l3miage.photonum.data.domain.Tirage;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CadreRepository implements CRUDRepository<String,Cadre> {

    private final EntityManager entityManager;

    @Autowired
    public CadreRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Cadre save(Cadre entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public Cadre get(String id) {

        return entityManager.find(Cadre.class, id);
    }

    @Override
    public void delete(Cadre entity) {
        entityManager.remove(entity);
    }

    @Override
    public List<Cadre> all() {

        return entityManager.createQuery("from Cadre", Cadre.class).getResultList();
    }
}
