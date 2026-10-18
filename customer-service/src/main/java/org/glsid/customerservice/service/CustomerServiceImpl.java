package org.glsid.customerservice.service;

import lombok.RequiredArgsConstructor;
import org.glsid.customerservice.dto.CustomerRequestDTO;
import org.glsid.customerservice.dto.CustomerResponseDTO;
import org.glsid.customerservice.entities.Customer;
import org.glsid.customerservice.exceptions.CustomerNotFoundException;
import org.glsid.customerservice.exceptions.EmailAlreadyExistsException;
import org.glsid.customerservice.mapper.CustomerMapper;
import org.glsid.customerservice.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Default transactional implementation of the {@link CustomerService} interface.
 * Handles business validation, persistence operations, and delegates DTO mappings to {@link CustomerMapper}.
 */
@Service
@Transactional
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public CustomerResponseDTO createCustomer(CustomerRequestDTO requestDTO) {
        if (customerRepository.existsByEmail(requestDTO.email())) {
            throw new EmailAlreadyExistsException("A customer with email " + requestDTO.email() + " already exists.");
        }

        if (customerRepository.existsByIdentityNumber(requestDTO.identityNumber())) {
            throw new EmailAlreadyExistsException("A customer with identity number " + requestDTO.identityNumber() + " already exists.");
        }

        Customer customer = customerMapper.toEntity(requestDTO);
        Customer savedCustomer = customerRepository.save(customer);

        return customerMapper.toResponseDTO(savedCustomer);
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerResponseDTO getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with ID: " + id));
        return customerMapper.toResponseDTO(customer);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomerResponseDTO> getAllCustomers() {
        return customerRepository.findAll().stream()
                .map(customerMapper::toResponseDTO)
                .toList();
    }

    @Override
    public CustomerResponseDTO updateCustomer(Long id, CustomerRequestDTO requestDTO) {
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with ID: " + id));

        if (!existingCustomer.getEmail().equals(requestDTO.email()) && customerRepository.existsByEmail(requestDTO.email())) {
            throw new EmailAlreadyExistsException("Email " + requestDTO.email() + " is already in use by another account.");
        }

        existingCustomer.setFirstName(requestDTO.firstName());
        existingCustomer.setLastName(requestDTO.lastName());
        existingCustomer.setEmail(requestDTO.email());
        existingCustomer.setIdentityNumber(requestDTO.identityNumber());
        existingCustomer.setPhoneNumber(requestDTO.phoneNumber());
        existingCustomer.setCustomerType(requestDTO.customerType());

        Customer updatedCustomer = customerRepository.save(existingCustomer);
        return customerMapper.toResponseDTO(updatedCustomer);
    }
}