package fr.uga.l3miage.photonum.data.repo;

import fr.uga.l3miage.photonum.data.domain.Client;
import fr.uga.l3miage.photonum.data.domain.Commande;
import fr.uga.l3miage.photonum.data.domain.enums.Status;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

class ClientRepositoryTest extends Base {
    
    private Client createClient() {
        Client client = new Client();
        client.setFirstName("John");
        client.setLastName("Doe");
        client.setEmail("johndoe@example.com");
        client.setPassword("secret");
        return client;
    }

    @Autowired
    private ClientRepository clientRepository;

    @Test
    void saveClientTest() {
        Client client = createClient();

        clientRepository.save(client);
        entityManager.persist(client);
        entityManager.flush();

        assertThat(client.getId()).isNotNull();
    }

    @Test
    void getClientTest() {
        Client client = createClient();
        clientRepository.save(client);
        entityManager.persist(client);
        entityManager.flush();

        Client getClient = clientRepository.get(client.getId());
        assertThat(getClient).isNotNull();
        assertThat(getClient).isEqualTo(client);
    }

    @Test
    void deleteClientTest() {
        Client client = createClient();
        clientRepository.save(client);
        entityManager.persist(client);
        entityManager.flush();

        clientRepository.delete(client);
        assertThat(clientRepository.get(client.getId())).isNull();
    }

    @Test
    public void testUpdateClient() {
        // Création d'un client
        Client client = createClient();

        // Persist dans la base de données
        entityManager.persist(client);

        entityManager.flush();
        Client retrievedClient = clientRepository.get(client.getId());

        // Modification du client
        String newPassword = "passwordJdid";
        String newFirstName = "Mohamed";
        String newLastName = "VI";

        // Update dans la base de données
        clientRepository.updateClientInformation(client.getId(), newPassword, newFirstName, newLastName);
        entityManager.flush();
        entityManager.clear(); // Clear le cache de l'entity manager sinon le test ne marche pas
        retrievedClient = clientRepository.get(client.getId());

        // Vérification
        assertEquals(newPassword, retrievedClient.getPassword());
        assertEquals(newFirstName, retrievedClient.getFirstName());
        assertEquals(newLastName, retrievedClient.getLastName());
    }



    @Test
    public void testGetCommandesByClientId() {
        // Création d'un client
        Client client = createClient();

        // Persist dans la base de données
        entityManager.persist(client);

        // Création de deux commandes pour le client
        Commande commande1 = new Commande();
        commande1.setClient(client);
        commande1.setTotalPrice(309.0);
        commande1.setCreatedate(new Date());
        commande1.setStatus(Status.EN_COURS);
        entityManager.persist(commande1);

        Commande commande2 = new Commande();
        commande2.setClient(client);
        commande2.setTotalPrice(39.0);
        commande2.setCreatedate(new Date());
        commande2.setStatus(Status.EN_COURS);
        entityManager.persist(commande2);

        entityManager.flush();
        List<Commande> commandes = clientRepository.getCommandesByClientId(client.getId());

        // Verify that the method returns the expected number of orders
        assertThat(commandes).hasSize(2);
    }
}
