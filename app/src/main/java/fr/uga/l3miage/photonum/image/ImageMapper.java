package fr.uga.l3miage.photonum.image;

import fr.uga.l3miage.photonum.data.domain.Image;
import fr.uga.l3miage.photonum.data.domain.Impression;
import fr.uga.l3miage.photonum.impression.ImpressionDTO;

import java.util.Collection;

public interface ImageMapper {
    ImageDTO entityToDTO(Image image);

    Collection<ImageDTO> entityToDTO(Iterable<Image> images);

    Image dtoToEntity(ImageDTO image);

    Collection<Image> dtoToEntity(Iterable<ImageDTO> images);
}
