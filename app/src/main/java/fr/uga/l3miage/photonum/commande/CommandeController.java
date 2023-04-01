package fr.uga.l3miage.photonum.commande;

import fr.uga.l3miage.photonum.data.domain.Client;
import fr.uga.l3miage.photonum.data.domain.Commande;
import fr.uga.l3miage.photonum.data.domain.Image;
import fr.uga.l3miage.photonum.image.ImageDTO;
import fr.uga.l3miage.photonum.image.ImageMapper;
import fr.uga.l3miage.photonum.service.ClientService;
import fr.uga.l3miage.photonum.service.CommandeService;
import fr.uga.l3miage.photonum.service.EntityNotFoundException;
import fr.uga.l3miage.photonum.service.ImageService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(value = "/api/v1", produces = "application/json")
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

    @GetMapping("/commandes/{commandeId}")
    public ResponseEntity<CommandeDTO> getCommandeById(@PathVariable String commandeId) throws EntityNotFoundException {
        Commande commande = commandeService.get(commandeId);
        if (commande == null) {
            throw new EntityNotFoundException("commande not found");
        }
        CommandeDTO commandeDTO = commandeMapper.entityToDTO(commande);
        return  ResponseEntity.ok(commandeDTO);
    }
    @GetMapping("/commandes")
    public ResponseEntity<Collection<CommandeDTO>> getAllCommandes() throws EntityNotFoundException {
        Collection<Commande> commandes = commandeService.list();
        Collection<CommandeDTO> commandesdto = commandeMapper.entityToDTO(commandes);
        return ResponseEntity.ok(commandesdto);
    }

    @PostMapping("/addCommande/{id}")
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

    //Manque Update
    @DeleteMapping("/deleteCommande/{clientId}/{commandId}")
    public ResponseEntity<String> deleteImage(@PathVariable("clientId") @NotNull String ownerId,
                                              @PathVariable("commandId") @NotNull String commandId) throws EntityNotFoundException {
        Client owner = clientService.get(ownerId);
        Commande commandeToDelete = commandeService.get(commandId);
        if (commandeToDelete == null || owner == null) {
            throw new EntityNotFoundException("commande or owner not found ") ;
        }

        commandeService.delete(commandId);
        return ResponseEntity.ok("Commande deleted successfully.");
    }


}
