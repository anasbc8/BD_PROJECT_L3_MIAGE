package fr.uga.l3miage.photonum.service;

import fr.uga.l3miage.photonum.data.domain.Cadre;
import fr.uga.l3miage.photonum.data.repo.CadreRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@Transactional
public class CadreServiceImpl implements CadreService {
    private CadreRepository cadreRepository;

    @Autowired
    public CadreServiceImpl(CadreRepository cadreRepository) {
        this.cadreRepository = cadreRepository;
    }

    @Override
    public Collection<Cadre> list() {
        return cadreRepository.all();
    }

    @Override
    public Cadre update(Cadre cadre) throws EntityNotFoundException {
        return cadreRepository.save(cadre);
    }

    @Override
    public void delete(String id) {
        cadreRepository.delete(cadreRepository.get(id));
    }

    @Override
    public Cadre save(Cadre cadre) {
        return cadreRepository.save(cadre);
    }

    @Override
    public Cadre get(String id) {
        return cadreRepository.get(id);
    }

}
