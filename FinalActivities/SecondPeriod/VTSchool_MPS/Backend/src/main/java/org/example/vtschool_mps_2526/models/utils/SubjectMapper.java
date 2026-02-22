package org.example.vtschool_mps_2526.models.utils;

import org.example.vtschool_mps_2526.models.dto.SubjectDTO;
import org.example.vtschool_mps_2526.models.entities.SubjectEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE, componentModel = "spring")
public interface SubjectMapper {
    SubjectMapper INSTANCE = Mappers.getMapper(SubjectMapper.class);

    @Mappings({
            @Mapping(source ="id", target="id"),
            @Mapping(source ="name", target="name"),
            @Mapping(source ="year", target="year"),
            @Mapping(source ="hours", target="hours"),
    })
    SubjectDTO mapSubjectEntityToSubjectDTO(SubjectEntity subjectEntity);

    @Mappings({
            @Mapping(source ="id", target="id"),
            @Mapping(source ="name", target="name"),
            @Mapping(source ="year", target="year"),
            @Mapping(source ="hours", target="hours"),
    })
    SubjectEntity mapSubjectDTOToSubjectEntity(SubjectDTO subjectDTO);


}
