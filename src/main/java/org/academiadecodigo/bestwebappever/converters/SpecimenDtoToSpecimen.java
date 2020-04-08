package org.academiadecodigo.bestwebappever.converters;

import org.academiadecodigo.bestwebappever.command.SpecimenDto;
import org.academiadecodigo.bestwebappever.persistence.model.Specimen;
import org.academiadecodigo.bestwebappever.services.SpecimenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * A {@link Converter} implementation, responsible for {@link SpecimenDto} to {@link Specimen} type conversion
 */
@Component
public class SpecimenDtoToSpecimen extends AbstractConverter<SpecimenDto, Specimen> {

    private SpecimenService specimenService;

    /**
     * Sets the recipient service
     *
     * @param specimenService the specimen service to set
     */
    @Autowired
    public void setSpecimenService(SpecimenService specimenService) {
        this.specimenService = specimenService;
    }

    /**
     * Converts the recipient model object into a specimen DTO
     * @param specimenDto the recipient
     * @return the recipient DTO
     */

    @Override
    public Specimen convert(SpecimenDto specimenDto) {

        Specimen specimen = (specimenDto.getId() != null ? specimenService.get(specimenDto.getId()) : new Specimen());

        specimen.setSpecimenNumber(specimenDto.getSpecimenNumber());
        specimen.setName(specimenDto.getName());
        specimen.setEmail(specimenDto.getEmail());
        specimen.setPhone(specimenDto.getPhone());
        specimen.setDescription(specimenDto.getDescription());

        return specimen;

    }
}
