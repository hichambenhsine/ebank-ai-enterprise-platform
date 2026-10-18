package org.glsid.customerservice.exceptions;

/**
 * Business exception thrown when attempting to register a customer with an email that is already registered.
 */
public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException(String message) {
        super(message);
    }
}