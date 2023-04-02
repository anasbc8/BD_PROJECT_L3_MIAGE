package fr.uga.l3miage.photonum.album;

import fr.uga.l3miage.photonum.data.domain.Album;
import fr.uga.l3miage.photonum.service.AlbumService;
import fr.uga.l3miage.photonum.service.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(value = "/api/v1/album", produces = "application/json")
public class AlbumController {

   private final AlbumService albumService;
   private final AlbumMapper albumMapper;

    public AlbumController(AlbumService albumService, AlbumMapper albumMapper) {
        this.albumService = albumService;
        this.albumMapper = albumMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlbumDTO> getAlbumById(@PathVariable String id) throws EntityNotFoundException {
        Album album = albumService.get(id);
        if (album == null) {
            return ResponseEntity.notFound().build();
        }
        AlbumDTO albumDTO = albumMapper.entityToDTO(album);
        return ResponseEntity.ok(albumDTO);
    }

    @GetMapping("/all")
    public ResponseEntity<Collection<AlbumDTO>> getAllAlbums() throws EntityNotFoundException {
        Collection<Album> albums = albumService.list();
        Collection<AlbumDTO> albumDTOs = albumMapper.entityToDTO(albums);
        return ResponseEntity.ok(albumDTOs);
    }

    @PostMapping("/addAlbum/")
    public ResponseEntity<AlbumDTO> createAlbum(@RequestBody @Valid AlbumDTO albumDTO) {
        Album album = albumMapper.dtoToEntity(albumDTO);
        albumService.save(album);
        return ResponseEntity.ok(albumDTO);
    }

    @PutMapping("/updateAlbum/{id}")
    public ResponseEntity<AlbumDTO> updateAlbum(@PathVariable String id, @RequestBody @Valid AlbumDTO albumDTO) throws EntityNotFoundException {
        Album album = albumMapper.dtoToEntity(albumDTO);
        albumService.update(album);
        return ResponseEntity.ok(albumDTO);
    }

    @DeleteMapping("/deleteAlbum/{id}")
    public ResponseEntity<AlbumDTO> deleteAlbum(@PathVariable String id) throws EntityNotFoundException {
        Album album = albumService.get(id);
        if ( album== null) {
            return ResponseEntity.notFound().build();
        }
        albumService.delete(album.getId());
        return ResponseEntity.ok().build();
    }
}
