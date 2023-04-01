package fr.uga.l3miage.photonum.image;

import fr.uga.l3miage.photonum.data.domain.Client;
import fr.uga.l3miage.photonum.data.domain.Image;
import fr.uga.l3miage.photonum.service.EntityNotFoundException;
import fr.uga.l3miage.photonum.service.ImageService;
import fr.uga.l3miage.photonum.service.ClientService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
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
        return clientMapper.entityToDTO(clientService.list());
    }

    @GetMapping("/{id}")
    public ClientDTO getClientById(@PathVariable Long id) {
        try {
            return clientMapper.entityToDTO(clientService.get(id));
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, null, e);
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClientDTO createClient(@RequestBody @Valid ClientDTO client) {
        try {
            final var entity = clientService.save(clientMapper.dtoToEntity(client));
            return clientMapper.entityToDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, null, e);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, null, e);
        }
    }

    @PutMapping("/{id}")
    public ClientDTO updateClient(@PathVariable Long id, @RequestBody @Valid ClientDTO client) {
        try {
            if (client.getId().equals(id)) {
                var updated = clientService.update(clientMapper.dtoToEntity(client));
                return clientMapper.entityToDTO(updated);
            }
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, null, e);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, null, e);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClient(@PathVariable Long id) {
        try {
            clientService.delete(id);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, null, e);
        }
    }
}