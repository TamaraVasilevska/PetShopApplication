package mk.ukim.finki.databases.petshop.service;

import mk.ukim.finki.databases.petshop.model.Customer;
import mk.ukim.finki.databases.petshop.model.Role;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface CustomerService extends UserDetailsService {
    Customer register(String username, String password, String repeatPassword, String name, String surname, Role role);
}

