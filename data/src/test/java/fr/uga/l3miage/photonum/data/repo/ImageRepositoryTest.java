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
                .owner(owner)
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
    void deleteImagesByOwnerTest() {
        Client owner = createClient();
        Image image1 = buildImage("path/to/image1.jpg", "metadata for image1", 350.0, true, owner);
        Image image2 = buildImage("path/to/image2.jpg", "metadata for image2", 150.0, true, owner);
        entityManager.persist(owner);
        entityManager.persist(image1);
        entityManager.persist(image2);
        entityManager.flush();
        imageRepository.deleteImagesByOwner(owner.getId());
        entityManager.flush();
        List<Image> ownerImages = imageRepository.findImagesByOwner(owner.getId());
        assertThat(ownerImages).isEmpty();
    }

    @Test
    void deleteImageByPathTest() {
        Client owner = createClient();
        Image image1 = buildImage("path/to/image1.jpg", "metadata for image1", 350.0, true, owner);
        Image image2 = buildImage("path/to/image2.jpg", "metadata for image2", 150.0, true, owner);
        entityManager.persist(owner);
        entityManager.persist(image1);
        entityManager.persist(image2);
        entityManager.flush();
        imageRepository.deleteImageByPath(image1.getPath());
        entityManager.flush();
        List<Image> allImages = imageRepository.all();
        assertThat(allImages.stream().map(Image::getPath)).containsExactly(image2.getPath());
    }

    @Test
    void deleteImageByIdTest() {
        Client owner = createClient();
        Image image1 = buildImage("path/to/image1.jpg", "metadata for image1", 350.0, true, owner);
        Image image2 = buildImage("path/to/image2.jpg", "metadata for image2", 150.0, true, owner);
        entityManager.persist(owner);
        entityManager.persist(image1);
        entityManager.persist(image2);
        entityManager.flush();
        imageRepository.deleteImageById(image1.getId());
        entityManager.flush();
        List<Image> allImages = imageRepository.all();
        assertThat(allImages.stream().map(Image::getPath)).containsExactly(image2.getPath());
    }

    @Test
    void updateImageIsSharedTest() {
        Client owner = createClient();
        Image image1 = buildImage("path/to/image1.jpg", "metadata for image1", 350.0, false, owner);
        Image image2 = buildImage("path/to/image2.jpg", "metadata for image2", 150.0, true, owner);
        entityManager.persist(owner);
        entityManager.persist(image1);
        entityManager.persist(image2);
        entityManager.flush();
        imageRepository.updateImageIsShared(image1.getId(), true);
        try { 
            imageRepository.updateImageIsShared(image2.getId(), false);
        } catch (Exception e) {
            //print the error
            System.out.println(e.getMessage());
        }
        entityManager.flush();
        List<Image> allImages = imageRepository.all();
        assertThat(allImages.stream().map(Image::isShared)).containsExactly(true, true);
    }

    @Test
    void updateImagePathTest(){
        Client owner = createClient();
        Image image1 = buildImage("path/to/image1.jpg", "metadata for image1", 350.0, false, owner);
        Image image2 = buildImage("path/to/image2.jpg", "metadata for image2", 150.0, true, owner);
        entityManager.persist(owner);
        entityManager.persist(image1);
        entityManager.persist(image2);
        entityManager.flush();
        imageRepository.updateImagePath(image1.getId(), "new/path/to/image1.jpg");
        imageRepository.updateImagePath(image2.getId(), "new/path/to/image2.jpg");
        entityManager.flush();
        List<Image> allImages = imageRepository.all();
        assertThat(allImages.stream().map(Image::getPath)).containsExactly("new/path/to/image1.jpg", "new/path/to/image2.jpg");
    }

    @Test
    void findImageByIdTest() {
        Client owner = createClient();
        Image image1 = buildImage("path/to/image1.jpg", "metadata for image1", 350.0, true, owner);
        Image image2 = buildImage("path/to/image2.jpg", "metadata for image2", 150.0, true, owner);
        entityManager.persist(owner);
        entityManager.persist(image1);
        entityManager.persist(image2);
        entityManager.flush();
        Image foundImage = imageRepository.findImageById(image1.getId());
        assertThat(foundImage).isEqualTo(image1);
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
        Image foundImage = imageRepository.findImageByPath(imagePath);

        // Then
        assertThat(foundImage).isEqualTo(image);
    }

    @Test
    void findImagesByOwnerTest() {
        Client owner = createClient();
        Image image1 = buildImage("path/to/image1.jpg", "metadata for image1", 350.0, true, owner);
        Image image2 = buildImage("path/to/image2.jpg", "metadata for image2", 150.0, true, owner);
        entityManager.persist(owner);
        entityManager.persist(image1);
        entityManager.persist(image2);
        entityManager.flush();
        List<Image> images = imageRepository.findImagesByOwner(owner.getId());
        assertThat(images).containsExactly(image1, image2);
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
