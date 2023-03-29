package fr.uga.l3miage.photonum.data.repo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import fr.uga.l3miage.photonum.data.domain.Adress;
import fr.uga.l3miage.photonum.data.domain.Client;
import fr.uga.l3miage.photonum.data.domain.Commande;
import fr.uga.l3miage.photonum.data.domain.Image;

@DataJpaTest
class ImageRepositoryTest extends Base {

    @Autowired
    EntityManager entityManager;
    @Autowired
    private ImageRepository imageRepository;

    Client createClient(){
        Client owner = new Client();
        owner.setLastName("Doe");
        owner.setFirstName("John");
        owner.setEmail("johndoe@example.com");
        owner.setPassword("secret");

        return owner;
    }

    Image createImage(String path,String metadata,double resolution, boolean shared, Client owner){
        Image image = new Image();
        image.setPath(path);
        image.setMetadata(metadata);
        image.setResolution(resolution);
        image.setShared(shared);
        image.setOwner(owner);

        return image;
    }
    @Test
    void findAllTest() {
        Client owner = createClient();

        Adress adresse = new Adress();
        adresse.setName("Grenoble");

        Set<Adress> adresses = new HashSet<>();
        adresses.add(adresse);
        owner.setAdresses(adresses);

        Image image1 = createImage("path/to/image1.jpg","metadata for image1",350.0,true,owner);
        Image image2 = createImage("path/to/image2.jpg","metadata for image2",150.0,true,owner);

        entityManager.persist(owner);
        entityManager.persist(image1);
        entityManager.persist(image2);
        entityManager.flush();

        Commande commande = new Commande();
        commande.setClient(owner);
        commande.setStatus(true);
        commande.setCreatedate(new Date());
        commande.setTotalPrice(100.0);

        List<Image> allImages = imageRepository.all();

        assertThat(allImages).containsExactly(image1, image2);
    }

    @Test
    void deleteImageByOwnerAndPathTest() {
        // Given
        Client owner = createClient();
        entityManager.persist(owner);

        Image image = createImage("path/to/image1.jpg","metadata for image1",350.0,true,owner);

        imageRepository.deleteImageByOwnerAndPath(owner.getId(), image.getPath());

        List<Image> images = imageRepository.all();
        assertThat(images).isEmpty();
    }

    @Test
    void updateImageTest() {
        Client owner = createClient();

        Image image = createImage("path/to/image1.jpg","metadata for image1",350.0,false,owner);

        entityManager.persist(owner);
        entityManager.persist(image);
        entityManager.flush();
        
        String newMetadata = "metadata jdida";
        String newResolution = "600.0";
        boolean isShared = true;
        imageRepository.updateImage(owner.getId(), image.getPath(), newMetadata, newResolution, isShared);
        entityManager.flush();
        
        Image updatedImage = entityManager.find(Image.class, image.getId());
        assertThat(updatedImage.getMetadata()).isEqualTo(newMetadata);
        assertThat(updatedImage.getResolution()).isEqualTo(Double.parseDouble(newResolution));
        assertThat(updatedImage.isShared()).isEqualTo(isShared);
    }

    @Test
    void findImageByPathTest() {
        // Given
        Client owner = createClient();
        entityManager.persist(owner);
        entityManager.flush();

        String imagePath = "path/to/image.jpg";
        Image image = createImage(imagePath, "metadata", 300.0, true, owner);
        entityManager.persist(image);
        entityManager.flush();

        // When
        List<Image> foundImages = imageRepository.findImageByPath(owner.getId(), imagePath);

        // Then
        assertThat(foundImages).containsExactly(image);
    }

    @Test
    void FindImageByOwnerAndPathTest() {

        Client owner = createClient();
        entityManager.persist(owner);
        entityManager.flush();

        Image image1 = createImage("path/to/image1.jpg", "metadata1", 300.0, false, owner);
        Image image2 = createImage("path/to/image2.jpg", "metadata2", 150.0, true, owner);
        entityManager.persist(image1);
        entityManager.persist(image2);
        entityManager.flush();

        List<Image> foundImages = imageRepository.findImageByOwnerAndPath(owner.getId(), "path/to/image1.jpg");

        assertThat(foundImages).containsExactly(image1);
    }

    @Test
    void getSharedImagesTest() {
        Client owner1 = createClient();
        Client owner2 = createClient();

        Image image1 = createImage("path/to/image1.jpg", "Some metadata for image1", 300.0, true, owner1);
        Image image2 = createImage("path/to/image2.jpg", "Some metadata for image2", 150.0, true, owner1);
        Image image3 = createImage("path/to/image3.jpg", "Some metadata for image3", 200.0, false, owner1);
        Image image4 = createImage("path/to/image4.jpg", "Some metadata for image4", 100.0, true, owner2);

        imageRepository.save(image1);
        imageRepository.save(image2);
        imageRepository.save(image3);
        imageRepository.save(image4);

        List<Image> sharedImages = imageRepository.getSharedImages();
        
        assertThat(sharedImages).containsExactly(image1, image2, image4);
    }





}
