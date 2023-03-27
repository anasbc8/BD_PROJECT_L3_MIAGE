package fr.uga.l3miage.photonum.service;

import fr.uga.l3miage.photonum.data.domain.Image;
import fr.uga.l3miage.photonum.data.domain.Client;
import fr.uga.l3miage.photonum.service.base.BaseService;


public interface ImageService extends BaseService<Image, String> {

    public Image save(Client owner, Image image) ;

    public void delete(String id) throws EntityNotFoundException;

    //find image by path
    public Image findByPath(String path) throws EntityNotFoundException;

    //get images from owner
    public Image findByOwner(String owner) throws EntityNotFoundException;



}
