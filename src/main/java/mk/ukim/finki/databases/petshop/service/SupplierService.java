package mk.ukim.finki.databases.petshop.service;

import mk.ukim.finki.databases.petshop.model.Supplier;

import java.util.List;
import java.util.Optional;

public interface SupplierService {

    List<Supplier> findAll();

    Optional<Supplier> findById(Long id);

    Optional<Supplier> save(String name, String emailAddress);

    void deleteById(Long id);
}
