package org.glsid.customerservice.dto;

import java.time.LocalDateTime;

/**
 * Standardized error response payload following RFC-7807 structure principles.
 *
 * @param status    HTTP status code value
 * @param error     HTTP status error description
 * @param message   business-specific exception detail message
 * @param path      the request URI path where the error occurred
 * @param timestamp exact moment the error response was generated
 */
public record ErrorResponseDTO(
        int status,
        String error,
        String message,
        String path,
        LocalDateTime timestamp
) {}