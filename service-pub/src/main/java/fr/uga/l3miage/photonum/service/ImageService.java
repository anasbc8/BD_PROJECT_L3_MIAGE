package fr.uga.l3miage.photonum.service;

import fr.uga.l3miage.photonum.data.domain.Image;
import fr.uga.l3miage.photonum.data.domain.Client;
import fr.uga.l3miage.photonum.service.base.BaseService;

import java.util.List;


public interface ImageService extends BaseService<Image, String> {

    //save image
    Image save(Client owner, Image image) ;

    //get image by id
    Image get(String id) throws EntityNotFoundException;

    //delete image by id
    void delete(String id) throws EntityNotFoundException;

    //find image by id
    Image findById(String id) throws EntityNotFoundException;

    //find image by path
    Image findByPath(String path) throws EntityNotFoundException;

    //get images from owner
    List<Image> findByOwner(String owner) throws EntityNotFoundException;

    //get all images
    List<Image> all() throws EntityNotFoundException;

    //get image by id
    void deleteImageById(String imageId) throws EntityNotFoundException;

    //get image by path
    void deleteImageByPath(String path) throws EntityNotFoundException;

    //get image by owner
    void deleteImagesByOwner(String owner) throws EntityNotFoundException;

    //update image isShared
    Image updateImageIsShared(String imageId, boolean isShared) throws EntityNotFoundException;

    //update image path
    void updateImagePath(String imageId, String path) throws EntityNotFoundException;

    //get all shared images
    public List<Image> getSharedImages();

}
