package fr.uga.l3miage.photonum.calendrier;

import fr.uga.l3miage.photonum.data.domain.Calendrier;
import fr.uga.l3miage.photonum.service.EntityNotFoundException;
import fr.uga.l3miage.photonum.service.CalendrierService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/calendriers")
public class CalendrierController {

    private final CalendrierService calendrierService;

    private final CalendrierMapper calendrierMapper;

    public CalendrierController(CalendrierService calendrierService, CalendrierMapper calendrierMapper) {
        this.calendrierService = calendrierService;
        this.calendrierMapper = calendrierMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CalendrierDTO> getCalendrierById(@PathVariable String id) throws EntityNotFoundException {
        Calendrier calendrier = calendrierService.get(id);
        if (calendrier == null) {
            return ResponseEntity.notFound().build();
        }
        CalendrierDTO calendrierDTO = calendrierMapper.entityToDTO(calendrier);
        return ResponseEntity.ok(calendrierDTO);
    }

    @GetMapping("/all")
    public ResponseEntity<Collection<CalendrierDTO>> getAllCalendriers() throws EntityNotFoundException {
        Collection<Calendrier> calendriers = calendrierService.list();
        Collection<CalendrierDTO> calendrierDTOs = calendrierMapper.entityToDTO(calendriers);
        return ResponseEntity.ok(calendrierDTOs);
    }

    @PostMapping("/addCalendrier/")
    public ResponseEntity<CalendrierDTO> createCalendrier(@RequestBody @Valid CalendrierDTO calendrierDTO) {
        Calendrier calendrier = calendrierMapper.dtoToEntity(calendrierDTO);
        calendrierService.save(calendrier);
        return ResponseEntity.ok(calendrierDTO);
    }

    @PutMapping("/updateCalendrier/{id}")
    public ResponseEntity<CalendrierDTO> updateCalendrier(@PathVariable String id, @RequestBody @Valid CalendrierDTO calendrierDTO) throws EntityNotFoundException {
        Calendrier calendrier = calendrierMapper.dtoToEntity(calendrierDTO);
        calendrierService.update(calendrier);
        return ResponseEntity.ok(calendrierDTO);
    }

    @DeleteMapping("/deleteCalendrier/{id}")
    public ResponseEntity<CalendrierDTO> deleteCalendrier(@PathVariable String id) throws EntityNotFoundException {
        Calendrier calendrier = calendrierService.get(id);
        if (calendrier == null) {
            return ResponseEntity.notFound().build();
        }
        calendrierService.delete(calendrier.getId());
        return ResponseEntity.ok().build();
    }
    
}
