package mk.ukim.finki.databases.petshop.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPayment;

    private String status;

    private double amount;

    private int referenceNumber;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_order")
    private Order order;



    public Payment() {
    }

    public Payment(String status, double amount, int referenceNumber) {
        this.status = status;
        this.amount = amount;
        this.referenceNumber = referenceNumber;
    }
}
