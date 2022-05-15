package mk.ukim.finki.databases.petshop.repository.jpa;

import mk.ukim.finki.databases.petshop.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {

    Optional<Customer> findByUsernameAndPassword(String username, String password);
    Optional<Customer> findByUsername(String username);
}
