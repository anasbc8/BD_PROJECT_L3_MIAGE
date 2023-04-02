package fr.uga.l3miage.photonum.cadre;

import fr.uga.l3miage.photonum.data.domain.Cadre;

import fr.uga.l3miage.photonum.image.ImageDTO;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper(componentModel = "spring")
public interface CadreMapper {

    CadreDTO entityToDTO(Cadre cadre);

    Collection<CadreDTO> entityToDTO(Iterable<Cadre> cadres);

    Cadre dtoToEntity(CadreDTO cadre);

    Collection<Cadre> dtoToEntity(Iterable<CadreDTO> cadres);

}
