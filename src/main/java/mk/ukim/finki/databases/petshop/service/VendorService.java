package mk.ukim.finki.databases.petshop.service;

import mk.ukim.finki.databases.petshop.model.Role;
import mk.ukim.finki.databases.petshop.model.Vendor;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface VendorService extends UserDetailsService {
    Vendor register(String username, String password, String repeatPassword, String name, String surname, Role role);
}
