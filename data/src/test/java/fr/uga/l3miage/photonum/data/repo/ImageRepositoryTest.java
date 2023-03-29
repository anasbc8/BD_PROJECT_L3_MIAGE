package fr.uga.l3miage.photonum.data.repo;

import fr.uga.l3miage.photonum.data.domain.Client;
import fr.uga.l3miage.photonum.data.domain.Image;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static fr.uga.l3miage.photonum.data.repo.Utils.*;
import static org.assertj.core.api.Assertions.assertThat;

class ImageRepositoryTest extends Base {

    @Autowired
    private ImageRepository imageRepository;

    Client createClient() {
        Client owner = new Client();
        owner.setLastName("Doe");
        owner.setFirstName("John");
        owner.setEmail("johndoe@example.com");
        owner.setPassword("secret");
        return owner;
    }

    Image buildImage(String path, String metadata, double resolution, boolean shared, Client owner) {
        return Image.builder()
                .path(path)
                .metadata(metadata)
                .resolution(resolution)
                .isShared(shared)
                .owner(owner != null ? owner : null)
                .build();
    }

    @Test
    void findAllTest() {

        Image image1 = buildImage("path/to/image1.jpg", "metadata for image1", 350.0, true, null);
        Image image2 = buildImage("path/to/image2.jpg", "metadata for image2", 150.0, true, null);

        entityManager.persist(image1);
        entityManager.persist(image2);
        entityManager.flush();
        List<Image> allImages = imageRepository.all();
        assertThat(allImages.stream().map(Image::getPath)).containsExactly(image1.getPath(), image2.getPath());
    }

    @Test
    void deleteImageByOwnerAndPathTest() {
        // Given
        Client owner = createClient();
        entityManager.persist(owner);

        Image image = buildImage("path/to/image1.jpg", "metadata for image1", 350.0, true, owner);
        entityManager.persist(image);

        imageRepository.deleteImageByOwnerAndPath(owner.getId(), image.getPath());

        List<Image> images = imageRepository.all();
        assertThat(images).isEmpty();
    }

    @Test
    void updateImageTest() {
        Client owner = createClient();

        Image image = buildImage("path/to/image1.jpg", "metadata for image1", 350.0, false, owner);

        entityManager.persist(owner);
        entityManager.persist(image);
        entityManager.flush();

        String newMetadata = "metadata jdida";
        double newResolution = 600.0;
        boolean isShared = true;
        imageRepository.updateImage(owner.getId(), image.getId(), newMetadata, newResolution, isShared);
        entityManager.flush();
        Image updatedImage = entityManager.find(Image.class, image.getId());
        assertThat(updatedImage.getMetadata()).isEqualTo(newMetadata);
        assertThat(updatedImage.getResolution()).isEqualTo(newResolution);
        assertThat(updatedImage.isShared()).isEqualTo(isShared);
    }

    @Test
    void findImageByPathTest() {
        // Given
        entityManager.flush();

        String imagePath = "path/to/image.jpg";
        Image image = buildImage(imagePath, "metadata", 300.0, true, null);
        entityManager.persist(image);
        entityManager.flush();

        // When
        List<Image> foundImages = imageRepository.findImageByPath(imagePath);

        // Then
        assertThat(foundImages).containsExactly(image);
    }

    @Test
    void findImageByOwnerAndPathTest() {

        Client owner = createClient();
        entityManager.persist(owner);
        entityManager.flush();

        Image image1 = buildImage("path/to/image1.jpg", "metadata1", 300.0, false, owner);
        Image image2 = buildImage("path/to/image2.jpg", "metadata2", 150.0, true, owner);
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
        entityManager.persist(owner1);
        entityManager.persist(owner2);

        Image image1 = buildImage(IMAGE_1_PATH, "Some metadata for image1", 300.0, true, owner1);
        Image image2 = buildImage(IMAGE_2_PATH, "Some metadata for image2", 150.0, true, owner1);
        Image image3 = buildImage(IMAGE_3_PATH, "Some metadata for image3", 200.0, false, owner1);
        Image image4 = buildImage(IMAGE_4_PATH, "Some metadata for image4", 100.0, true, owner2);

        imageRepository.save(image1);
        imageRepository.save(image2);
        imageRepository.save(image3);
        imageRepository.save(image4);

        List<Image> sharedImages = imageRepository.getSharedImages();

        assertThat(sharedImages).containsExactly(image1, image2, image4);
    }


}
