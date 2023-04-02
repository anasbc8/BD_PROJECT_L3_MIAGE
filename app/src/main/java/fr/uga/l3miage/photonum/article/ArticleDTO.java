package fr.uga.l3miage.photonum.article;

import fr.uga.l3miage.photonum.data.domain.Commande;
import fr.uga.l3miage.photonum.data.domain.Impression;
import jakarta.validation.constraints.NotBlank;

public record ArticleDTO(
        @NotBlank(message = "id of the article is mandatory")
         String id,

        @NotBlank(message = "quantity of the article is mandatory")
         int quantity,

        @NotBlank(message = "price of the article is mandatory")
         double prixTotal,

        @NotBlank(message = "impression of the article is mandatory")
         Impression impression,

        @NotBlank(message = "commande of the article is mandatory")
         Commande commande)
{ }
