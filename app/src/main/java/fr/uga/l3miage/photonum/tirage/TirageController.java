package fr.uga.l3miage.photonum.tirage;

import fr.uga.l3miage.photonum.data.domain.Tirage;
import fr.uga.l3miage.photonum.service.EntityNotFoundException;
import fr.uga.l3miage.photonum.service.TirageService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/tirages")
public class TirageController {

    private final TirageService tirageService;

    private final TirageMapper tirageMapper;

    public TirageController(TirageService tirageService, TirageMapper tirageMapper) {
        this.tirageService = tirageService;
        this.tirageMapper = tirageMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TirageDTO> getTirageById(@PathVariable String id) throws EntityNotFoundException {
        Tirage tirage = tirageService.get(id);
        if (tirage == null) {
            return ResponseEntity.notFound().build();
        }
        TirageDTO tirageDTO = tirageMapper.entityToDTO(tirage);
        return ResponseEntity.ok(tirageDTO);
    }

    @GetMapping("/tirages")
    public ResponseEntity<Collection<TirageDTO>> getAllTirages() throws EntityNotFoundException {
        Collection<Tirage> tirages = tirageService.list();
        Collection<TirageDTO> tirageDTOs = tirageMapper.entityToDTO(tirages);
        return ResponseEntity.ok(tirageDTOs);
    }

    @PostMapping
    public ResponseEntity<TirageDTO> createTirage(@RequestBody @Valid TirageDTO tirageDTO) {
        Tirage tirage = tirageMapper.dtoToEntity(tirageDTO);
        tirageService.save(tirage);
        return ResponseEntity.ok(tirageDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TirageDTO> updateTirage(@PathVariable String id, @RequestBody @Valid TirageDTO tirageDTO) throws EntityNotFoundException {
        Tirage tirage = tirageMapper.dtoToEntity(tirageDTO);
        tirageService.update(tirage);
        return ResponseEntity.ok(tirageDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TirageDTO> deleteTirage(@PathVariable String id) throws EntityNotFoundException {
        Tirage tirage = tirageService.get(id);
        if (tirage == null) {
            return ResponseEntity.notFound().build();
        }
        tirageService.delete(tirage.getId());
        return ResponseEntity.ok().build();
    }
    
}
