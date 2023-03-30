package fr.uga.l3miage.photonum.data.repo;

import fr.uga.l3miage.photonum.data.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static fr.uga.l3miage.photonum.data.repo.Utils.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CadreRepositoryTest extends Base {
    @Autowired
    private CadreRepository cadreRepository;

    @Test
    void allCadreTest(){
        Cadre cadre1 = new Cadre();
        Impression cadre2 = new Cadre();
        Impression cadre3 = new Cadre();

        entityManager.persist(cadre1);
        entityManager.persist(cadre2);
        entityManager.persist(cadre3);
        List<Cadre> allCadre = cadreRepository.all();
        assertThat(allCadre.stream().map(Cadre::getId)).containsExactly(cadre1.getId(),cadre2.getId(), cadre3.getId());
    }

    @Test
    void updateCadreTest(){
        Cadre cadreTest = new Cadre();
        Set<Photo> photos = new HashSet<Photo>();
        Photo ph1 = new Photo();
        Photo ph2 = new Photo();
        ph1.setRetouching("khayat");
        ph2.setRetouching("benay");
        photos.add(ph1);
        photos.add(ph2);
        cadreTest.setPhotos(photos);
        entityManager.persist(cadreTest);
        for(Photo ph : photos){
            entityManager.persist(ph);
        }
        entityManager.flush();

        Set<Photo> photosMaj = new HashSet<Photo>();
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

        cadreRepository.update(cadreTest.getId(),photosMaj);
        assertEquals(cadreTest.getPhotos(), photosMaj);

    }





}
