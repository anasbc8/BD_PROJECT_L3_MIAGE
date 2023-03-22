package fr.uga.l3miage.photonum.data.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@DiscriminatorValue("1")
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String retouching;
    @OneToOne
    private Image image;

    @ManyToOne
    private Tirage tirage;

    @ManyToOne
    private Cadre cadrePhoto;
}
