package fr.uga.l3miage.photonum.service;

import fr.uga.l3miage.photonum.data.domain.Impression;
import fr.uga.l3miage.photonum.data.repo.ImpressionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@Transactional
public class ImpressionServiceImpl implements ImpressionService {


    private ImpressionRepository impressionRepository;

    @Autowired
    public ImpressionServiceImpl(ImpressionRepository authorRepository) {
        this.impressionRepository = authorRepository;
    }


    @Override
    public Impression save(Impression impression) {
        return impressionRepository.save(impression);
    }

    @Override
    public Impression get(Long id) throws EntityNotFoundException {
        return impressionRepository.get(id);
    }

    @Override
    public Collection<Impression> list() {
        return impressionRepository.all();
    }

    @Override
    public Impression update(Impression object) throws EntityNotFoundException {
        return null;
    }
}
