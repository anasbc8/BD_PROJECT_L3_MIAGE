package fr.uga.l3miage.photonum.data.repo;

import fr.uga.l3miage.photonum.data.domain.Calendrier;
import fr.uga.l3miage.photonum.data.domain.Image;
import fr.uga.l3miage.photonum.data.domain.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CalendrierRepositoryTest extends Base{
    @Autowired
    CalendrierRepository calendrierRepository ;

    @Test
    void findAllCalendriersTest(){

    Calendrier calendrier1 = new Calendrier() ;
    Calendrier calendrier2 = new Calendrier() ;
    entityManager.persist(calendrier1);
    entityManager.persist(calendrier2);
    entityManager.flush();
        List<Calendrier> cal = calendrierRepository.all() ;
        assertThat(cal.stream().map(Calendrier::getId)).containsExactly(calendrier1.getId(), calendrier2.getId());
    }
@Test
    void updateCalendrierTest(){

        Calendrier calendrier = new Calendrier();
        Set<Page> pages = new HashSet<>();
        Page page1 = new Page();
        page1.setText("Page 1");
        Page page2 = new Page();
        page2.setText("Page 2");
        pages.add(page1);
        pages.add(page2);
        calendrier.setPages(pages);
        entityManager.persist(calendrier);
        for(Page pages2 :pages){
            entityManager.persist(pages2);
        }
        entityManager.flush();

    Set<Page> nouvellesPages = new HashSet<>();
    Page page3 = new Page();
    page3.setText("Page 3");
    Page page4 = new Page();
    page4.setText("Page 4");
    nouvellesPages.add(page3);
    nouvellesPages.add(page4);

    for(Page pages3 :nouvellesPages){
        entityManager.persist(pages3);
    }
    entityManager.flush();

    calendrierRepository.updateCalendrier(calendrier.getId(),nouvellesPages);

    // Vérifier que les pages ont été correctement mises à jour dans la base de données
    List<Calendrier> calendriers = calendrierRepository.all();
    assertEquals(1, calendriers.size());
    Calendrier calendrierRecupere = calendriers.get(0);
    Set<Page> pagesRecuperees = calendrierRecupere.getPages();
    assertEquals(2, pagesRecuperees.size());
    assertTrue(pagesRecuperees.contains(page3));
    assertTrue(pagesRecuperees.contains(page4));


    }

    @Test
    void deleteCalendrierById(){
        Calendrier calendrier1= new Calendrier();
        entityManager.persist(calendrier1);

        calendrierRepository.deleteCalendrierById(calendrier1.getId());
        List<Calendrier> calendriers = calendrierRepository.all();
        assertThat(calendriers).isEmpty();

    }
    @Test
    void getCalendrierByIdTest(){
    Calendrier calendrier1 = new Calendrier();
    entityManager.persist(calendrier1);
    entityManager.flush();
    List<Calendrier> allcalendriers = calendrierRepository.getCalendrierById(calendrier1.getId());
        assertThat(allcalendriers.stream().map(Calendrier::getId)).containsExactly(calendrier1.getId());
    }
}
