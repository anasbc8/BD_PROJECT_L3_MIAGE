package fr.uga.l3miage.photonum.image;

import fr.uga.l3miage.photonum.data.domain.Client;
import fr.uga.l3miage.photonum.data.domain.Image;
import fr.uga.l3miage.photonum.service.EntityNotFoundException;
import fr.uga.l3miage.photonum.service.ImageService;
import fr.uga.l3miage.photonum.service.ClientService;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(value = "/api/v1/image", produces = "application/json")
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

    @GetMapping("/all")
    public ResponseEntity<Collection<ImageDTO>> getAllImages() throws EntityNotFoundException {
        Collection<Image> images = imageService.all();
        Collection<ImageDTO> imageDTOs = imageMapper.entityToDTO(images);
        return ResponseEntity.ok(imageDTOs);
    }

    @PostMapping("/addImage/{id}")
    public ResponseEntity<ImageDTO> createImage(@PathVariable("id") @NotNull String ownerId,@RequestBody ImageDTO imageDTO) throws EntityNotFoundException {
        Client owner = clientService.get(ownerId); // Récupération du propriétaire de l'image
        if (owner == null) throw new EntityNotFoundException("client not found with id " + ownerId);
        Image image = imageMapper.dtoToEntity(imageDTO);
        Image savedImage = imageService.save(owner, image);
        return ResponseEntity.ok(imageMapper.entityToDTO(savedImage));
    }

    @PutMapping("/updateImage/{clientId}/{imageId}")
    public ResponseEntity<String> updateSharedImage(@PathVariable("clientId") @NotNull String clientId,
                                                      @PathVariable("imageId") @NotNull String imageId,
                                                      @RequestBody ImageDTO imageUpdateDTO) throws EntityNotFoundException {

        Client owner = clientService.get(clientId);
        Image imageToUpdate = imageService.get(imageId);

        if (imageToUpdate == null) return ResponseEntity.badRequest().body("Image not found with id " + imageId);
        if (owner == null) return ResponseEntity.badRequest().body("client not found with id " + clientId);
        if (imageToUpdate.isShared()) return ResponseEntity.badRequest().body("The image is already shared / can't be set to private again");

        try {
            imageToUpdate.setShared(imageUpdateDTO.isShared());
            imageToUpdate = imageService.updateImageIsShared(imageId,imageUpdateDTO.isShared());
            return ResponseEntity.ok("Image with id : "+imageMapper.entityToDTO(imageToUpdate).id()+" has been up to dated to isShared : "+imageUpdateDTO.isShared());
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body("Invalid input: " + ex.getMessage());
        }
    }


    @DeleteMapping("/deleteImage/{clientId}/{imageId}")
    public ResponseEntity<String> deleteImage(@PathVariable("clientId") @NotNull String clientId,
                                              @PathVariable("imageId") @NotNull String imageId) throws EntityNotFoundException {
        Client owner = clientService.get(clientId);
        Image imageToDelete = imageService.get(imageId);
        if (imageToDelete == null) return ResponseEntity.badRequest().body("Image not found with id " + imageId);
        if (owner == null) return ResponseEntity.badRequest().body("client not found with id " + clientId);
        if (imageToDelete.isShared()) return ResponseEntity.badRequest().body("The image with id '"+imageId +"' is shared and can not be deleted");

        imageService.delete(imageId);
        return ResponseEntity.ok("Image deleted successfully.");
    }
}
