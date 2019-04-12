package br.com.blz.testjava.restapi;

import br.com.blz.testjava.exception.BelezaBussinessException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    protected ResponseEntity<Object> handleBussinessExceptions(final BelezaBussinessException ex, final WebRequest webRequest) {
        ResponseStatus annotation = ex.getClass().getAnnotation(ResponseStatus.class);
        if(ObjectUtils.isEmpty(annotation)){
            return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, webRequest);
        }
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), annotation.code(), webRequest);

    }
}
