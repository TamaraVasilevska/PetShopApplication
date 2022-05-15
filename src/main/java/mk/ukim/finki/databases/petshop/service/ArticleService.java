package mk.ukim.finki.databases.petshop.service;

import mk.ukim.finki.databases.petshop.model.Article;

import java.util.List;
import java.util.Optional;

public interface ArticleService {

    List<Article> findAll();

    Optional<Article> findById(Long id);

    Optional<Article> findByName(String name);

    Optional<Article> save(String name, String description, double quantity,
                           double price, double serialNumber);

    void deleteById(Long id);

    List<Article> fullTextSearch(String search);
}
