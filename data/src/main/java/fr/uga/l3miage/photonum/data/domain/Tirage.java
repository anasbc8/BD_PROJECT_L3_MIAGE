package fr.uga.l3miage.photonum.data.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;
import java.util.Set;

@Entity
@DiscriminatorValue("3")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Tirage extends Impression {
    @OneToMany(mappedBy = "tirage")
    private Set<Photo> photos;

}
