package mk.ukim.finki.databases.petshop.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idArticle;

    private String description;

    private double quantity;

    private String name;

    private double serialNumber;

    private double price;

    @ManyToOne
    private Supplier supplier;

    @ManyToMany (mappedBy = "articles")
    private List<Order> orders;

    public Article() {
    }

    public Article(String description, double quantity, String name, double serialNumber, double price) {
        this.description = description;
        this.quantity = quantity;
        this.name = name;
        this.serialNumber = serialNumber;
        this.price = price;
    }

    public Long getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(Long idArticle) {
        this.idArticle = idArticle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(double serialNumber) {
        this.serialNumber = serialNumber;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
