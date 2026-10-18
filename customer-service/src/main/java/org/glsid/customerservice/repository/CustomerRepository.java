package org.glsid.customerservice.repository;

import org.glsid.customerservice.entities.Customer;
import org.glsid.customerservice.enums.CustomerStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data JPA repository interface for managing {@link Customer} domain entities.
 * Encapsulates database access methods and custom queries for uniqueness and KYC compliance checks.
 *
 * @author
 * @version 1.0.0
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    /**
     * Retrieves a customer entity by its unique email address.
     *
     * @param email the target email address
     * @return an {@link Optional} containing the customer if present, or empty otherwise
     */
    Optional<Customer> findByEmail(String email);

    /**
     * Retrieves a customer entity by its unique identity document number (e.g., National ID or Passport).
     *
     * @param identityNumber the target identity document number
     * @return an {@link Optional} containing the customer if present, or empty otherwise
     */
    Optional<Customer> findByIdentityNumber(String identityNumber);

    /**
     * Filters customers based on their lifecycle and compliance status (e.g., PENDING_VERIFICATION, ACTIVE).
     *
     * @param status the target customer lifecycle status
     * @return a list of customers matching the specified status
     */
    List<Customer> findByStatus(CustomerStatus status);

    /**
     * Performs a lightweight existence check for a given email address.
     * Generates an optimized SQL EXISTS query rather than fetching full entity state.
     *
     * @param email the email address to check
     * @return true if a record exists with the specified email, false otherwise
     */
    boolean existsByEmail(String email);

    /**
     * Performs a lightweight existence check for a given identity document number.
     *
     * @param identityNumber the identity document number to check
     * @return true if a record exists with the specified identity number, false otherwise
     */
    boolean existsByIdentityNumber(String identityNumber);
}