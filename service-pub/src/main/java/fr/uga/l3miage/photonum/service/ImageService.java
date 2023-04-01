package fr.uga.l3miage.photonum.service;

import fr.uga.l3miage.photonum.data.domain.Image;
import fr.uga.l3miage.photonum.data.domain.Client;
import fr.uga.l3miage.photonum.service.base.BaseService;

import java.util.List;


public interface ImageService extends BaseService<Image, String> {

    public Image save(Client owner, Image image) ;

    public Image get(String id) throws EntityNotFoundException;

    public void delete(String id) throws EntityNotFoundException;

    //find image by path
    public Image findByPath(String path) throws EntityNotFoundException;

    //get images from owner
    public List<Image> findByOwner(String owner) throws EntityNotFoundException;

    public List<Image> all() throws EntityNotFoundException;

    void deleteImageByOwnerAndPath(String clientId, String imagePath) throws EntityNotFoundException;

    void updateImage(String clientId, String imageId, String newMetadata, double newResolution, boolean isShared) throws EntityNotFoundException;

    public List<Image> findImageByOwnerAndPath(String clientId, String imagePath);

    public List<Image> getSharedImages();

}
