package org.academiadecodigo.bestwebappever.persistence.dao.jpa;

import org.academiadecodigo.bestwebappever.persistence.dao.CustomerDao;
import org.academiadecodigo.bestwebappever.persistence.model.Customer;
import org.springframework.stereotype.Repository;

/**
 * A JPA {@link CustomerDao} implementation
 */
@Repository
public class JpaCustomerDao extends GenericJpaDao<Customer> implements CustomerDao {

    /**
     * @see GenericJpaDao#GenericJpaDao(Class)
     */
    public JpaCustomerDao() {
        super(Customer.class);
    }
}