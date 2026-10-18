package org.glsid.customerservice.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.glsid.customerservice.dto.CustomerRequestDTO;
import org.glsid.customerservice.dto.CustomerResponseDTO;
import org.glsid.customerservice.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller exposing HTTP API endpoints for managing customer profiles.
 * Handles HTTP requests, enforces payload validation, and delegates business logic execution to {@link CustomerService}.
 */
@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerRestController {

    private final CustomerService customerService;

    /**
     * Creates a new customer profile.
     *
     * @param requestDTO the validated payload containing customer details
     * @return {@link ResponseEntity} containing the created {@link CustomerResponseDTO} with HTTP status 201 (CREATED)
     */
    @PostMapping
    public ResponseEntity<CustomerResponseDTO> createCustomer(@Valid @RequestBody CustomerRequestDTO requestDTO) {
        CustomerResponseDTO createdCustomer = customerService.createCustomer(requestDTO);
        return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
    }

    /**
     * Retrieves a single customer profile by its unique database identifier.
     *
     * @param id the unique customer identifier
     * @return {@link ResponseEntity} containing the matching {@link CustomerResponseDTO} with HTTP status 200 (OK)
     */
    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponseDTO> getCustomerById(@PathVariable Long id) {
        CustomerResponseDTO customer = customerService.getCustomerById(id);
        return ResponseEntity.ok(customer);
    }

    /**
     * Retrieves all registered customer profiles.
     *
     * @return {@link ResponseEntity} containing a list of {@link CustomerResponseDTO} with HTTP status 200 (OK)
     */
    @GetMapping
    public ResponseEntity<List<CustomerResponseDTO>> getAllCustomers() {
        List<CustomerResponseDTO> customers = customerService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }

    /**
     * Updates an existing customer profile with new details.
     *
     * @param id         the unique identifier of the customer to update
     * @param requestDTO the validated payload containing updated customer details
     * @return {@link ResponseEntity} containing the updated {@link CustomerResponseDTO} with HTTP status 200 (OK)
     */
    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponseDTO> updateCustomer(
            @PathVariable Long id,
            @Valid @RequestBody CustomerRequestDTO requestDTO) {

        CustomerResponseDTO updatedCustomer = customerService.updateCustomer(id, requestDTO);
        return ResponseEntity.ok(updatedCustomer);
    }
}