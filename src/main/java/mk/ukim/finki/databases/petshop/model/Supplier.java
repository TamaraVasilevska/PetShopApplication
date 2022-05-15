package mk.ukim.finki.databases.petshop.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSupplier;

    private String name;

    private String emailAddress;

    @OneToMany//(mappedBy = "supplier")
    List<VendorMakesSupplierTakesOrder> orderList = new ArrayList<>();

    public Supplier() {
    }

    public Supplier(String name, String emailAddress) {
        this.name = name;
        this.emailAddress = emailAddress;
    }
}
