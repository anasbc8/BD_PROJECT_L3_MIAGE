package fr.uga.l3miage.photonum.service;
import fr.uga.l3miage.photonum.data.domain.Image;
import fr.uga.l3miage.photonum.data.domain.Client;
import fr.uga.l3miage.photonum.data.repo.ImageRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@Transactional
public class ImageServiceImpl implements ImageService {

    private ImageRepository imageRepository;

    private EntityManager entityManager;

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
    public Image get(String id) throws EntityNotFoundException {
        return imageRepository.get(id);
    }

    @Override
    public Collection<Image> list() {
        return imageRepository.all();
    }

    @Override
    public Image update(Image image) throws EntityNotFoundException {
        return imageRepository.save(image);
    }

    @Override
    public void delete(String id) throws EntityNotFoundException {
        imageRepository.delete(imageRepository.get(id));
    }

    @Override
    public Image findByPath(String path) throws EntityNotFoundException{
        return (Image) imageRepository.findImageByPath(path);
    }

    @Override
    public Image findByOwner(String owner) throws EntityNotFoundException{
        return null;
    }

    private void bindImageToClient(Client owner, Image image) {
        owner.addImage(image);
        image.setOwner(owner);
    }

}
