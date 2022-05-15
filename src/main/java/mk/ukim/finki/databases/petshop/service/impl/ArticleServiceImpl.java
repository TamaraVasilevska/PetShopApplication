package mk.ukim.finki.databases.petshop.service.impl;

import mk.ukim.finki.databases.petshop.model.Article;
import mk.ukim.finki.databases.petshop.model.Supplier;
import mk.ukim.finki.databases.petshop.repository.jpa.ArticleRepository;
import mk.ukim.finki.databases.petshop.repository.jpa.SupplierRepository;
import mk.ukim.finki.databases.petshop.service.ArticleService;
import org.hibernate.type.BigDecimalType;
import org.hibernate.type.descriptor.sql.DecimalTypeDescriptor;
import org.hibernate.type.descriptor.sql.NumericTypeDescriptor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public List<Article> findAll() {
        return this.articleRepository.findAll();
    }

    @Override
    public Optional<Article> findById(Long id) {
        return this.articleRepository.findById(id);
    }

    @Override
    public Optional<Article> findByName(String name) {
        return this.articleRepository.findByName(name);
    }

    @Override
    @Transactional
    public Optional<Article> save(String name, String description, double quantity, double price, double serialNumber) {
        this.articleRepository.deleteByName(name);
        return Optional.of(this.articleRepository.save(new Article(description, quantity, name, serialNumber, price)));
    }

    @Override
    public void deleteById(Long id) {
        this.articleRepository.deleteById(id);
    }

    @Override
    public List<Article> fullTextSearch(String search) {
        return this.articleRepository.findByNameContainingOrDescriptionContaining(search, search);
    }
}
