package com.thoughtworks.felix.products.rest.exceptions;

import com.thoughtworks.felix.products.rest.dto.ErrorDTO;
import org.springframework.http.HttpStatus;

import java.util.List;

public class BadRequestException extends ShopifyInternalException {

    public BadRequestException(List<? extends ErrorDTO> errors) {
        super(errors);
    }

    @Override
    public HttpStatus httpStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
