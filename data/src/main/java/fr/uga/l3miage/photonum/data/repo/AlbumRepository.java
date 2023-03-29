package fr.uga.l3miage.photonum.data.repo;

import fr.uga.l3miage.photonum.data.domain.Album;
import fr.uga.l3miage.photonum.data.domain.Page;
import fr.uga.l3miage.photonum.data.domain.Photo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Repository
public class AlbumRepository implements CRUDRepository<String, Album> {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Album save(Album album) {
        entityManager.persist(album);
        return album;
    }

    @Override
    public Album get(String id) {
        return entityManager.find(Album.class, id);
    }

    @Override
    public void delete(Album album) {
        entityManager.remove(album);
    }

    public void update(String title, Photo photoCover, Set<Page> pages ) {
        String query = "UPDATE Album i SET i.title = :title, i.photoCover = :photoCover" + ", i.pages=:pages ";
         entityManager.createQuery(query).
                setParameter("title", title).
                setParameter("photoCover", photoCover).
                setParameter("pages", pages).
                executeUpdate();

    }

    @Override
    public List<Album> all() {
        String query = "SELECT a FROM Album a";
        return entityManager.createQuery(query, Album.class).getResultList();
    }


}