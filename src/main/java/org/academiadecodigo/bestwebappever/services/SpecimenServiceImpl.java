package org.academiadecodigo.bestwebappever.services;

import org.academiadecodigo.bestwebappever.persistence.dao.SpecimenDao;
import org.academiadecodigo.bestwebappever.persistence.model.Specimen;
import org.springframework.beans.factory.annotation.Autowired;

public class SpecimenServiceImpl implements SpecimenService {

    private SpecimenDao specimenDao;

    /**
     * Sets the recipient data access object
     *
     * @param specimenDao the recipient DAO to set
     */
    @Autowired
    public void setSpecimenDao(SpecimenDao specimenDao) {
        this.specimenDao = specimenDao;
    }

    /**
     * @see SpecimenService#get(Integer)
     */
    @Override
    public Specimen get(Integer id) {
        return specimenDao.findById(id);
    }

}
