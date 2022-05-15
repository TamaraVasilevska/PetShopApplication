package mk.ukim.finki.databases.petshop.service;

import mk.ukim.finki.databases.petshop.model.Customer;
import mk.ukim.finki.databases.petshop.model.Vendor;

public interface AuthService {
    Customer loginCustomer(String username, String password);
    Vendor loginVendor(String username, String password);
}

