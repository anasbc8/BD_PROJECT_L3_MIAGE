package fr.uga.l3miage.photonum.service;


import fr.uga.l3miage.photonum.data.domain.Client;
import fr.uga.l3miage.photonum.data.domain.Commande;
import fr.uga.l3miage.photonum.data.repo.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Client save(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Client get(String id) {
        return clientRepository.get(id);
    }

    @Override
    public void delete(Client client) {
        clientRepository.delete(client);
    }

    @Override
    public List<Client> all() {
        return clientRepository.all();
    }

    @Override
    public void updateClientInformation(String clientId, String password, String firstName, String lastName) {
        clientRepository.updateClientInformation(clientId, password, firstName, lastName);
    }

    @Override
    public List<Commande> getCommandesByClientId(String clientId) {
        return clientRepository.getCommandesByClientId(clientId);
    }
    
}
