package fr.uga.l3miage.photonum.service;
import fr.uga.l3miage.photonum.data.domain.Article;
import fr.uga.l3miage.photonum.data.domain.Calendrier;
import fr.uga.l3miage.photonum.data.domain.Commande;
import fr.uga.l3miage.photonum.service.base.BaseService;

import java.util.List;

public interface CommandeService extends BaseService<Commande, String> {
    Commande save(Commande commande);
    void delete(String id) throws EntityNotFoundException ;
    Commande update(Commande commande) throws EntityNotFoundException;
    public List<Commande> list() ;
    void addArticle(String id, Article article) throws EntityNotFoundException;
}
