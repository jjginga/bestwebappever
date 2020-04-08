package org.academiadecodigo.bestwebappever.converters;

import org.academiadecodigo.bestwebappever.command.SpecimenDto;
import org.academiadecodigo.bestwebappever.persistence.model.Specimen;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * A {@link Converter} implementation, responsible for {@link Specimen} to {@link SpecimenDto} type conversion
 */
@Component
public class SpecimenToSpecimenDto extends AbstractConverter<Specimen, SpecimenDto> {

    /**
     * Converts the recipient model object into a specimen DTO
     * @param specimen the recipient
     * @return the specimen DTO
     */
    @Override
    public SpecimenDto convert(Specimen specimen) {

        SpecimenDto specimenDto = new SpecimenDto();
        specimen.setId(specimen.getId());
        specimen.setSpecimenNumber(specimen.getSpecimenNumber());
        specimen.setName(specimen.getName());
        specimen.setEmail(specimen.getEmail());
        specimen.setPhone(specimen.getPhone());
        specimen.setDescription(specimen.getDescription());

        return specimenDto;
    }
}
