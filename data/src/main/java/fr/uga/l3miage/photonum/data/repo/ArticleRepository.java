package fr.uga.l3miage.photonum.data.repo;

import fr.uga.l3miage.photonum.data.domain.Article;
import fr.uga.l3miage.photonum.data.domain.Commande;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ArticleRepository implements CRUDRepository<String, Article> {

    @PersistenceContext
    private EntityManager entityManager ;
    @Override
    public Article save(Article article) {
        entityManager.persist(article);
        return article ;
    }

    @Override
    public Article get(String id) {
        return entityManager.find(Article.class,id);
    }

    @Override
    public void delete(Article article) {
    entityManager.remove(article);
    }

    @Override
    public List<Article> all() {
        String query = "select a FROM Article order by a.quantity" ;
        return entityManager.createQuery(query, Article.class).getResultList();
    }
}
