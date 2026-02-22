package org.example.vtschool_mps_2526.models.utils;

import javax.annotation.processing.Generated;
import org.example.vtschool_mps_2526.models.dto.SubjectDTO;
import org.example.vtschool_mps_2526.models.entities.SubjectEntity;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-02-22T11:11:19+0100",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.45.0.v20260128-0750, environment: Java 21.0.9 (Eclipse Adoptium)"
)
@Component
public class SubjectMapperImpl implements SubjectMapper {

    @Override
    public SubjectDTO mapSubjectEntityToSubjectDTO(SubjectEntity subjectEntity) {
        if ( subjectEntity == null ) {
            return null;
        }

        SubjectDTO subjectDTO = new SubjectDTO();

        subjectDTO.setId( subjectEntity.getId() );
        subjectDTO.setName( subjectEntity.getName() );
        subjectDTO.setYear( subjectEntity.getYear() );
        subjectDTO.setHours( subjectEntity.getHours() );

        return subjectDTO;
    }

    @Override
    public SubjectEntity mapSubjectDTOToSubjectEntity(SubjectDTO subjectDTO) {
        if ( subjectDTO == null ) {
            return null;
        }

        SubjectEntity subjectEntity = new SubjectEntity();

        subjectEntity.setId( subjectDTO.getId() );
        subjectEntity.setName( subjectDTO.getName() );
        subjectEntity.setYear( subjectDTO.getYear() );
        subjectEntity.setHours( subjectDTO.getHours() );

        return subjectEntity;
    }
}
