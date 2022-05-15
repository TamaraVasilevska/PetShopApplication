package mk.ukim.finki.databases.petshop.service.impl;

import mk.ukim.finki.databases.petshop.model.Article;
import mk.ukim.finki.databases.petshop.model.Customer;
import mk.ukim.finki.databases.petshop.model.Order;
import mk.ukim.finki.databases.petshop.model.exceptions.*;
import mk.ukim.finki.databases.petshop.repository.jpa.ArticleRepository;
import mk.ukim.finki.databases.petshop.repository.jpa.CustomerRepository;
import mk.ukim.finki.databases.petshop.repository.jpa.OrderRepository;
import mk.ukim.finki.databases.petshop.repository.jpa.VendorRepository;
import mk.ukim.finki.databases.petshop.service.ArticleService;
import mk.ukim.finki.databases.petshop.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ArticleService articleService;
    private final CustomerRepository customerRepository;
    private final VendorRepository vendorRepository;
    private final ArticleRepository articleRepository;

    public OrderServiceImpl(OrderRepository orderRepository, ArticleService articleService, CustomerRepository customerRepository, VendorRepository vendorRepository, ArticleRepository articleRepository) {
        this.orderRepository = orderRepository;
        this.articleService = articleService;
        this.customerRepository = customerRepository;
        this.vendorRepository = vendorRepository;
        this.articleRepository = articleRepository;
    }

    @Override
    public List<Article> listAllArticlesInOrder(Long orderId) {
        if(!this.orderRepository.findById(orderId).isPresent())
            throw new OrderNotFoundException(orderId);
        return this.orderRepository.findById(orderId).get().getArticles();
    }

    @Override
    public Order addArticleToOrder(String username, Long articleId) {
        Order order = this.getActiveOrder(username);
        Article article = this.articleService.findById(articleId)
                .orElseThrow(() -> new ArticleNotFoundException(articleId));
        if(order.getArticles()
                .stream().filter(i -> i.getIdArticle().equals(articleId))
                .collect(Collectors.toList()).size() > 0)
            throw new ArticleAlreadyInOrderException(articleId);
        order.getArticles().add(article);
        return this.orderRepository.save(order);
    }

    @Override
    public Order getActiveOrder(String username) {
        Customer customer = this.customerRepository.findByUsername(username)
                .orElseThrow(CustomerNotFoundException::new);

        // Vendor vendor = this.vendorRepository.findByUsername(username)
        //   .orElseThrow(VendorNotFoundException::new);

        if(this.orderRepository.findByCustomer(customer).isPresent()){
            return this.orderRepository.findByCustomer(customer).get();
        }
        //else if(this.orderRepository.findByVendor(vendor).isPresent()){
        //     return this.orderRepository.findByVendor(vendor).get();
        // }

        else {
            Order order = new Order(customer);
            return this.orderRepository.save(order);
        }
    }

    @Override
    public void removeFromOrder(String username, Long articleId) {
        Order o = this.getActiveOrder(username);
        Article article = articleRepository.findById(articleId).orElseThrow();
        List<Article> articles = o.getArticles();
        articles.remove(article);
        o.setArticles(articles);
        this.orderRepository.save(o);
    }

    @Override
    public void removeAllFromOrder(String username) {
        Order o = this.getActiveOrder(username);
        o.setArticles(new ArrayList<>());
        this.orderRepository.save(o);
    }


    @Override
    public double getFullPriceOfOrder(Long id) {
        return orderRepository.getById(id).getArticles().stream().mapToDouble(Article::getPrice).sum();
    }

    @Override
    public long getProductsInOrder(Long id) {
        return orderRepository.getById(id).getArticles().size();
    }



//    @Override
//    public Order getActiveOrder(Long orderId) {
//        return this.orderRepository
//                .findByIdOrderAndStatus(orderId, OrderStatus.CREATED).orElseThrow();
//    }
}

