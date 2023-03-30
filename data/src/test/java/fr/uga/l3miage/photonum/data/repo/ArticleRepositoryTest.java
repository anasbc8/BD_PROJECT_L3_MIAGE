package fr.uga.l3miage.photonum.data.repo;

import fr.uga.l3miage.photonum.data.domain.*;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static fr.uga.l3miage.photonum.data.repo.Utils.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ArticleRepositoryTest extends Base{

    @Autowired
    private ArticleRepository articleRepository;


    @Test
    void saveArticleTest() {
        // Création d'un objet Article
        Article article = new Article();
        article.setQuantity(10);
        article.setPrixTotal(50.0);

        // Persist dans la base de données
        entityManager.persist(article);

        // Vérification de l'existence de l'article dans la base de données
        entityManager.flush();
        assertThat(articleRepository.get(article.getId())).isEqualTo(article);
    }

    @Test
    void getArticleTest() {
        // Création d'un objet Article
        Article article = new Article();
        article.setQuantity(10);
        article.setPrixTotal(50.0);

        // Persist dans la base de données
        entityManager.persist(article);

        // Récupération de l'article depuis la base de données
        entityManager.flush();
        Article getArticle = articleRepository.get(article.getId());

        // Vérification de l'égalité entre l'article créé et celui récupéré
        assertThat(getArticle).isEqualTo(article);
    }

    @Test
    void deleteArticleTest() {
        // Création d'un objet Article
        Article article = new Article();
        article.setQuantity(10);
        article.setPrixTotal(50.0);

        // Persist dans la base de données
        entityManager.persist(article);

        // Suppression de l'article de la base de données
        articleRepository.delete(article);

        // Vérification que l'article n'existe plus dans la base de données
        entityManager.flush();
        assertThat(articleRepository.get(article.getId())).isNull();
    }

    @Test
    void getAllArticlesTest() {
        // Création d'un objet Article
        Article article1 = new Article();
        article1.setQuantity(10);
        article1.setPrixTotal(50.0);

        // Persist article1 dans la base de données
        entityManager.persist(article1);

        // Création de l'article2
        Article article2 = new Article();
        article2.setQuantity(20);
        article2.setPrixTotal(100.0);

        // Persist article2 dans la base de données
        entityManager.persist(article2);

        // Récupération de tous les articles depuis la base de données
        entityManager.flush();
        List<Article> articles = articleRepository.all();

        // Vérification que la liste des articles contient les deux articles créés
        assertThat(articles).containsExactly(article1, article2);
    }

    @Test
    void updateArticleTest() {
        // Création d'un objet Article
        Article article = new Article();
        article.setQuantity(10);
        article.setPrixTotal(50.0);

        // Persist dans la base de données
        entityManager.persist(article);

        // Modification de la quantité de l'article
        article.setQuantity(20);

        // Appel de la méthode update pour mettre à jour l'article dans la base de données
        articleRepository.update(article);

        // Récupération de l'article modifié depuis la base de données
        entityManager.flush();
        Article updatedArticle = entityManager.find(Article.class, article.getId());

        // Vérification que la quantité de l'article a bien été mise à jour
        assertThat(updatedArticle.getQuantity()).isEqualTo(20);
    }

}
