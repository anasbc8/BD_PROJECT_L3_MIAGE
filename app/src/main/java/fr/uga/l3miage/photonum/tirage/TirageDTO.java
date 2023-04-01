package fr.uga.l3miage.photonum.tirage;

import fr.uga.l3miage.photonum.data.domain.Photo;
import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Builder
public class TirageDTO {

    private Set<Photo> photos;

}
