package fr.uga.l3miage.photonum.data.repo;

import fr.uga.l3miage.photonum.TestApp;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = TestApp.class, webEnvironment = SpringBootTest.WebEnvironment.NONE,
        properties = {"spring.jpa.show-sql=true"})
@Transactional
abstract class Base {

    @PersistenceContext
    EntityManager entityManager;

}
