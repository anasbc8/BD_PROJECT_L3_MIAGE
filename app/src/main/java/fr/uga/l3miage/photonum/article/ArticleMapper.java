package fr.uga.l3miage.photonum.article;

import fr.uga.l3miage.photonum.data.domain.Article;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper(componentModel = "spring")
public interface ArticleMapper {
    
    ArticleDTO entityToDTO(Article article);

    Collection<ArticleDTO> entityToDTO(Iterable<Article> articles);

    Article dtoToEntity(ArticleDTO article);

    Collection<Article> dtoToEntity(Iterable<ArticleDTO> articles);
}