package org.example.vtschool_mps_2526.models.utils;

import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.example.vtschool_mps_2526.models.dto.CourseDTO;
import org.example.vtschool_mps_2526.models.dto.EnrollmentDTO;
import org.example.vtschool_mps_2526.models.dto.ScoreDTO;
import org.example.vtschool_mps_2526.models.dto.SubjectDTO;
import org.example.vtschool_mps_2526.models.entities.CourseEntity;
import org.example.vtschool_mps_2526.models.entities.EnrollmentEntity;
import org.example.vtschool_mps_2526.models.entities.ScoreEntity;
import org.example.vtschool_mps_2526.models.entities.SubjectEntity;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-02-22T11:11:18+0100",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.45.0.v20260128-0750, environment: Java 21.0.9 (Eclipse Adoptium)"
)
@Component
public class EnrollmentMapperImpl implements EnrollmentMapper {

    @Override
    public EnrollmentDTO mapEnrollmentEntityToEnrollmentDTO(EnrollmentEntity enrollmentEntity) {
        if ( enrollmentEntity == null ) {
            return null;
        }

        EnrollmentDTO enrollmentDTO = new EnrollmentDTO();

        enrollmentDTO.setId( enrollmentEntity.getId() );
        enrollmentDTO.setCourse( courseEntityToCourseDTO( enrollmentEntity.getCourse() ) );
        enrollmentDTO.setYear( enrollmentEntity.getYear() );
        enrollmentDTO.setScores( scoreEntitySetToScoreDTOSet( enrollmentEntity.getScores() ) );

        return enrollmentDTO;
    }

    @Override
    public EnrollmentEntity mapEnrollmentDTOToEnrollmentEntity(EnrollmentDTO enrollmentDTO) {
        if ( enrollmentDTO == null ) {
            return null;
        }

        EnrollmentEntity enrollmentEntity = new EnrollmentEntity();

        enrollmentEntity.setId( enrollmentDTO.getId() );
        enrollmentEntity.setCourse( courseDTOToCourseEntity( enrollmentDTO.getCourse() ) );
        enrollmentEntity.setYear( enrollmentDTO.getYear() );
        enrollmentEntity.setScores( scoreDTOSetToScoreEntitySet( enrollmentDTO.getScores() ) );

        return enrollmentEntity;
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

    protected ScoreDTO scoreEntityToScoreDTO(ScoreEntity scoreEntity) {
        if ( scoreEntity == null ) {
            return null;
        }

        ScoreDTO scoreDTO = new ScoreDTO();

        scoreDTO.setId( scoreEntity.getId() );
        scoreDTO.setSubject( subjectEntityToSubjectDTO( scoreEntity.getSubject() ) );
        scoreDTO.setScore( scoreEntity.getScore() );

        return scoreDTO;
    }

    protected Set<ScoreDTO> scoreEntitySetToScoreDTOSet(Set<ScoreEntity> set) {
        if ( set == null ) {
            return null;
        }

        Set<ScoreDTO> set1 = new LinkedHashSet<ScoreDTO>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( ScoreEntity scoreEntity : set ) {
            set1.add( scoreEntityToScoreDTO( scoreEntity ) );
        }

        return set1;
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

    protected ScoreEntity scoreDTOToScoreEntity(ScoreDTO scoreDTO) {
        if ( scoreDTO == null ) {
            return null;
        }

        ScoreEntity scoreEntity = new ScoreEntity();

        scoreEntity.setId( scoreDTO.getId() );
        scoreEntity.setSubject( subjectDTOToSubjectEntity( scoreDTO.getSubject() ) );
        scoreEntity.setScore( scoreDTO.getScore() );

        return scoreEntity;
    }

    protected Set<ScoreEntity> scoreDTOSetToScoreEntitySet(Set<ScoreDTO> set) {
        if ( set == null ) {
            return null;
        }

        Set<ScoreEntity> set1 = new LinkedHashSet<ScoreEntity>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( ScoreDTO scoreDTO : set ) {
            set1.add( scoreDTOToScoreEntity( scoreDTO ) );
        }

        return set1;
    }
}
