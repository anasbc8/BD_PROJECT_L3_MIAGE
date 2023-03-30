package fr.uga.l3miage.photonum.data.repo;

import fr.uga.l3miage.photonum.data.domain.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class TirageRepositoryTest extends Base {

    @Autowired
    private TirageRepository tirageRepository;

    @Test
    void allTirageTest(){
        Tirage tirage1 = new Tirage();
        Tirage tirage2 = new Tirage();
        Tirage tirage3 = new Tirage();

        entityManager.persist(tirage1);
        entityManager.persist(tirage2);
        entityManager.persist(tirage3);
        List<Tirage> allTirage = tirageRepository.all();
        assertThat(allTirage.stream().map(Tirage::getId)).containsExactly(tirage1.getId(),tirage2.getId(), tirage3.getId());
    }

    @Test
    void updateTirageTest(){
        Tirage tirageTest = new Tirage();
        Set<Photo> photos = new HashSet<>();
        Photo ph1 = new Photo();
        Photo ph2 = new Photo();
        ph1.setRetouching("khayat");
        ph2.setRetouching("benay");
        photos.add(ph1);
        photos.add(ph2);
        tirageTest.setPhotos(photos);
        entityManager.persist(tirageTest);
        for(Photo ph : photos){
            entityManager.persist(ph);
        }
        entityManager.flush();

        Set<Photo> photosMaj = new HashSet<>();
        Photo ph3 = new Photo();
        Photo ph4 = new Photo();
        ph3.setRetouching("khadar");
        ph4.setRetouching("guezar");
        photosMaj.add(ph3);
        photosMaj.add(ph4);
        for(Photo ph : photos){
            entityManager.persist(ph);
        }
        entityManager.flush();

        tirageTest.setPhotos(photosMaj);
        entityManager.persist(tirageTest);
        entityManager.flush();

        Tirage tirageMaj = tirageRepository.get(tirageTest.getId());
        assertEquals(tirageMaj.getPhotos(), photosMaj);
    }

    @Test
    void deleteTirageTest(){

        Tirage tirageTest = new Tirage();
        Set<Photo> photos = new HashSet<>();
        Photo ph1 = new Photo();
        Photo ph2 = new Photo();
        ph1.setRetouching("khayat");
        ph2.setRetouching("benay");
        photos.add(ph1);
        photos.add(ph2);
        tirageTest.setPhotos(photos);
        entityManager.persist(tirageTest);
        for(Photo ph : photos){
            entityManager.persist(ph);
        }
        entityManager.flush();

        tirageRepository.delete(tirageTest);
        entityManager.flush();

        Tirage tirageMaj = tirageRepository.get(tirageTest.getId());
        assertNull(tirageMaj);

    }

    @Test
    void saveTirageTest(){
        Tirage tirageTest = new Tirage();
        Set<Photo> photos = new HashSet<>();
        Photo ph1 = new Photo();
        Photo ph2 = new Photo();
        ph1.setRetouching("khayat");
        ph2.setRetouching("benay");
        photos.add(ph1);
        photos.add(ph2);
        tirageTest.setPhotos(photos);
        entityManager.persist(tirageTest);
        for(Photo ph : photos){
            entityManager.persist(ph);
        }
        entityManager.flush();

        Tirage tirageMaj = tirageRepository.get(tirageTest.getId());
        assertEquals(tirageMaj.getPhotos(), photos);
    }

    @Test
    void getTirageTest(){
        Tirage tirageTest = new Tirage();
        Set<Photo> photos = new HashSet<>();
        Photo ph1 = new Photo();
        Photo ph2 = new Photo();
        ph1.setRetouching("khayat");
        ph2.setRetouching("benay");
        photos.add(ph1);
        photos.add(ph2);
        tirageTest.setPhotos(photos);
        entityManager.persist(tirageTest);
        for(Photo ph : photos){
            entityManager.persist(ph);
        }
        entityManager.flush();

        Tirage tirageMaj = tirageRepository.get(tirageTest.getId());
        assertEquals(tirageMaj.getPhotos(), photos);
    }

}
