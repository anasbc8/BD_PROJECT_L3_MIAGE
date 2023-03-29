package fr.uga.l3miage.photonum.service;

import fr.uga.l3miage.photonum.data.domain.Album;
import fr.uga.l3miage.photonum.data.domain.Impression;
import fr.uga.l3miage.photonum.data.domain.Page;
import fr.uga.l3miage.photonum.data.domain.Photo;
import fr.uga.l3miage.photonum.data.repo.AlbumRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@Transactional
public class AlbumServiceImpl implements AlbumService {


    private AlbumRepository albumRepository;

    @Autowired
    public AlbumServiceImpl(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }


    @Override
    public Album save(Album album) {

        return albumRepository.save(album);
    }

    @Override
    public Album get(String id) throws EntityNotFoundException {
        return albumRepository.get(id);
    }

    @Override
    public List<Album> list() {
        return albumRepository.all();
    }

    @Override
    public void delete(String id) throws EntityNotFoundException {
        Album imp = get(id);
        if (imp == null) {
            throw new EntityNotFoundException("album  with id=%d not found".formatted(id));
        } else {
            albumRepository.delete(imp);
        }

    }

    @Override
    public Album update(Album album) throws EntityNotFoundException {
        return albumRepository.save(album);
    }
}
