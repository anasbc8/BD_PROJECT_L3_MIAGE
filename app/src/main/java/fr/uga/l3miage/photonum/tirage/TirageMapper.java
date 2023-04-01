package fr.uga.l3miage.photonum.tirage;

import fr.uga.l3miage.photonum.data.domain.Tirage;
import fr.uga.l3miage.photonum.data.domain.Impression;
import fr.uga.l3miage.photonum.impression.ImpressionDTO;

import java.util.Collection;

public interface TirageMapper {
    TirageDTO entityToDTO(Tirage tirage);

    Collection<TirageDTO> entityToDTO(Iterable<Tirage> tirages);

    Tirage dtoToEntity(TirageDTO tirage);

    Collection<Tirage> dtoToEntity(Iterable<TirageDTO> tirages);
}
