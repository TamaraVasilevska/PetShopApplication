package mk.ukim.finki.databases.petshop.service;

import mk.ukim.finki.databases.petshop.model.Article;
import mk.ukim.finki.databases.petshop.model.Order;

import java.util.List;

public interface OrderService {

    List<Article> listAllArticlesInOrder(Long orderId);

    Order addArticleToOrder(String username, Long productId);

    Order getActiveOrder(String email);

    void removeFromOrder(String username, Long articleId);
    double getFullPriceOfOrder(Long id);
    void removeAllFromOrder(String username);
    long getProductsInOrder(Long id);

}
