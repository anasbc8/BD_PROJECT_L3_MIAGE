package fr.uga.l3miage.photonum.image;

import fr.uga.l3miage.photonum.data.domain.Client;
import jakarta.validation.constraints.NotBlank;
import lombok.*;


public record ImageDTO (
        @NotBlank(message = "id of the image is mandatory")
         String id,
        @NotBlank(message = "path of the image is mandatory")
         String path,
         String metadata,
         double resolution,
         boolean isShared,
         Client owner
){
}
