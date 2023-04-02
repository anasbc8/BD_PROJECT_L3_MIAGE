package fr.uga.l3miage.photonum.image;

import fr.uga.l3miage.photonum.data.domain.Image;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper(componentModel = "spring")
public interface ImageMapper {
    ImageDTO entityToDTO(Image image);

    Collection<ImageDTO> entityToDTO(Iterable<Image> images);

    Image dtoToEntity(ImageDTO image);

    Collection<Image> dtoToEntity(Iterable<ImageDTO> images);
}
