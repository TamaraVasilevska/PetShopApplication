package mk.ukim.finki.databases.petshop.repository.jpa;

import mk.ukim.finki.databases.petshop.model.Customer;
import mk.ukim.finki.databases.petshop.model.Order;
import mk.ukim.finki.databases.petshop.model.Vendor;
import mk.ukim.finki.databases.petshop.model.enumerations.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByIdOrderAndStatus(Long idOrder, OrderStatus status);

    Optional<Order> findByCustomer(Customer customer);

    // Optional<Order> findByVendor(Vendor vendor);
}

