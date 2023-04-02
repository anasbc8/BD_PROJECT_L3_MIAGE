package fr.uga.l3miage.photonum.cadre;

import fr.uga.l3miage.photonum.data.domain.Cadre;
import fr.uga.l3miage.photonum.data.domain.Tirage;
import fr.uga.l3miage.photonum.service.EntityNotFoundException;
import fr.uga.l3miage.photonum.service.CadreService;
import fr.uga.l3miage.photonum.tirage.TirageDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
@RestController
@RequestMapping(value = "/api/v1/cadre", produces = "application/json")
public class CadreController {
    private final CadreService cadreService;
    private final CadreMapper cadreMapper;

    public CadreController(CadreService cadreService, CadreMapper cadreMapper) {
        this.cadreService = cadreService;
        this.cadreMapper = cadreMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CadreDTO> getCadrebyId(@PathVariable String id) throws EntityNotFoundException {
        Cadre cadre = cadreService.get(id);
        if (cadre == null) {
            return ResponseEntity.notFound().build();
        }
        CadreDTO cadreDTO = cadreMapper.entityToDTO(cadre);
        return ResponseEntity.ok(cadreDTO);
    }

    @GetMapping("/all")
    public ResponseEntity<Collection<CadreDTO>> getAllCadres() throws EntityNotFoundException {
        Collection<Cadre> cadres = cadreService.list();
        Collection<CadreDTO> cadreDTOs = cadreMapper.entityToDTO(cadres);
        return ResponseEntity.ok(cadreDTOs);
    }

    @PostMapping("/addCadre/")
    public ResponseEntity<CadreDTO> createCadre(@RequestBody @Valid CadreDTO cadreDTO) {
        Cadre cadre = cadreMapper.dtoToEntity(cadreDTO);
        cadreService.save(cadre);
        return ResponseEntity.ok(cadreDTO);
    }

    @PutMapping("/updateCadre/{id}")
    public ResponseEntity<CadreDTO> updateCadre(@PathVariable String id, @RequestBody @Valid CadreDTO cadreDTO) throws EntityNotFoundException {
        Cadre cadre = cadreMapper.dtoToEntity(cadreDTO);
        cadreService.update(cadre);
        return ResponseEntity.ok(cadreDTO);
    }

    @DeleteMapping("/deleteCadre/{id}")
    public ResponseEntity<CadreDTO> deleteTirage(@PathVariable String id) throws EntityNotFoundException {
        Cadre cadre = cadreService.get(id);
        if (cadre == null) {
            return ResponseEntity.notFound().build();
        }
        cadreService.delete(cadre.getId());
        return ResponseEntity.ok().build();
    }
}
