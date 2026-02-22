package org.example.vtschool_mps_2526.models.utils;

import org.example.vtschool_mps_2526.models.dto.CourseDTO;
import org.example.vtschool_mps_2526.models.entities.CourseEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE, componentModel = "spring")
public interface CourseMapper {
    CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);
    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "name", target = "name"),
    })
   CourseDTO mapCourseEntityToCourseDTO(CourseEntity courseEntity);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "name", target = "name"),
    })
   CourseEntity mapCourseDTOToCourseEntity(CourseDTO courseDTO);
}
