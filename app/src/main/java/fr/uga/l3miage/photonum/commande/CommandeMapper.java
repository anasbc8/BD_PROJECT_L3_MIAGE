package fr.uga.l3miage.photonum.commande;

import fr.uga.l3miage.photonum.data.domain.Commande;
import org.mapstruct.Mapper;

import java.util.Collection;
@Mapper(componentModel = "spring")
public interface CommandeMapper {
    CommandeDTO entityToDTO(Commande commande);

    Collection<CommandeDTO> entityToDTO(Iterable<Commande> commandes);

    Commande dtoToEntity(CommandeDTO commande);

    Collection<Commande> dtoToEntity(Iterable<CommandeDTO> commandes);
}
