package org.example.vtschool_mps_2526.models.utils;

import javax.annotation.processing.Generated;
import org.example.vtschool_mps_2526.models.dto.CourseDTO;
import org.example.vtschool_mps_2526.models.entities.CourseEntity;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-02-22T11:11:19+0100",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.45.0.v20260128-0750, environment: Java 21.0.9 (Eclipse Adoptium)"
)
@Component
public class CourseMapperImpl implements CourseMapper {

    @Override
    public CourseDTO mapCourseEntityToCourseDTO(CourseEntity courseEntity) {
        if ( courseEntity == null ) {
            return null;
        }

        CourseDTO courseDTO = new CourseDTO();

        courseDTO.setId( courseEntity.getId() );
        courseDTO.setName( courseEntity.getName() );

        return courseDTO;
    }

    @Override
    public CourseEntity mapCourseDTOToCourseEntity(CourseDTO courseDTO) {
        if ( courseDTO == null ) {
            return null;
        }

        CourseEntity courseEntity = new CourseEntity();

        courseEntity.setId( courseDTO.getId() );
        courseEntity.setName( courseDTO.getName() );

        return courseEntity;
    }
}
