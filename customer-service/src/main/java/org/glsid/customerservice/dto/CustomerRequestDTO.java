package org.glsid.customerservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.glsid.customerservice.enums.CustomerType;

/**
 * Data Transfer Object for creating or updating a customer profile.
 * Enforces JSR-380 Bean Validation constraints on incoming HTTP request payloads.
 *
 * @param firstName      the customer's first name
 * @param lastName       the customer's last name
 * @param email          the customer's unique email address
 * @param identityNumber national identity card (CIN) or passport number
 * @param phoneNumber    contact phone number
 * @param customerType   segmentation category (INDIVIDUAL or CORPORATE)
 */
public record CustomerRequestDTO(
        @NotBlank(message = "First name is required")
        String firstName,

        @NotBlank(message = "Last name is required")
        String lastName,

        @NotBlank(message = "Email address is required")
        @Email(message = "Email address must be valid")
        String email,

        @NotBlank(message = "Identity number is required")
        String identityNumber,

        String phoneNumber,

        @NotNull(message = "Customer type is required")
        CustomerType customerType
) {}