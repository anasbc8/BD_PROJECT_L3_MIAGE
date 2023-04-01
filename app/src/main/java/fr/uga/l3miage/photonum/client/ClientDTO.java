package fr.uga.l3miage.photonum.client;

import fr.uga.l3miage.photonum.image.ImageDTO;
import fr.uga.l3miage.photonum.adresse.AdressDTO;
import fr.uga.l3miage.photonum.impression.ImpressionDTO;
import fr.uga.l3miage.photonum.commande.CommandeDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

import java.util.Collection;

public record ClientDTO(
        @NotBlank
        String id,
        @NotBlank
        String email,
        @NotBlank
        String firstName,
        @NotBlank
        String lastName,
        @NotBlank
        String password,

        @Null
        Collection<ImageDTO> ownedImages
        @NotNull
        Collection<AdressDTO> adresses
        @Null
        Collection<ImpressionDTO> impressions
        @Null
        Collection<CommandeDTO> commandes

) {
}
