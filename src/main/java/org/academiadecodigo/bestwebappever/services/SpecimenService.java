package org.academiadecodigo.bestwebappever.services;

import org.academiadecodigo.bestwebappever.persistence.model.Specimen;

import java.util.List;

/**
 * Common interface for recipient services, provides a method to get the recipient
 */
public interface SpecimenService {

    /**
     * Gets the recipient
     *
     * @param id the specimen id
     * @return the specimen
     */
    Specimen get(Integer id);

    List<Specimen> listSpecimen();
}
