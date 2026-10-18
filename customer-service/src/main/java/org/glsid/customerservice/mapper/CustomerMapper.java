package org.glsid.customerservice.mapper;

import org.glsid.customerservice.dto.CustomerRequestDTO;
import org.glsid.customerservice.dto.CustomerResponseDTO;
import org.glsid.customerservice.entities.Customer;
import org.glsid.customerservice.enums.CustomerStatus;
import org.springframework.stereotype.Component;

/**
 * Dedicated mapper component responsible for bidirectional transformations
 * between {@link Customer} domain entities and their corresponding Data Transfer Objects (DTOs).
 */
@Component
public class CustomerMapper {

    /**
     * Maps a customer creation payload DTO to a persistent domain JPA entity.
     *
     * @param dto the incoming request payload DTO
     * @return the constructed {@link Customer} JPA entity
     */
    public Customer toEntity(CustomerRequestDTO dto) {
        if (dto == null) {
            return null;
        }
        return Customer.builder()
                .firstName(dto.firstName())
                .lastName(dto.lastName())
                .email(dto.email())
                .identityNumber(dto.identityNumber())
                .phoneNumber(dto.phoneNumber())
                .customerType(dto.customerType())
                .status(CustomerStatus.PENDING_VERIFICATION)
                .build();
    }

    /**
     * Maps a persistent domain JPA entity to an outgoing REST response payload DTO.
     *
     * @param customer the persistent domain entity
     * @return the constructed {@link CustomerResponseDTO} payload
     */
    public CustomerResponseDTO toResponseDTO(Customer customer) {
        if (customer == null) {
            return null;
        }
        return new CustomerResponseDTO(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getIdentityNumber(),
                customer.getPhoneNumber(),
                customer.getCustomerType(),
                customer.getStatus(),
                customer.getCreatedAt(),
                customer.getUpdatedAt()
        );
    }
}