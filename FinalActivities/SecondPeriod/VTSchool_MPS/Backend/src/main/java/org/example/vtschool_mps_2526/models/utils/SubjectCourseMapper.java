package org.example.vtschool_mps_2526.models.utils;

import org.example.vtschool_mps_2526.models.dto.SubjectCourseDTO;
import org.example.vtschool_mps_2526.models.entities.SubjectCourseEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE, componentModel = "spring")
public interface SubjectCourseMapper {
    SubjectCourseMapper INSTANCE = Mappers.getMapper(SubjectCourseMapper.class);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "subject", target = "subject"),
            @Mapping(source = "course", target = "course")
    })
    SubjectCourseDTO mapSubjectCourseEntityToSubjectCourseDTO(SubjectCourseEntity subjectCourseEntity);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "subject", target = "subject"),
            @Mapping(source = "course", target = "course")
    })
    SubjectCourseEntity mapSubjectCourseDTOToSubjectCourseEntity(SubjectCourseDTO subjectCourseDTO);
}
