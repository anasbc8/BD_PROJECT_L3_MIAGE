package fr.uga.l3miage.photonum.data.repo;

import fr.uga.l3miage.photonum.data.domain.Image;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ImageRepository implements CRUDRepository<String, Image> {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public ImageRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Image save(Image image) {
        entityManager.persist(image);
        return image;
    }

    @Override
    public Image get(String id) {
        return entityManager.find(Image.class, id);
    }

    @Override
    public void delete(Image image) {
        entityManager.remove(image);
    }

    @Override
    public List<Image> all() {
        String query = "SELECT i FROM Image i";
        return entityManager.createQuery(query, Image.class).getResultList();
    }

    //supprimer une image avec son path
    /**
     * @param imagePath
    */
    public void deleteImageByPath(String imagePath) {
        String query = "DELETE FROM Image i WHERE i.path = :imagePath";
        entityManager.createQuery(query)
                .setParameter("imagePath", imagePath)
                .executeUpdate();
    }
    
    //supprimer une image avec son id
    /**
     * @param imageId
    */
    public void deleteImageById(String imageId) {
        String query = "DELETE FROM Image i WHERE i.id = :imageId";
        entityManager.createQuery(query)
                .setParameter("imageId", imageId)
                .executeUpdate();
    }

    //supprimer les images d'un client
    /**
     * @param clientId
    */
    public void deleteImagesByOwner(String clientId) {
        String query = "DELETE FROM Image i WHERE i.owner.id = :clientId";
        entityManager.createQuery(query)
                .setParameter("clientId", clientId)
                .executeUpdate();
    }

    /**
     * @param imageId
     * @param isShared
     */
    public Image updateImageIsShared(String imageId, boolean isShared) {
        Image imageToUpdate = entityManager.find(Image.class, imageId);
        if (imageToUpdate.isShared() && !isShared) {
            //If the image is already shared we can't set it to false
            //throw an exception saying that the image is shared and can't be set to private
            throw new IllegalArgumentException("The image is already shared, can't be set to private again !");
        }
        imageToUpdate.setShared(isShared);
        entityManager.persist(imageToUpdate);
        return imageToUpdate;
    }

    /**
     * @param imageId
     * @param newPath
     */
    public void updateImagePath(String imageId, String newPath) {
        Image imageToUpdate = entityManager.find(Image.class, imageId);
        imageToUpdate.setPath(newPath);
        entityManager.persist(imageToUpdate);
    }

    /**
     * @param imageId
     * @return Image
    */
    public Image findImageById(String imageId) {
        String query = "Select i FROM Image i WHERE i.id = :imageId";
        return (Image) entityManager.createQuery(query)
                .setParameter("imageId", imageId)
                .getSingleResult();
    }

    /**
     * @param imagePath
     * @return Image
    */
    public Image findImageByPath(String imagePath) {
        String query = "Select i FROM Image i WHERE i.path = :imagePath";
        return (Image) entityManager.createQuery(query)
                .setParameter("imagePath", imagePath)
                .getSingleResult();
    }
    
    /**
     * @param clientId
     * @return List<Image>
    */
    public List<Image> findImagesByOwner(String clientId) {
        String query = "Select i FROM Image i WHERE i.owner.id = :clientId";
        return entityManager.createQuery(query, Image.class)
                .setParameter("clientId", clientId)
                .getResultList();
    }

    //récupérer la liste des images partagées
    public List<Image> getSharedImages() {
        String query = "SELECT i FROM Image i WHERE i.isShared = true";
        return entityManager.createQuery(query, Image.class).getResultList();
    }

}
