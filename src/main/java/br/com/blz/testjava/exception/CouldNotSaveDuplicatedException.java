package br.com.blz.testjava.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class CouldNotSaveDuplicatedException extends BelezaBussinessException {
    public CouldNotSaveDuplicatedException(final String message) {
        super(message);
    }
}


