package fr.uga.l3miage.photonum.client;

import java.util.Collection;

import fr.uga.l3miage.photonum.commande.CommandeDTO;
import fr.uga.l3miage.photonum.data.domain.Adress;
import fr.uga.l3miage.photonum.image.ImageDTO;
import fr.uga.l3miage.photonum.impression.ImpressionDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public record ClientDTO(
                @NotBlank String id,
                @NotBlank String email,
                @NotBlank String firstName,
                @NotBlank String lastName,
                @NotBlank String password,

                @Null Collection<ImageDTO> ownedImages,
                @NotNull Collection<Adress> adresses,
                //@Null Collection<ImpressionDTO> impressions,
                @Null Collection<CommandeDTO> commandes

) {
}
