package mk.ukim.finki.databases.petshop.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class VendorNotFoundException extends RuntimeException{

    public VendorNotFoundException() {
        super(String.format("Vendor was not found"));
    }
}
