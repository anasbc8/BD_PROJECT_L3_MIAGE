package fr.uga.l3miage.photonum.data.repo;

import com.github.javafaker.Faker;
import fr.uga.l3miage.photonum.data.domain.Impression;

import java.util.Random;

public class Fixtures {

    private static final Faker FAKER = Faker.instance(new Random(42));

    public static Impression newImpression() {
        Impression impression = new Impression();
        // ...
        return impression;
    }

}
