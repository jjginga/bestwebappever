package org.academiadecodigo.bestwebappever.converters;

import org.academiadecodigo.bestwebappever.command.CustomerDto;
import org.academiadecodigo.bestwebappever.persistence.model.Customer;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * A {@link Converter} implementation, responsible for {@link Customer} to {@link CustomerDto} type conversion
 */
@Component
public class CustomerToCustomerDto extends AbstractConverter<Customer, CustomerDto> {
    /**
     * Converts the customer model object into a customer DTO
     *
     * @param customer the customer
     * @return the customer DTO
     */
    @Override
    public CustomerDto convert(Customer customer) {

        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(customer.getId());
        customerDto.setFirstName(customer.getFirstName());
        customerDto.setLastName(customer.getLastName());
        customerDto.setEmail(customer.getEmail());
        customerDto.setPhone(customer.getPhone());
        customerDto.setUsername(customer.getUsername());
        customerDto.setPassword(customer.getPassword());

        return customerDto;
    }
}
