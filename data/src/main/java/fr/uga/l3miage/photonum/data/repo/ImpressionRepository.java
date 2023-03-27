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

    //Création d'impression (tirage, album, agenda, etc) à partir du catalogue PhotoNum. Les
    //photos qui composent l'impression seront créées à partir de fichiers images téléchargés ou
    //bien à parti des fichiers images partagés par d'autres utilisateurs. On suppose les images
    //déjà uploadées

    //Création d'un tirage
    public void createTirage(String id, double price, Article article, Set<Photo> photos) {
        Tirage tirage = new Tirage();
        tirage.setId(id);
        tirage.setPrice(price);
        tirage.setArticle(article);
        tirage.setPhotos(photos);
        entityManager.persist(tirage);
    }

    //Création d'un album
    public void createAlbum(String id, double price, Article article, String title, Photo photoCover, Set<Page> pages) {
        Album album = new Album();
        album.setId(id);
        album.setPrice(price);
        album.setArticle(article);
        album.setTitle(title);
        album.setPhotoCover(photoCover);
        album.setPages(pages);
        entityManager.persist(album);
    }

    //Création d'un cadre
    public void createCadre(String id, double price, Article article, Set<Photo> photos) {
        Cadre cadre = new Cadre();
        cadre.setId(id);
        cadre.setPrice(price);
        cadre.setArticle(article);
        cadre.setPhotos(photos);
        entityManager.persist(cadre);
    }

    //Création d'un calendrier
    public void createCalendrier(String id, double price, Article article, Set<Page> pages) {
        Calendrier calendrier = new Calendrier();
        calendrier.setId(id);
        calendrier.setPrice(price);
        calendrier.setArticle(article);
        calendrier.setPages(pages);
        entityManager.persist(calendrier);
    }


}
