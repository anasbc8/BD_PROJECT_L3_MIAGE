package fr.uga.l3miage.photonum.calendrier;

import java.util.Set;

import fr.uga.l3miage.photonum.data.domain.Article;
import fr.uga.l3miage.photonum.data.domain.Catalogue;
import fr.uga.l3miage.photonum.data.domain.Page;
import jakarta.validation.constraints.NotBlank;

public record CalendrierDTO(
    @NotBlank(message = "impression's id is mandatory")
    String id,
    int prix,
    Catalogue reference,
    Article article,
    Set<Page> pages) {}
