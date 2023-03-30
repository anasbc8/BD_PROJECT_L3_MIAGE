package fr.uga.l3miage.photonum.data.repo;

import fr.uga.l3miage.photonum.data.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class AlbumRepositoryTest extends  Base{


    @Autowired
    AlbumRepository albumRepository ;

    @Test
    void getAllAlbumsTest(){
        Album album1 = new Album() ;
        Album album2 = new Album() ;
        entityManager.persist(album1);
        entityManager.persist(album2);
        entityManager.flush();
        List<Album> albums = albumRepository.all() ;
        assertThat(albums.stream().map(Album::getId)).containsExactly(album1.getId(), album2.getId());

    }
    @Test
    void deleteAlbumTest(){
        Album album1 = new Album() ;
        Photo photoCover = new Photo();
        photoCover.setRetouching("khayat");
        Set<Page> pages = new HashSet<>();
        Page page1= new Page() ;
        Page page2= new Page() ;
        pages.add(page1);
        pages.add(page2);
        album1.setPages(pages);
        album1.setTitle("cheb Mami");
        album1.setPhotoCover(photoCover);
        entityManager.persist(album1);
        entityManager.persist(photoCover);
        for(Page pages2:pages){
            entityManager.persist(pages2);
        }
        entityManager.flush();

        albumRepository.delete(album1);
        entityManager.flush();

        Album album2 = albumRepository.get(album1.getId());
        assertNull(album2);
    }
@Test
    void updateAlbumTest(){
        Album album1 = new Album() ;
        Photo photoCover = new Photo();
        photoCover.setRetouching("khayat");
        Set<Page> pages = new HashSet<>();
        Page page1= new Page() ;
        Page page2= new Page() ;
        pages.add(page1);
        pages.add(page2);
        album1.setPages(pages);
        album1.setTitle("cheb Mami");
        album1.setPhotoCover(photoCover);
        entityManager.persist(album1);
        entityManager.persist(photoCover);
        for(Page pages2:pages){
            entityManager.persist(pages2);
        }
        entityManager.flush();

            //Update album1
        Photo photoCoverUpdated = new Photo();
        photoCoverUpdated.setRetouching("najar");
        Set<Page> pagesUpdated = new HashSet<>();
        Page page3= new Page() ;
        Page page4= new Page() ;
        pagesUpdated.add(page3);
        pagesUpdated.add(page4);
        entityManager.persist(photoCoverUpdated);
        for(Page pages3 : pagesUpdated){
            entityManager.persist(pages3);
        }

        albumRepository.update(album1.getId(),"khalil hmar",photoCoverUpdated,pagesUpdated);
        entityManager.flush();

        Album updatedAlbum = albumRepository.get(album1.getId());
        assertEquals(updatedAlbum.getPhotoCover(), photoCoverUpdated);
    }
    @Test
    void saveAlbumTest(){
        Album album1 = new Album() ;
        Photo photoCover = new Photo();
        photoCover.setRetouching("khayat");
        Set<Page> pages = new HashSet<>();
        Page page1= new Page() ;
        Page page2= new Page() ;
        pages.add(page1);
        pages.add(page2);
        album1.setPages(pages);
        album1.setTitle("cheb Mami");
        album1.setPhotoCover(photoCover);
        entityManager.persist(album1);
        entityManager.persist(photoCover);
        for(Page pages2:pages){
            entityManager.persist(pages2);
        }
        entityManager.flush();
        Album savedAlbum = albumRepository.save(album1);
        assertThat(savedAlbum.getId()).isNotNull();

    }
}
