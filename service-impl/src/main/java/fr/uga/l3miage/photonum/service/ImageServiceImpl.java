package fr.uga.l3miage.photonum.service;
import fr.uga.l3miage.photonum.data.domain.Image;
import fr.uga.l3miage.photonum.data.domain.Client;
import fr.uga.l3miage.photonum.data.repo.ImageRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    @Autowired
    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public Image save(Client owner, Image image) {
        imageRepository.save(image);
        bindImageToClient(owner, image);
        return image;
    }

    @Override
    public Image get(String id) {
        return imageRepository.get(id);
    }

    @Override
    public Collection<Image> list() {
        return imageRepository.all();
    }

    @Override
    public Image update(Image image) {
        return imageRepository.save(image);
    }

    @Override
    public void delete(String id) {
        imageRepository.delete(imageRepository.get(id));
    }

    @Override
    public Image findById(String id) {
        return imageRepository.findImageById(id);
    }

    @Override
    public Image findByPath(String path) {
        return imageRepository.findImageByPath(path);
    }

    @Override
    public List<Image> findByOwner(String owner) {
        return imageRepository.findImagesByOwner(owner);
    }

    @Override
    public List<Image> all() {
        return imageRepository.all();
    }

    @Override
    public void deleteImageById(String id) {
        imageRepository.deleteImageById(id);
    }

    @Override
    public void deleteImageByPath(String path) {
        imageRepository.deleteImageByPath(path);
    }

    @Override
    public void deleteImagesByOwner(String owner) {
        imageRepository.deleteImagesByOwner(owner);
    }

    @Override
    public Image updateImageIsShared(String imageId, boolean isShared) {
        return imageRepository.updateImageIsShared(imageId, isShared);
    }

    @Override
    public void updateImagePath(String imageId, String newPath) {
        imageRepository.updateImagePath(imageId, newPath);
    }

    @Override
    public List<Image> getSharedImages() {
        return imageRepository.getSharedImages();
    }

    private void bindImageToClient(Client owner, Image image) {
        owner.addImage(image);
        image.setOwner(owner);
    }
}
