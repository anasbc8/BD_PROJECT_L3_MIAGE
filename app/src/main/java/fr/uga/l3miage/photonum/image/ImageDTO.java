package fr.uga.l3miage.photonum.image;

import fr.uga.l3miage.photonum.data.domain.Client;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Builder
public class ImageDTO {

    private String id;

    @NotBlank(message = "path of the image is mandatory")
    private String path;

    private String metadata;

    private double resolution;

    private boolean isShared;

    private Client owner;

}
