package org.micro.apps.common.exception;


import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author tibinatomy
 */

@ResponseStatus
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
