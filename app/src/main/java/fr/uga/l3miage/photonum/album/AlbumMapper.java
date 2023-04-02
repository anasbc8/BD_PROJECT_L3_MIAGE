package fr.uga.l3miage.photonum.album;

import fr.uga.l3miage.photonum.data.domain.Album;
import fr.uga.l3miage.photonum.data.domain.Album;
import fr.uga.l3miage.photonum.album.AlbumDTO;
import org.mapstruct.Mapper;
import java.util.Collection;

@Mapper(componentModel = "spring")
public interface AlbumMapper {
    AlbumDTO entityToDTO(Album album);

    Collection<AlbumDTO> entityToDTO(Iterable<Album> albums);

    Album dtoToEntity(AlbumDTO album);

    Collection<Album> dtoToEntity(Iterable<AlbumDTO> albums);
}
