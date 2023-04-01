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

    //supprimer une image avec l'owner id et son path
    public void deleteImageByOwnerAndPath(String clientId, String imagePath) {
        // create and execute the JPQL delete query
        String query = "DELETE FROM Image i WHERE i.owner.id = :clientId AND i.path = :imagePath";
        entityManager.createQuery(query)
                .setParameter("clientId", clientId)
                .setParameter("imagePath", imagePath)
                .executeUpdate();
    }


    public void updateImage(String clientId, String imageId, String newMetadata, double newResolution, boolean isShared) {
        Image imageToUpdate = entityManager.find(Image.class, imageId);
        imageToUpdate.setMetadata(newMetadata);
        imageToUpdate.setResolution(newResolution);
        imageToUpdate.setShared(isShared);
        entityManager.persist(imageToUpdate);
    }

    public List<Image> findImageByPath(String imagePath) {
        String query = "Select i FROM Image i WHERE i.path = :imagePath";
        return entityManager.createQuery(query)
                .setParameter("imagePath", imagePath)
                .getResultList();
    }
    public List<Image> findImageByOwnerAndPath(String clientId, String imagePath) {
        String query = "Select i FROM Image i WHERE i.owner.id = :clientId AND i.path = :imagePath";
        return entityManager.createQuery(query)
                .setParameter("clientId", clientId)
                .setParameter("imagePath", imagePath)
                .getResultList();
    }

    public List<Image> findImageByOwner(String clientId) {
        String query = "Select i FROM Image i WHERE i.owner.id = :clientId";
        return entityManager.createQuery(query)
                .setParameter("clientId", clientId)
                .getResultList();
    }

    //récupérer la liste des images partagées
    public List<Image> getSharedImages() {
        String query = "SELECT i FROM Image i WHERE i.isShared = true";
        return entityManager.createQuery(query, Image.class).getResultList();
    }





}
