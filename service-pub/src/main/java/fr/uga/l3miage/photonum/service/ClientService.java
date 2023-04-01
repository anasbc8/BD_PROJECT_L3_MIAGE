package fr.uga.l3miage.photonum.service;

import fr.uga.l3miage.photonum.data.domain.Client;
import fr.uga.l3miage.photonum.data.domain.Commande;

import java.util.List;

public interface ClientService {
    Client save(Client client);
    Client get(String id);
    void delete(Client client);
    List<Client> all();
    void updateClientInformation(String clientId, String password, String firstName, String lastName);
    List<Commande> getCommandesByClientId(String clientId);
}
