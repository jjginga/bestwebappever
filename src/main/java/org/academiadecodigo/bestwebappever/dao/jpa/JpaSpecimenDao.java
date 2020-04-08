package org.academiadecodigo.bestwebappever.dao.jpa;

import org.academiadecodigo.bestwebappever.dao.SpecimenDao;
import org.academiadecodigo.bestwebappever.persistence.model.Recipient;
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
