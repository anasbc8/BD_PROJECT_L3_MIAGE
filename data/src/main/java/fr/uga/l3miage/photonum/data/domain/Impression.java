
package fr.uga.l3miage.photonum.data.domain;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "impression_type", discriminatorType = DiscriminatorType.INTEGER)
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Impression {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "price", nullable = false)
    private double price;
    @OneToOne
    private Article article;
    @Enumerated
    private Catalogue reference;


}
