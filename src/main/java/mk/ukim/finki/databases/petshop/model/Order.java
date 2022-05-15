package mk.ukim.finki.databases.petshop.model;

import lombok.Data;
import mk.ukim.finki.databases.petshop.model.enumerations.OrderStatus;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="ORDERR")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOrder;

    private OrderStatus status;

    private int numberOfItems;

    private double totalCost;

    private String category;

    private LocalDateTime date;

    @OneToOne//(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Payment payment;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;

    @ManyToMany
    List<Article> articles;

    @OneToMany(mappedBy = "order")
    List<VendorMakesSupplierTakesOrder> orderList = new ArrayList<>();

    public Order() {
    }

    public Order(OrderStatus status, int numberOfItems, double totalCost, String category, LocalDateTime date) {
        this.status = status;
        this.numberOfItems = numberOfItems;
        this.totalCost = totalCost;
        this.category = category;
        this.date = date;
    }

    public Order(Customer customer) {
        this.status = OrderStatus.CREATED;
        this.numberOfItems = 0;
        this.totalCost = 0.0;
        this.date = LocalDateTime.now();
        this.category = "customer";
        this.articles = new ArrayList<>();
        this.customer = customer;
    }

    public Long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Long idOrder) {
        this.idOrder = idOrder;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public int getNumberOfItems() {
        return numberOfItems;
    }

    public void setNumberOfItems(int numberOfItems) {
        this.numberOfItems = numberOfItems;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public List<VendorMakesSupplierTakesOrder> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<VendorMakesSupplierTakesOrder> orderList) {
        this.orderList = orderList;
    }
}
