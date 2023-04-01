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
@RequestMapping("/images")
public class ImageController {
    private final ImageService imageService;
    private final ClientService clientService;
    private final ImageMapper imageMapper;

    public ImageController(ImageService imageService, ImageMapper imageMapper, ClientService clientService) {
        this.imageService = imageService;
        this.imageMapper = imageMapper;
        this.clientService = clientService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ImageDTO> getImageById(@PathVariable String id) throws EntityNotFoundException {
        Image image = imageService.get(id);
        if (image == null) {
            return ResponseEntity.notFound().build();
        }
        ImageDTO imageDTO = imageMapper.entityToDTO(image);
        return ResponseEntity.ok(imageDTO);
    }

    @GetMapping("/images")
    public ResponseEntity<Collection<ImageDTO>> getAllImages() throws EntityNotFoundException {
        Collection<Image> images = imageService.all();
        Collection<ImageDTO> imageDTOs = imageMapper.entityToDTO(images);
        return ResponseEntity.ok(imageDTOs);
    }

    @PostMapping("/addImage/{id}")
    public ImageDTO createImage(@PathVariable("id") @NotNull String ownerId, @Valid @RequestBody ImageDTO imageDTO) throws EntityNotFoundException {
        Image image = imageMapper.dtoToEntity(imageDTO);
        Client owner = clientService.get(ownerId); // Récupération du propriétaire de l'image
        if (owner == null) throw new EntityNotFoundException("client not found with id " + ownerId);
        Image savedImage = imageService.save(owner, image);
        ImageDTO savedImageDTO = imageMapper.entityToDTO(savedImage);
        return savedImageDTO;
    }

 /*   @PutMapping("/updateImage/{clientId}/{imageId}")
    public ImageDTO updateImage(@PathVariable("clientId") @NotNull String clientId,
                                @PathVariable("imageId") @NotNull String imageId,
                                @RequestBody ImageDTO imageUpdateDTO) throws EntityNotFoundException {
        Client owner = clientService.get(clientId);
        Image imageToUpdate = imageService.get(imageId);

        if (imageToUpdate == null) throw new EntityNotFoundException("Image not found with id " + imageId);
        if (owner == null) throw new EntityNotFoundException("client not found with id " + ownerId);

        imageToUpdate.setShared(imageUpdateDTO.isShared());

        imageToUpdate = imageService.updateImageIsShared(imageId,imageUpdateDTO.isShared());
        ImageDTO updatedImageDTO = imageMapper.entityToDTO(imageToUpdate);
        return updatedImageDTO;
    }
*/
    @DeleteMapping("/deleteImage/{clientId}/{imageId}")
    public ResponseEntity<String> deleteImage(@PathVariable("clientId") @NotNull String clientId,
                                              @PathVariable("imageId") @NotNull String imageId) throws EntityNotFoundException {
        Client owner = clientService.get(clientId);
        Image imageToDelete = imageService.get(imageId);
        if (imageToDelete == null) {
            return ResponseEntity.notFound().build();
        }
        if (!owner.getId().equals(clientId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        imageService.delete(imageId);
        return ResponseEntity.ok("Image deleted successfully.");
    }
}
