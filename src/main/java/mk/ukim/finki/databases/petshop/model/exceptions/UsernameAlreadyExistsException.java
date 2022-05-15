package mk.ukim.finki.databases.petshop.model.exceptions;

public class UsernameAlreadyExistsException extends RuntimeException{
    public UsernameAlreadyExistsException(String email) {
        super(String.format("Vendor with email %s already exists", email));
    }
}
