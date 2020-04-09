package org.academiadecodigo.bestwebappever.services;

import org.academiadecodigo.bestwebappever.exceptions.AssociationExistsException;
import org.academiadecodigo.bestwebappever.exceptions.CustomerNotFoundException;
import org.academiadecodigo.bestwebappever.exceptions.RecipientNotFoundException;
import org.academiadecodigo.bestwebappever.persistence.Security;
import org.academiadecodigo.bestwebappever.persistence.dao.CustomerDao;
import org.academiadecodigo.bestwebappever.persistence.dao.SpecimenDao;
import org.academiadecodigo.bestwebappever.persistence.model.Customer;
import org.academiadecodigo.bestwebappever.persistence.model.Specimen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerDao customerDao;
    private SpecimenDao specimenDao;

    /**
     * Sets the customer data access object
     *
     * @param customerDao the account DAO to set
     */
    @Autowired
    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    /**
     * Sets the recipient data access object
     *
     * @param specimenDao the specimen DAO to set
     */
    @Autowired
    public void setRecipientDao(SpecimenDao specimenDao) {
        this.specimenDao = specimenDao;
    }


    /**
     * @see CustomerService#get(Integer)
     */
    @Override
    public Customer get(Integer id) {
        return customerDao.findById(id);
    }

    /**
     * @see CustomerService#save(Customer)
     */
    @Transactional
    @Override
    public Customer save(Customer customer) {
        return customerDao.saveOrUpdate(customer);
    }

    /**
     * @see CustomerService#delete(Integer)
     */
    @Transactional
    @Override
    public void delete(Integer id) throws CustomerNotFoundException, AssociationExistsException {

        Customer customer = customerDao.findById(id);

        if (customer == null) {
            throw new CustomerNotFoundException();
        }

        if (!customer.getSpecimens().isEmpty()) {
            throw new AssociationExistsException();
        }

        customerDao.delete(id);
    }

    /**
     * @see CustomerService#list()
     */
    @Override
    public List<Customer> list() {
        return customerDao.findAll();
    }

    /**
     * @see CustomerService#listSpecimens(Integer)
     */
    @Transactional(readOnly = true)
    @Override
    public List<Specimen> listSpecimens(Integer id) throws CustomerNotFoundException {

        // check then act logic requires transaction,
        // even if read only

        Customer customer = customerDao.findById(id);

        if (customer == null) {
            throw new CustomerNotFoundException();
        }

        return new ArrayList<>(customerDao.findById(id).getSpecimens());
    }

    /**
     * @see CustomerService#addSpecimen(Integer, Specimen)
     */
    @Transactional
    @Override
    public Specimen addSpecimen(Integer id, Specimen specimen) throws CustomerNotFoundException {

        Customer customer = customerDao.findById(id);

        if (customer == null) {
            throw new CustomerNotFoundException();
        }

        if (specimen.getId() == null) {
            customer.addSpecimen(specimen);
            customerDao.saveOrUpdate(customer);
        } else {
            specimenDao.saveOrUpdate(specimen);
        }
        return customer.getSpecimens().get(customer.getSpecimens().size() - 1);
    }

    /**
     * @see CustomerService#removeSpecimen(Integer, Integer)
     */
    @Transactional
    @Override
    public void removeSpecimen(Integer id, Integer recipientId) throws CustomerNotFoundException, RecipientNotFoundException {

        Customer customer = customerDao.findById(id);
        Specimen specimen = specimenDao.findById(recipientId);

        if (customer == null) {
            throw new CustomerNotFoundException();
        }

        if (specimen == null || !specimen.getCustomer().getId().equals(id)) {
            throw new RecipientNotFoundException();
        }

        customer.removeSpecimen(specimen);
        customerDao.saveOrUpdate(customer);
    }

    @Transactional
    @Override
    public void transferSpecimen(Integer sid, Integer rip, Integer specimenId) throws CustomerNotFoundException, RecipientNotFoundException {

        Customer sCustomer = customerDao.findById(sid);
        Customer rCustomer = customerDao.findById(rip);

        if (sCustomer == null || rCustomer == null) {
            throw new CustomerNotFoundException();
        }

        Specimen specimen = specimenDao.findById(specimenId);

        if (!sCustomer.getSpecimens().contains(specimen)) {
            throw new RecipientNotFoundException();
        }

        // TODO missing validations?

        sCustomer.removeSpecimen(specimenDao.findById(specimenId));
        rCustomer.addSpecimen(specimenDao.findById(specimenId));

        customerDao.saveOrUpdate(sCustomer);
        customerDao.saveOrUpdate(rCustomer);
    }

    @Transactional
    @Override
    public boolean login(String username, String password) {

        Customer customer1 = null;

        for (Customer customer : list()) {
            if(customer.getUsername() == username){
                customer1=customer;
            }

        }

        if(customer1==null){
            return false;
        }

        return customer1.getPassword().equals(Security.getHash(password));
    }

    @Transactional
    @Override
    public Customer getByUsername(String username) {

        Customer customer1 = null;

        for (Customer customer : list()) {
            if(customer.getUsername() == username){
                customer1=customer;
            }

        }

        return customer1;
    }
}
