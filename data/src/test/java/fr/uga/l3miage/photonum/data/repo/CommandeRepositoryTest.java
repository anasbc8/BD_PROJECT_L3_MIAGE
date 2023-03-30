package fr.uga.l3miage.photonum.data.repo;

import fr.uga.l3miage.photonum.data.domain.Article;
import fr.uga.l3miage.photonum.data.domain.Client;
import fr.uga.l3miage.photonum.data.domain.Commande;
import fr.uga.l3miage.photonum.data.domain.Image;
import fr.uga.l3miage.photonum.data.domain.enums.Status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static fr.uga.l3miage.photonum.data.repo.Utils.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CommandeRepositoryTest extends Base {

    @Autowired
    private CommandeRepositoy commandeRepository;

    Client createClient() {
        Client owner = new Client();
        owner.setLastName("Doe");
        owner.setFirstName("John");
        owner.setEmail("johndoe@example.com");
        owner.setPassword("secret");

        return owner;
    }

    Commande buildCommande(Date date, Double price, Status status) {
        Commande commande = new Commande();
        commande.setCreatedate(date);
        commande.setTotalPrice(price);
        commande.setStatus(status);

        return commande;
    }

    Set<Article> createArticles() {
        Article article = new Article();
        Set<Article> articles = new HashSet();
        articles.add(article);
        return articles;
    }

    @Test
    void saveCommandeTest() {
        Commande commande = buildCommande(new Date(), 100.0, Status.EN_COURS);

        Client client = createClient();

        Set<Article> articles = createArticles();

        commande.setClient(client);
        commande.setArticles(articles);
        entityManager.persist(client);

        for(Article article : articles){
            entityManager.persist(article);
        }
        entityManager.persist(commande);
        entityManager.flush();
        Commande savedCommande = commandeRepository.save(commande);
        assertThat(savedCommande.getId()).isNotNull();

    }

    @Test
    void getAllCommandesTest() {

        // Création de la première commande
        Commande commande1 = buildCommande(new Date(), 100.0, Status.EN_COURS);
        Set<Article> articles1 = createArticles();
        Client client1 = createClient();
        commande1.setClient(client1);
        commande1.setArticles(articles1);
        entityManager.persist(client1);
        for (Article article : articles1) {
            article.setCommande(commande1);
            entityManager.persist(article);
        }
        entityManager.persist(commande1);

        // Création de la deuxième commande
        Commande commande2 = buildCommande(new Date(), 500.0, Status.EN_COURS);
        Set<Article> articles2 = createArticles();
        Client client2 = createClient();
        commande2.setClient(client2);
        commande2.setArticles(articles2);
        entityManager.persist(client2);
        for (Article article : articles2) {
            article.setCommande(commande2);
            entityManager.persist(article);
        }
        entityManager.persist(commande2);

        entityManager.flush();

        // test
        List<Commande> allCommandes = commandeRepository.all();
        assertThat(allCommandes).isNotEmpty();
        assertThat(allCommandes.stream().map(Commande::getId)).containsExactlyInAnyOrder(commande1.getId(), commande2.getId());
    }


    @Test
    void getCommandeTest() {
        Commande commande = buildCommande(new Date(), 100.0, Status.EN_COURS);

        Client client = createClient();
        entityManager.persist(client);
        entityManager.flush();

        Set<Article> articles = createArticles();
        for (Article article : articles) {
            article.setCommande(commande);
            entityManager.persist(article);
        }

        commande.setClient(client);
        commande.setArticles(articles);

        Commande savedCommande = commandeRepository.save(commande);
        entityManager.flush();

        Commande getCommande = commandeRepository.get(savedCommande.getId());
        assertThat(getCommande).isNotNull();
        assertThat(getCommande).isEqualTo(savedCommande);
    }

    @Test
    void deleteCommandeTest() {
        Commande commande = buildCommande(new Date(), 100.0, Status.EN_COURS);

        Client client = createClient();
        entityManager.persist(client);
        entityManager.flush();

        Set<Article> articles = createArticles();
        for (Article article : articles) {
            entityManager.persist(article);
        }

        commande.setClient(client);
        commande.setArticles(articles);

        Commande savedCommande = commandeRepository.save(commande);

        // Supprimer les articles associés à la commande
        for (Article article : articles) {
            entityManager.remove(article);
        }

        commandeRepository.delete(savedCommande);
        entityManager.flush();

        Commande deletedCommande = commandeRepository.get(savedCommande.getId());
        assertThat(deletedCommande).isNull();
    }


    @Test
    void updateCommandeTest() {
        Client client = createClient();
        entityManager.persist(client);
        entityManager.flush();

        Set<Article> articles = createArticles();
        for (Article article : articles) {
            entityManager.persist(article);
        }

        Commande commande = buildCommande(new Date(), 500.0, Status.EN_COURS);

        commande.setClient(client);
        entityManager.persist(commande);

        assertNotNull(commande.getId());

        commandeRepository.updateCommande(commande.getId(), Status.EXPEDIE);

        Commande updatedCommande = entityManager.find(Commande.class, commande.getId());

        assertEquals(Status.EXPEDIE, updatedCommande.getStatus());
    }

}
