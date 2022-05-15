package mk.ukim.finki.databases.petshop.config;

import mk.ukim.finki.databases.petshop.repository.jpa.CustomerRepository;
import mk.ukim.finki.databases.petshop.repository.jpa.VendorRepository;
import mk.ukim.finki.databases.petshop.service.CustomerService;
import mk.ukim.finki.databases.petshop.service.VendorService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UsernamePasswordAuthenticationProvider implements AuthenticationProvider {

    private final CustomerService customerService;
    private final VendorService vendorService;
    private final PasswordEncoder passwordEncoder;
    private final CustomerRepository customerRepository;
    private final VendorRepository vendorRepository;

    public UsernamePasswordAuthenticationProvider(CustomerService customerService, VendorService vendorService, PasswordEncoder passwordEncoder, CustomerRepository customerRepository, VendorRepository vendorRepository) {
        this.customerService = customerService;
        this.vendorService = vendorService;
        this.passwordEncoder = passwordEncoder;
        this.customerRepository = customerRepository;
        this.vendorRepository = vendorRepository;
    }


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        if ("".equals(username) || "".equals(password)) {
            throw new BadCredentialsException("Invalid Credentials");
        }

        UserDetails userDetails = null;

        if(this.customerRepository.existsById(username)) {
            userDetails = this.customerService.loadUserByUsername(username);
        }
        else if(this.vendorRepository.existsById(username)) {
            userDetails = this.vendorService.loadUserByUsername(username);
        }

        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Password is incorrect!");
        }
        return new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());

    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}


