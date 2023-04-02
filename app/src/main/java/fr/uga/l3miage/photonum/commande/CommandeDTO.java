package fr.uga.l3miage.photonum.commande;

import fr.uga.l3miage.photonum.data.domain.Article;
import fr.uga.l3miage.photonum.data.domain.Client;
import fr.uga.l3miage.photonum.data.domain.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;
import java.util.Set;

public record CommandeDTO(
        @NotBlank(message = "command's id  is mandatory")
        String id,

        Date createdate,

        Double totalPrice,

        @Enumerated(EnumType.STRING)
        Status status,

        Set<Article> articles,

        Client client
) {
}
