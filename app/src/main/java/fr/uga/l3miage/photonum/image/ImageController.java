package fr.uga.l3miage.photonum.image;

import fr.uga.l3miage.photonum.data.domain.Client;
import fr.uga.l3miage.photonum.data.domain.Image;
import fr.uga.l3miage.photonum.service.EntityNotFoundException;
import fr.uga.l3miage.photonum.service.ImageService;
import fr.uga.l3miage.photonum.service.ClientService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


}
