package fr.uga.l3miage.photonum.impression;

import fr.uga.l3miage.photonum.data.domain.Catalogue;
import fr.uga.l3miage.photonum.data.domain.Impression;
import jakarta.validation.constraints.NotNull;

public record ImpressionDTO(
/*         @NotNull String id,
        double price,
        @NotNull ArticleDTO article,
        @NotNull Catalogue reference
*/) {/*
    public static ImpressionDTO fromEntity(Impression impression) {
        return new ImpressionDTO(
                impression.getId(),
                impression.getPrice(),
                ArticleDTO.fromEntity(impression.getArticle()),
                impression.getReference()
        );
    }

    public Impression toEntity() {
        Impression impression = new Impression();
        impression.setId(id);
        impression.setPrice(price);
        impression.setArticle(article.toEntity());
        impression.setReference(reference);
        return impression;
    }*/ 
}