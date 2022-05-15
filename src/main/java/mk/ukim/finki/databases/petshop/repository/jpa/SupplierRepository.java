package mk.ukim.finki.databases.petshop.repository.jpa;

import mk.ukim.finki.databases.petshop.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}
