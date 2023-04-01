package fr.uga.l3miage.photonum.service;

import fr.uga.l3miage.photonum.data.domain.Article;
import fr.uga.l3miage.photonum.data.domain.Calendrier;
import fr.uga.l3miage.photonum.data.repo.ArticleRepository;
import org.slf4j.Logger;
import jakarta.transaction.Transactional;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService{
    private Logger logger = LoggerFactory.getLogger(CalendrierServiceImpl.class);
private ArticleRepository articleRepository ;

    @Autowired
    public ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }


    @Override
    public Article save(Article article) {
        return articleRepository.save(article);
    }

    @Override
    public Article get(String id) throws EntityNotFoundException {
        return articleRepository.get(id);
    }

    @Override
    public List<Article> list() {
        return articleRepository.all();
    }

    @Override
    public void delete(String id) throws EntityNotFoundException {
        logger.info("deleting article with id {}", id);
        Article foundedArticle = get(id);
        if (Objects.isNull(foundedArticle)) {
            throw new EntityNotFoundException("calendrier  with id=%d not found".formatted(id));
        }
        articleRepository.delete(foundedArticle);

    }

    @Override
    public Article update(Article article) throws EntityNotFoundException {
        return articleRepository.save(article);
    }

}
