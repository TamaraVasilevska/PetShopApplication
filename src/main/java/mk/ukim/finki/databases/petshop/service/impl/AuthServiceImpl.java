package mk.ukim.finki.databases.petshop.service.impl;

import mk.ukim.finki.databases.petshop.model.Customer;
import mk.ukim.finki.databases.petshop.model.Role;
import mk.ukim.finki.databases.petshop.model.Vendor;
import mk.ukim.finki.databases.petshop.model.exceptions.InvalidArgumentsException;
import mk.ukim.finki.databases.petshop.model.exceptions.InvalidUserCredentialsException;
import mk.ukim.finki.databases.petshop.repository.jpa.CustomerRepository;
import mk.ukim.finki.databases.petshop.repository.jpa.VendorRepository;
import mk.ukim.finki.databases.petshop.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final CustomerRepository customerRepository;
    private final VendorRepository vendorRepository;

    public AuthServiceImpl(CustomerRepository customerRepository, VendorRepository vendorRepository) {
        this.customerRepository = customerRepository;
        this.vendorRepository = vendorRepository;
    }
    @Override
    public Customer loginCustomer(String username, String password) {
        if (username==null || username.isEmpty() || password==null || password.isEmpty()) {
            throw new InvalidArgumentsException();
        }
        return customerRepository.findByUsernameAndPassword(username,password).orElseThrow(InvalidUserCredentialsException::new);
    }
    @Override
    public Vendor loginVendor(String username, String password) {
        if (username==null || username.isEmpty() || password==null || password.isEmpty()) {
            throw new InvalidArgumentsException();
        }
        return vendorRepository.findByUsernameAndPassword(username,password).orElseThrow(InvalidUserCredentialsException::new);
    }
}
