package fr.uga.l3miage.photonum.commande;

import fr.uga.l3miage.photonum.data.domain.Client;
import fr.uga.l3miage.photonum.data.domain.Commande;
import fr.uga.l3miage.photonum.service.ClientService;
import fr.uga.l3miage.photonum.service.CommandeService;
import fr.uga.l3miage.photonum.service.EntityNotFoundException;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(value = "/api/v1/commande", produces = "application/json")
public class CommandeController {
    private final CommandeService commandeService;
    private final ClientService clientService;
    private final CommandeMapper commandeMapper;

    @Autowired
    public CommandeController(CommandeService commandeService, ClientService clientService, CommandeMapper commandeMapper) {
        this.commandeService = commandeService;
        this.clientService = clientService;
        this.commandeMapper = commandeMapper;
    }

    @GetMapping("/{commandeId}")
    public ResponseEntity<CommandeDTO> getCommandeById(@PathVariable String commandeId) throws EntityNotFoundException {
        Commande commande = commandeService.get(commandeId);
        if (commande == null) {
            throw new EntityNotFoundException("commande not found");
        }
        CommandeDTO commandeDTO = commandeMapper.entityToDTO(commande);
        return ResponseEntity.ok(commandeDTO);
    }

    @GetMapping("/all")
    public ResponseEntity<Collection<CommandeDTO>> getAllCommandes() throws EntityNotFoundException {
        Collection<Commande> commandes = commandeService.list();
        Collection<CommandeDTO> commandesdto = commandeMapper.entityToDTO(commandes);
        return ResponseEntity.ok(commandesdto);
    }

    @PostMapping("/{id}")
    public CommandeDTO createCommande(@PathVariable("id") @NotNull String ownerId, @RequestBody CommandeDTO commandeDTO) throws EntityNotFoundException {
        // get the owner
        Client owner = clientService.get(ownerId);
        if (owner == null) throw new EntityNotFoundException("client not found with id " + ownerId);
        Commande commande = commandeMapper.dtoToEntity(commandeDTO);
        // set the client to the command
        commande.setClient(owner);
        Commande savedImage = commandeService.save(commande);
        return commandeMapper.entityToDTO(savedImage);
    }

    @DeleteMapping("/clientId/{clientId}/commandeId/{commandId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<String> deleteImage(@PathVariable("clientId") @NotNull String ownerId,
                                              @PathVariable("commandId") @NotNull String commandId) throws EntityNotFoundException {
        Client owner = clientService.get(ownerId);
        Commande commandeToDelete = commandeService.get(commandId);
        if (commandeToDelete == null || owner == null) {
            throw new EntityNotFoundException("commande or owner not found ");
        }

        commandeService.delete(commandId);
        return ResponseEntity.ok("Commande deleted successfully.");
    }

    @PutMapping("/updateCommande/{clientId}/{commandeId}")
    public ResponseEntity<String> updateSharedImage(@PathVariable("clientId") @NotNull String clientId,
                                                    @PathVariable("commandeId") @NotNull String commandeId,
                                                    @RequestBody CommandeDTO commandeUpdateDTO) throws EntityNotFoundException {

        Client owner = clientService.get(clientId);
        Commande commandeToUpdate = commandeService.get(commandeId);

        if (commandeToUpdate == null)
            return ResponseEntity.badRequest().body("Commande not found with id " + commandeId);
        if (owner == null) return ResponseEntity.badRequest().body("client not found with id " + clientId);
        commandeToUpdate.setStatus(commandeUpdateDTO.status());
        var commandeToUpdate2 = commandeService.update(commandeToUpdate);
        return ResponseEntity.ok("Commande Updated ");

    }

}
