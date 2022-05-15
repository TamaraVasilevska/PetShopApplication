package mk.ukim.finki.databases.petshop.repository.jpa;

import mk.ukim.finki.databases.petshop.model.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, String> {

    Optional<Vendor> findByUsernameAndPassword(String username, String password);
    Optional<Vendor> findByUsername(String username);
}
