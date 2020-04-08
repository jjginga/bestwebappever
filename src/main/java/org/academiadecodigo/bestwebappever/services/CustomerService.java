package org.academiadecodigo.bestwebappever.services;

import org.academiadecodigo.bestwebappever.exceptions.*;
import org.academiadecodigo.bestwebappever.persistence.model.Customer;
import org.academiadecodigo.bestwebappever.persistence.model.Specimen;

import java.util.List;

/**
 * Common interface for customer services, provides methods to manage customers
 */
public interface CustomerService {

    /**
     * Gets the customer with the given id
     *
     * @param id the customer id
     * @return the customer
     */
    Customer get(Integer id);


    /**
     * Saves a customer
     *
     * @param customer the customer to save
     * @return the saved custoemr
     */
    Customer save(Customer customer);

    /**
     * Deletes the customer
     *
     * @param id the customer id
     * @throws CustomerNotFoundException
     * @throws AssociationExistsException
     */
    void delete(Integer id) throws AssociationExistsException, CustomerNotFoundException;

    /**
     * Gets a list of the customers
     *
     * @return the customers list
     */
    List<Customer> list();

    /**
     * Gets the list of customer recipients
     *
     * @param id the customer id
     * @return the list of specimens of the customer
     * @throws CustomerNotFoundException
     */
    List<Specimen> listSpecimens(Integer id) throws CustomerNotFoundException;

    /**
     * Adds a recipient to the customer
     *
     * @param id        the customer id
     * @param specimen the specimen id
     * @throws CustomerNotFoundException
     */
    Specimen addSpecimen(Integer id, Specimen specimen)
            throws CustomerNotFoundException;

    /**
     * Removes a recipient from the customer
     *
     * @param id          the customer id
     * @param specimenId the specimen id
     * @throws CustomerNotFoundException
     * @throws RecipientNotFoundException
     */
    void removeSpecimen(Integer id, Integer specimenId)
            throws CustomerNotFoundException,  RecipientNotFoundException;

    void transferSpecimen(Integer sid, Integer rip, Integer specimenId);
}
