package fr.uga.l3miage.photonum.service;

import fr.uga.l3miage.photonum.data.domain.Article;
import fr.uga.l3miage.photonum.data.domain.Calendrier;
import fr.uga.l3miage.photonum.service.base.BaseService;

import java.util.List;

public interface ArticleService extends BaseService<Article, String> {
     Article save(Article article);
     List<Article> list() ;
     void delete(String id) throws EntityNotFoundException;
     Article get(String id) throws EntityNotFoundException;
      Article update(Article article) throws EntityNotFoundException;
}
