package fr.uga.l3miage.photonum.data.repo;

import fr.uga.l3miage.photonum.data.domain.Commande;
import fr.uga.l3miage.photonum.data.domain.enums.Status;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommandeRepositoy implements CRUDRepository<String, Commande> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Commande save(Commande commande) {
        entityManager.persist(commande);
        return commande;
    }

    @Override
    public Commande get(String id) {
        return entityManager.find(Commande.class, id);
    }

    @Override
    public void delete(Commande commande) {
        entityManager.remove(commande);
    }

    @Override
    public List<Commande> all() {
        String query = "select c FROM Commande c ";
        return entityManager.createQuery(query, Commande.class).getResultList();
    }

    public List<Commande> getCommandes(Long clientId) {
        String jpql = "SELECT c FROM Commande c WHERE c.client.id = :clientId";
        return entityManager.createQuery(jpql, Commande.class)
                .setParameter("clientId", clientId)
                .getResultList();
    }

    public Commande updateCommande(String commandeId, Status newStatus) {
        Commande commande = entityManager.find(Commande.class, commandeId);
        if (commande != null) {
            commande.setStatus(newStatus);
        }
        return commande ;
    }

}
