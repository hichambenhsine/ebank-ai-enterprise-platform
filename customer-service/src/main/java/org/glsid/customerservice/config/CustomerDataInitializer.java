package org.glsid.customerservice.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.glsid.customerservice.entities.Customer;
import org.glsid.customerservice.enums.CustomerStatus;
import org.glsid.customerservice.enums.CustomerType;
import org.glsid.customerservice.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

/**
 * Configuration class responsible for seeding initial customer sample data into the database
 * during local development and testing environments.
 */
@Configuration
@RequiredArgsConstructor
@Slf4j
public class CustomerDataInitializer {

    @Bean
    @Profile("!prod")
    CommandLineRunner commandLineRunner(CustomerRepository customerRepository) {
        return args -> {
            if (customerRepository.count() == 0) {
                log.info("Seeding initial customer sample data...");

                Customer customer1 = Customer.builder()
                        .firstName("Mohamed")
                        .lastName("Youssfi")
                        .email("mohamed.youssfi@ebank.net")
                        .identityNumber("CIN123456")
                        .phoneNumber("+212600112233")
                        .customerType(CustomerType.INDIVIDUAL)
                        .status(CustomerStatus.ACTIVE)
                        .build();

                Customer customer2 = Customer.builder()
                        .firstName("Hassan")
                        .lastName("El Amrani")
                        .email("hassan.elamrani@ebank.net")
                        .identityNumber("CIN654321")
                        .phoneNumber("+212661998877")
                        .customerType(CustomerType.INDIVIDUAL)
                        .status(CustomerStatus.PENDING_VERIFICATION)
                        .build();

                Customer customer3 = Customer.builder()
                        .firstName("eBank Corporate")
                        .lastName("HQ")
                        .email("contact@corporate-ebank.net")
                        .identityNumber("CORP998877")
                        .phoneNumber("+212522001122")
                        .customerType(CustomerType.CORPORATE)
                        .status(CustomerStatus.ACTIVE)
                        .build();

                customerRepository.saveAll(List.of(customer1, customer2, customer3));
                log.info("Successfully seeded 3 customer profiles.");
            } else {
                log.info("Database already contains customer data. Skipping sample data initialization.");
            }
        };
    }
}