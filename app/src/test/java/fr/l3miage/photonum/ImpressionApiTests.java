package fr.l3miage.photonum;

import fr.uga.l3miage.photonum.PhotoNumApplication;
import fr.uga.l3miage.photonum.impression.ImpressionDTO;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = PhotoNumApplication.class,
        properties = {
        "spring.datasource.embedded-database-connection=h2",
                "spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.H2Dialect",
                "spring.jpa.show-sql=true"
        }
)
@AutoConfigureTestDatabase
@AutoConfigureTestEntityManager
class ImpressionApiTests {

    @SuppressWarnings("java:S2699")
    @Test
    void contextLoads() {

    }

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void homeResponse() {
        ResponseEntity<String> response = this.restTemplate.getForEntity("/", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void apiV1Response() {
        ResponseEntity<String> response = this.restTemplate.getForEntity("/api/v1", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    @Disabled
    void creationImpression() {
        var author = new ImpressionDTO();
        var response = this.restTemplate.postForEntity("/api/v1/impressions", author, ImpressionDTO.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(201));
        var list = this.restTemplate.getForObject("/api/v1/impressions", List.class);
        assertThat(list)
                .hasSize(1);
    }
}
