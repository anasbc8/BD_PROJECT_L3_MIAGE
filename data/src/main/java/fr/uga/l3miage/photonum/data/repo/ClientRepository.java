package fr.uga.l3miage.photonum.data.repo;

import fr.uga.l3miage.photonum.data.domain.Client;
import fr.uga.l3miage.photonum.data.domain.Commande;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClientRepository implements CRUDRepository<String, Client> {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public Client save(Client client) {
        entityManager.persist(client);
        return client;
    }

    @Override
    public Client get(String id) {
        return entityManager.find(Client.class, id);
    }

    @Override
    public void delete(Client client) {
        entityManager.remove(client);
    }

    @Override
    public List<Client> all() {
        String query = "select c FROM Client c" ;
        return entityManager.createQuery(query, Client.class).getResultList();
    }

    public Client getClientById(Long clientId) {
        String query = "SELECT c FROM Client c WHERE c.id = :clientId";
        return entityManager.createQuery(query, Client.class)
        .setParameter("clientId", clientId)
        .getSingleResult();
    }

    public void updateClientInformation(String clientId, String password, String firstName, String lastName) {
        String query = "UPDATE Client c SET c.password = :password, c.firstName = :firstName, c.lastName = :lastName WHERE c.id = :clientId";
        entityManager.createQuery(query)
                .setParameter("password", password)
                .setParameter("firstName", firstName)
                .setParameter("lastName", lastName)
                .setParameter("clientId", clientId)
                .executeUpdate();
    }
    
    public List<Commande> getCommandesByClientId(String clientId) {
        String query = "SELECT c.commandes FROM Client c WHERE c.id = :clientId";
        return entityManager.createQuery(query, Commande.class)
                .setParameter("clientId", clientId)
                .getResultList();
    }
    
    
}
