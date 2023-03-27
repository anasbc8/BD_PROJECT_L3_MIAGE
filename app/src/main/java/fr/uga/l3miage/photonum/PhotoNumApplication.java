package fr.uga.l3miage.photonum;

import fr.uga.l3miage.photonum.data.domain.Article;
import fr.uga.l3miage.photonum.data.domain.Impression;
import fr.uga.l3miage.photonum.data.repo.ImpressionRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PhotoNumApplication {

    public static void main(String[] args) {
        System.out.println("anas zuin ");
        SpringApplication.run(PhotoNumApplication.class, args);
        System.out.println("anas zuin ");
    }

}