package org.glsid.customerservice.service;

import org.glsid.customerservice.dto.CustomerRequestDTO;
import org.glsid.customerservice.dto.CustomerResponseDTO;

import java.util.List;

/**
 * Business service interface defining domain operations for customer lifecycle management.
 * Acts as the primary application boundary between HTTP controllers and persistence layer.
 */
public interface CustomerService {

    /**
     * Registers a new customer profile after enforcing business uniqueness rules.
     *
     * @param requestDTO the payload containing customer creation details
     * @return the saved customer profile formatted as {@link CustomerResponseDTO}
     */
    CustomerResponseDTO createCustomer(CustomerRequestDTO requestDTO);

    /**
     * Retrieves a single customer profile by its unique database identifier.
     *
     * @param id the unique customer identifier
     * @return the matching customer profile formatted as {@link CustomerResponseDTO}
     */
    CustomerResponseDTO getCustomerById(Long id);

    /**
     * Retrieves all registered customer profiles.
     *
     * @return a list of all customers formatted as {@link CustomerResponseDTO}
     */
    List<CustomerResponseDTO> getAllCustomers();

    /**
     * Updates an existing customer profile with new details.
     *
     * @param id         the identifier of the customer to update
     * @param requestDTO the payload containing updated details
     * @return the updated customer profile formatted as {@link CustomerResponseDTO}
     */
    CustomerResponseDTO updateCustomer(Long id, CustomerRequestDTO requestDTO);
}