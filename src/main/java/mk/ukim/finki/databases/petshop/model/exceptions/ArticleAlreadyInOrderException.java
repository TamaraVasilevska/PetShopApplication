package mk.ukim.finki.databases.petshop.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
public class ArticleAlreadyInOrderException extends RuntimeException{

    public ArticleAlreadyInOrderException(Long id) {
        super(String.format("Article with id %d already exists in order", id));
    }
}
