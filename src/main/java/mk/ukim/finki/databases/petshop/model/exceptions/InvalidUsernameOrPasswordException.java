package mk.ukim.finki.databases.petshop.model.exceptions;

public class InvalidUsernameOrPasswordException extends RuntimeException {
    public InvalidUsernameOrPasswordException() {
        super(String.format("Invalid Username Or Password"));
    }
}
