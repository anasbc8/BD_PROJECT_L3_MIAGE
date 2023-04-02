package fr.uga.l3miage.photonum.album;

import fr.uga.l3miage.photonum.data.domain.Article;
import fr.uga.l3miage.photonum.data.domain.Catalogue;
import fr.uga.l3miage.photonum.data.domain.Page;
import fr.uga.l3miage.photonum.data.domain.Photo;
import jakarta.validation.constraints.NotBlank;

import java.util.Set;

public record AlbumDTO(
        @NotBlank(message = "impression's id is mandatory")
        String id,
        int prix,
        Catalogue reference,
        Article article,
        @NotBlank(message = "album's title is mandatory")
        String title,

        Photo photoCover,

        Set<Page> pages
) {
}
