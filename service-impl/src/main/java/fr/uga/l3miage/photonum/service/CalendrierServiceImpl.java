package fr.uga.l3miage.photonum.service;


import fr.uga.l3miage.photonum.data.domain.Calendrier;
import fr.uga.l3miage.photonum.data.repo.CalendrierRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class CalendrierServiceImpl implements CalendrierService {
    private Logger logger = LoggerFactory.getLogger(CalendrierServiceImpl.class);
    private CalendrierRepository calendrierRepository;

    @Autowired
    public CalendrierServiceImpl(CalendrierRepository calendrierRepository) {
        this.calendrierRepository = calendrierRepository;
    }


    @Override
    public Calendrier save(Calendrier calendrier) {

        return calendrierRepository.save(calendrier);
    }

    @Override
    public Calendrier get(String id) throws EntityNotFoundException {
        return calendrierRepository.get(id);
    }

    @Override
    public List<Calendrier> list() {
        return calendrierRepository.all();
    }

    @Override
    public void delete(String id) throws EntityNotFoundException {
        logger.info("deleting calendar with id {}", id);
        Calendrier foundedCalendar = get(id);
        if (Objects.isNull(foundedCalendar)) {
            throw new EntityNotFoundException("calendrier  with id=%d not found".formatted(id));
        }
        calendrierRepository.delete(foundedCalendar);

    }

    @Override
    public Calendrier update(Calendrier calendrier) throws EntityNotFoundException {
        return calendrierRepository.save(calendrier);
    }

}
