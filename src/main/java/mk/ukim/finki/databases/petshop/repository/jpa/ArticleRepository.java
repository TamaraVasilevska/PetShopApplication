package mk.ukim.finki.databases.petshop.repository.jpa;

import mk.ukim.finki.databases.petshop.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    List<Article> findAllByNameLike(String text);

    void deleteByName(String text);

    void deleteByIdArticle(Long id);

    Optional<Article> findByName(String name);

    List<Article> findByNameContainingOrDescriptionContaining(String text1, String text2);
}
