package fr.uga.l3miage.photonum.service;

import fr.uga.l3miage.photonum.data.domain.Photo;
import fr.uga.l3miage.photonum.data.domain.Tirage;
import fr.uga.l3miage.photonum.service.base.BaseService;

import java.util.List;

public interface TirageService extends BaseService<Tirage,String> {
    public Tirage save(Tirage tirage);
    public Tirage get(String id);
    public Tirage update(Tirage tirage);

    public void delete(String id);

    public List<Photo> getPhotosByTirageId(String tirageId);

}
