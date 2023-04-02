package fr.uga.l3miage.photonum.article;

import fr.uga.l3miage.photonum.service.ArticleService;
import fr.uga.l3miage.photonum.service.CommandeService;
import fr.uga.l3miage.photonum.service.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;


@RestController
@RequestMapping(value = "/api/v1/articles", produces = "application/json")
public class ArticleController {

    private final ArticleService articleService;
    private final CommandeService commandeService;
    private final ArticleMapper articleMapper;

    public ArticleController(ArticleService articleService, CommandeService commandeService, ArticleMapper articleMapper) {
        this.articleService = articleService;
        this.commandeService = commandeService;
        this.articleMapper = articleMapper;
    }

    @GetMapping("/all")
    public Collection<ArticleDTO> getAllArticles() {
        return articleMapper.entityToDTO(articleService.list());
    }

    @GetMapping("/{id}")
    public ArticleDTO getArticleById(@PathVariable String id) throws EntityNotFoundException {
        try {
            return articleMapper.entityToDTO(articleService.get(id));
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, null, e);
        }
    }

    @PostMapping("/addArticle/{id}")
    public ArticleDTO addArticle(@PathVariable String id, @RequestBody ArticleDTO article) throws EntityNotFoundException {
        try {
            final var entity = articleService.save(articleMapper.dtoToEntity(article));
            return articleMapper.entityToDTO(entity);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, null, e);
        }
    }

}
