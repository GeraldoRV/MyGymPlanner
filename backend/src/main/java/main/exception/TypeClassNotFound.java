package main.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Type class not found")
public class TypeClassNotFound extends RuntimeException {
    public TypeClassNotFound(String message){
        super(message);
    }
}
