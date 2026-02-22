package org.example.vtschool_mps_2526.models.utils;

import org.example.vtschool_mps_2526.models.dto.EnrollmentDTO;
import org.example.vtschool_mps_2526.models.entities.EnrollmentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE, componentModel = "spring")
public interface EnrollmentMapper {
    EnrollmentMapper INSTANCE = Mappers.getMapper(EnrollmentMapper.class);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "course", target = "course"),
            @Mapping(source = "year", target = "year"),
            @Mapping(source = "scores", target = "scores")
    })
    EnrollmentDTO mapEnrollmentEntityToEnrollmentDTO(EnrollmentEntity enrollmentEntity);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "course", target = "course"),
            @Mapping(source = "year", target = "year"),
            @Mapping(source = "scores", target = "scores")
    })
    EnrollmentEntity mapEnrollmentDTOToEnrollmentEntity(EnrollmentDTO enrollmentDTO);
}
