package org.example.vtschool_mps_2526.models.utils;

import javax.annotation.processing.Generated;
import org.example.vtschool_mps_2526.models.dto.CourseDTO;
import org.example.vtschool_mps_2526.models.dto.SubjectCourseDTO;
import org.example.vtschool_mps_2526.models.dto.SubjectDTO;
import org.example.vtschool_mps_2526.models.entities.CourseEntity;
import org.example.vtschool_mps_2526.models.entities.SubjectCourseEntity;
import org.example.vtschool_mps_2526.models.entities.SubjectEntity;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-02-22T11:11:19+0100",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.45.0.v20260128-0750, environment: Java 21.0.9 (Eclipse Adoptium)"
)
@Component
public class SubjectCourseMapperImpl implements SubjectCourseMapper {

    @Override
    public SubjectCourseDTO mapSubjectCourseEntityToSubjectCourseDTO(SubjectCourseEntity subjectCourseEntity) {
        if ( subjectCourseEntity == null ) {
            return null;
        }

        SubjectCourseDTO subjectCourseDTO = new SubjectCourseDTO();

        subjectCourseDTO.setId( subjectCourseEntity.getId() );
        subjectCourseDTO.setSubject( subjectEntityToSubjectDTO( subjectCourseEntity.getSubject() ) );
        subjectCourseDTO.setCourse( courseEntityToCourseDTO( subjectCourseEntity.getCourse() ) );

        return subjectCourseDTO;
    }

    @Override
    public SubjectCourseEntity mapSubjectCourseDTOToSubjectCourseEntity(SubjectCourseDTO subjectCourseDTO) {
        if ( subjectCourseDTO == null ) {
            return null;
        }

        SubjectCourseEntity subjectCourseEntity = new SubjectCourseEntity();

        subjectCourseEntity.setId( subjectCourseDTO.getId() );
        subjectCourseEntity.setSubject( subjectDTOToSubjectEntity( subjectCourseDTO.getSubject() ) );
        subjectCourseEntity.setCourse( courseDTOToCourseEntity( subjectCourseDTO.getCourse() ) );

        return subjectCourseEntity;
    }

    protected SubjectDTO subjectEntityToSubjectDTO(SubjectEntity subjectEntity) {
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

    protected CourseDTO courseEntityToCourseDTO(CourseEntity courseEntity) {
        if ( courseEntity == null ) {
            return null;
        }

        CourseDTO courseDTO = new CourseDTO();

        courseDTO.setId( courseEntity.getId() );
        courseDTO.setName( courseEntity.getName() );

        return courseDTO;
    }

    protected SubjectEntity subjectDTOToSubjectEntity(SubjectDTO subjectDTO) {
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

    protected CourseEntity courseDTOToCourseEntity(CourseDTO courseDTO) {
        if ( courseDTO == null ) {
            return null;
        }

        CourseEntity courseEntity = new CourseEntity();

        courseEntity.setId( courseDTO.getId() );
        courseEntity.setName( courseDTO.getName() );

        return courseEntity;
    }
}
