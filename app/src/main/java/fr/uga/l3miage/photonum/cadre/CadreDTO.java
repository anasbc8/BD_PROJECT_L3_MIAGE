package fr.uga.l3miage.photonum.cadre;

import fr.uga.l3miage.photonum.data.domain.Article;
import fr.uga.l3miage.photonum.data.domain.Catalogue;
import fr.uga.l3miage.photonum.data.domain.Photo;
import jakarta.validation.constraints.NotBlank;

import java.util.Set;

public record CadreDTO(
        @NotBlank(message = "impression's id is mandatory")
        String id,
        int prix,
        Catalogue reference,
        Article article,
        Set<Photo> photos

) {
}
