package mk.ukim.finki.databases.petshop.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Data
@Entity
public class Customer implements UserDetails {

    @Id
    private String username;
    private String password;
    private String name;

    private String surname;
    private String address;
    private String discount;

    private boolean isAccountNonExpired = true;
    private boolean isAccountNonLocked = true;
    private boolean isCredentialsNonExpired = true;
    private boolean isEnabled = true;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    public Customer() {
    }

    public Customer(String username, String password, String name, String surname, Role role) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.role = role;
    }

    public Customer(String name, String surname, String username, String address, String discount) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.address = address;
        this.discount = discount;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(role);
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}






//    @OneToMany//(mappedBy = "order")
//    private List<Order> orders = new ArrayList<>();
/*
    public Customer() {
    }

    public Customer(String name, String lastName, String emailAddress, String address, String discount) {
        this.name = name;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.address = address;
        this.discount = discount;
    }

    public Customer(String name, String lastName, String emailAddress) {
        this.name = name;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
    }
}*/
