package fr.uga.l3miage.photonum.client;

import fr.uga.l3miage.photonum.data.domain.Commande;
import fr.uga.l3miage.photonum.data.domain.Image;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;

import java.util.Collection;


public record ClientDTO(
                @NotBlank String id,
                @NotBlank String email,
                @NotBlank String firstName,
                @NotBlank String lastName,
                @NotBlank String password,

                @Null Collection<Image> ownedImages,

                @Null Collection<Commande> commandes

) {
}
