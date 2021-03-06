package org.academiadecodigo.bestwebappever.persistence.dao.jpa;

import org.academiadecodigo.bestwebappever.persistence.dao.SpecimenDao;
import org.academiadecodigo.bestwebappever.persistence.model.Specimen;
import org.springframework.stereotype.Repository;

/**
 * A JPA {@link SpecimenDao} implementation
 */
@Repository
public class JpaSpecimenDao extends GenericJpaDao<Specimen> implements SpecimenDao {

    /**
     * @see GenericJpaDao#GenericJpaDao(Class)
     */
    public JpaSpecimenDao() {
        super(Specimen.class);
    }

}
