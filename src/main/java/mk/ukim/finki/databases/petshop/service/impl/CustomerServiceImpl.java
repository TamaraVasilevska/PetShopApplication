package mk.ukim.finki.databases.petshop.service.impl;

import mk.ukim.finki.databases.petshop.model.Customer;
import mk.ukim.finki.databases.petshop.model.Role;
import mk.ukim.finki.databases.petshop.model.exceptions.InvalidUsernameOrPasswordException;
import mk.ukim.finki.databases.petshop.model.exceptions.PasswordsDoNotMatchException;
import mk.ukim.finki.databases.petshop.model.exceptions.UsernameAlreadyExistsException;
import mk.ukim.finki.databases.petshop.repository.jpa.CustomerRepository;
import mk.ukim.finki.databases.petshop.service.CustomerService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    public CustomerServiceImpl(CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return customerRepository.findByUsername(s).orElseThrow(()->new UsernameNotFoundException(s));
    }
    @Override
    public Customer register(String username, String password, String repeatPassword, String name, String surname, Role userRole) {
        if (username==null || username.isEmpty()  || password==null || password.isEmpty())
            throw new InvalidUsernameOrPasswordException();
        if (!password.equals(repeatPassword))
            throw new PasswordsDoNotMatchException();
        if(this.customerRepository.findByUsername(username).isPresent())
            throw new UsernameAlreadyExistsException(username);
        Customer user = new Customer(username,passwordEncoder.encode(password),name,surname,userRole);
        return customerRepository.save(user);
    }
}
