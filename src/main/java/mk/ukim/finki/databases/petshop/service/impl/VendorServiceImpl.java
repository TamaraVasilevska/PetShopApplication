package mk.ukim.finki.databases.petshop.service.impl;

import mk.ukim.finki.databases.petshop.model.Role;
import mk.ukim.finki.databases.petshop.model.Vendor;
import mk.ukim.finki.databases.petshop.model.exceptions.InvalidUsernameOrPasswordException;
import mk.ukim.finki.databases.petshop.model.exceptions.PasswordsDoNotMatchException;
import mk.ukim.finki.databases.petshop.model.exceptions.UsernameAlreadyExistsException;
import mk.ukim.finki.databases.petshop.repository.jpa.VendorRepository;
import mk.ukim.finki.databases.petshop.service.VendorService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;
    private final PasswordEncoder passwordEncoder;

    public VendorServiceImpl(VendorRepository vendorRepository, PasswordEncoder passwordEncoder) {
        this.vendorRepository = vendorRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return vendorRepository.findByUsername(s).orElseThrow(()->new UsernameNotFoundException(s));
    }
    @Override
    public Vendor register(String username, String password, String repeatPassword, String name, String surname, Role userRole) {
        if (username==null || username.isEmpty()  || password==null || password.isEmpty())
            throw new InvalidUsernameOrPasswordException();
        if (!password.equals(repeatPassword))
            throw new PasswordsDoNotMatchException();
        if(this.vendorRepository.findByUsername(username).isPresent())
            throw new UsernameAlreadyExistsException(username);
        Vendor user = new Vendor(username,passwordEncoder.encode(password),name,surname,userRole);
        return vendorRepository.save(user);
    }
}
