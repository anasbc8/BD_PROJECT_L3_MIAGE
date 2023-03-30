package fr.uga.l3miage.photonum.data.repo;

import fr.uga.l3miage.photonum.data.domain.Article;
import fr.uga.l3miage.photonum.data.domain.Commande;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
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
        String query = "select a FROM Article a order by a.quantity" ;
        return entityManager.createQuery(query, Article.class).getResultList();
    }

    public void update(Article article) {
        Query query = entityManager.createQuery(
                "UPDATE Article a SET a.quantity = :quantity, a.prixTotal = :prixTotal, a.impression = :impression WHERE a.id = :id"
        );
        query.setParameter("quantity", article.getQuantity());
        query.setParameter("prixTotal", article.getPrixTotal());
        query.setParameter("impression", article.getImpression());
        query.setParameter("id", article.getId());
        query.executeUpdate();
    }

}
