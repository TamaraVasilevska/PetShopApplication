package mk.ukim.finki.databases.petshop.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ArticleNotFoundException extends RuntimeException{

    public ArticleNotFoundException(Long id) {
        super(String.format("Article with id %d was not found", id));
    }
}
