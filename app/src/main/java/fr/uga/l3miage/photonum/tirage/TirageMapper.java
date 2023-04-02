package fr.uga.l3miage.photonum.tirage;

import fr.uga.l3miage.photonum.data.domain.Tirage;
import org.mapstruct.Mapper;
import java.util.Collection;

@Mapper(componentModel = "spring")
public interface TirageMapper {
    TirageDTO entityToDTO(Tirage tirage);

    Collection<TirageDTO> entityToDTO(Iterable<Tirage> tirages);

    Tirage dtoToEntity(TirageDTO tirage);

    Collection<Tirage> dtoToEntity(Iterable<TirageDTO> tirages);
}

