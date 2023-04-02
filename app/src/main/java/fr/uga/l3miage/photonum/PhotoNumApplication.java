package fr.uga.l3miage.photonum;

import fr.uga.l3miage.photonum.data.domain.*;
import fr.uga.l3miage.photonum.data.domain.enums.Status;
import fr.uga.l3miage.photonum.data.repo.ClientRepository;
import fr.uga.l3miage.photonum.data.repo.ImpressionRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@SpringBootApplication
public class PhotoNumApplication implements CommandLineRunner {

    @Autowired
    private EntityManager entityManager;
    public static void main(String[] args) {
        SpringApplication.run(PhotoNumApplication.class, args);
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        Client mouad = new Client();
        mouad.setFirstName("Mouad");
        mouad.setLastName("Uchiha");
        mouad.setEmail("mouad.uchiha@gmail.com");
        entityManager.persist(mouad);
        Commande mouadCommande = new Commande();
        mouadCommande.setClient(mouad);
        mouadCommande.setCreatedate(Date.from(Instant.now()));
        mouadCommande.setStatus(Status.EXPEDIE);
        mouadCommande.setArticles(Set.of());
        mouadCommande.setTotalPrice(1000.0);
        entityManager.persist(mouadCommande);
    }
}