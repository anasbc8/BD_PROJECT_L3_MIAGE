package fr.uga.l3miage.photonum.service;

import fr.uga.l3miage.photonum.data.domain.Album;
import fr.uga.l3miage.photonum.service.base.BaseService;

import java.util.Collection;

public interface AlbumService extends BaseService<Album, String> {

    Album save(Album album);
    void delete(String id) throws EntityNotFoundException;
    public Album update(Album album) throws EntityNotFoundException ;
}