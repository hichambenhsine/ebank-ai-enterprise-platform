package org.glsid.customerservice.exceptions;


/**
 * Business exception thrown when a requested customer entity cannot be found in the database.
 */
public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(String message) {
        super(message);
    }
}