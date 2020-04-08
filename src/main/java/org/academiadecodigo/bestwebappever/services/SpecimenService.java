package org.academiadecodigo.bestwebappever.services;

import org.academiadecodigo.bestwebappever.persistence.model.Specimen;

/**
 * Common interface for recipient services, provides a method to get the recipient
 */
public interface SpecimenService {

    /**
     * Gets the recipient
     *
     * @param id the recipient id
     * @return the recipient
     */
    Specimen get(Integer id);
}
