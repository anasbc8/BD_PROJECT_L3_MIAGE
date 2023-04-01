package fr.uga.l3miage.photonum.client;

import fr.uga.l3miage.photonum.data.domain.Client;
import fr.uga.l3miage.photonum.data.domain.Image;
import fr.uga.l3miage.photonum.service.EntityNotFoundException;
import fr.uga.l3miage.photonum.service.ImageService;
import fr.uga.l3miage.photonum.service.ClientService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;

@RestController
@RequestMapping("client")
public class ClientController {

    private final ClientService clientService;
    private final ClientMapper clientMapper;

    @Autowired
    public ClientController(ClientService clientService, ClientMapper clientMapper) {
        this.clientService = clientService;
        this.clientMapper = clientMapper;
    }

    @GetMapping
    public Collection<ClientDTO> getAllClients() {
        return clientMapper.entityToDTO(clientService.all());
    }

    @GetMapping("/{id}")
    public ClientDTO getClientById(@PathVariable String id) throws EntityNotFoundException {
        return clientMapper.entityToDTO(clientService.get(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClientDTO createClient(@RequestBody @Valid ClientDTO client) throws EntityNotFoundException {
        try {
            final var entity = clientService.save(clientMapper.dtoToEntity(client));
            return clientMapper.entityToDTO(entity);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, null, e);
        }
    }

    @PutMapping("/{id}")
    public ClientDTO updateClient(@PathVariable Long id, @RequestBody @Valid ClientDTO client)
            throws EntityNotFoundException {
        try {
            if (client.id().equals(id)) {
                Client cliententity = clientMapper.dtoToEntity(client);
                clientService.updateClientInformation(cliententity.getId(), cliententity.getPassword(),
                        cliententity.getFirstName(), cliententity.getLastName());
                return clientMapper.entityToDTO(cliententity);
            }
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, null, e);
        }
    }

}
