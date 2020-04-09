package org.academiadecodigo.bestwebappever.persistence.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "specimen")
public class Specimen extends AbstractModel {

    private String name;
    private String description;

    @ManyToOne
    private Customer customer;

    /**
     * Gets the name of the recipient
     *
     * @return the recipient name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the recipient
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the recipient description
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the recipient description
     *
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the customer
     *
     * @return the customer
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Sets the customer
     *
     * @param customer the customer to set
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * @see Object#toString()
     */
    @Override
    public String toString() {
        return "Specimen{" +
                "name='" + name + '\'' +
               ", description='" + description + '\'' +
                "} " + super.toString();
    }
}
