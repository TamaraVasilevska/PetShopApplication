package mk.ukim.finki.databases.petshop.model.exceptions;

public class PasswordsDoNotMatchException extends RuntimeException{
    public PasswordsDoNotMatchException() {
        super(String.format("Passwords Do Not Match "));
    }
}