package org.glsid.customerservice.dto;

import org.glsid.customerservice.enums.CustomerStatus;
import org.glsid.customerservice.enums.CustomerType;

import java.time.LocalDateTime;

/**
 * Data Transfer Object for exposing customer state through REST endpoints.
 * Prevents direct leakage of internal JPA entity details over the network.
 *
 * @param id             unique database identifier
 * @param firstName      customer's first name
 * @param lastName       customer's last name
 * @param email          customer's email address
 * @param identityNumber national identity or passport number
 * @param phoneNumber    contact phone number
 * @param customerType   segmentation category
 * @param status         compliance and lifecycle status
 * @param createdAt      timestamp of profile creation
 * @param updatedAt      timestamp of last modification
 */
public record CustomerResponseDTO(
        Long id,
        String firstName,
        String lastName,
        String email,
        String identityNumber,
        String phoneNumber,
        CustomerType customerType,
        CustomerStatus status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}