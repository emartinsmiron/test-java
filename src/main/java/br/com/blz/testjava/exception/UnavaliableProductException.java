package br.com.blz.testjava.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.PRECONDITION_FAILED)
public class UnavaliableProductException extends BelezaBussinessException {
    public UnavaliableProductException(final String message) {
        super(message);
    }
}

