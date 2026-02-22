package org.example.vtschool_mps_2526.service;

import org.example.vtschool_mps_2526.models.dao.SubjectCourseDAO;
import org.example.vtschool_mps_2526.models.dto.SubjectCourseDTO;
import org.example.vtschool_mps_2526.models.entities.SubjectCourseEntity;
import org.example.vtschool_mps_2526.models.utils.SubjectCourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class serviceSubjectCourse {

    @Autowired
    private SubjectCourseDAO subjectCourseDAO;

    public List<SubjectCourseDTO> getAllSubjectCourses() {
        List<SubjectCourseDTO> subjectCourseEntityList = new ArrayList<>();

        for (SubjectCourseEntity subjectCourseEntity : subjectCourseDAO.findAll()) {
            subjectCourseEntityList.add(SubjectCourseMapper.INSTANCE.mapSubjectCourseEntityToSubjectCourseDTO(subjectCourseEntity));
        }
        return subjectCourseEntityList;
    }

    public SubjectCourseDTO getSubjectCourseById(int id) {
        Optional<SubjectCourseEntity> subjectCourseEntity = subjectCourseDAO.findById(id);

        return subjectCourseEntity.isPresent() ? SubjectCourseMapper.INSTANCE.mapSubjectCourseEntityToSubjectCourseDTO(subjectCourseEntity.get()) : null;
    }


    public SubjectCourseEntity saveSubjectCourse(SubjectCourseDTO subjectCourseDTO) {
        Optional<SubjectCourseEntity> subjectCourseEntity = subjectCourseDAO.findById(subjectCourseDTO.getId());

        if(!subjectCourseEntity.isPresent()) {
            return subjectCourseDAO.save(SubjectCourseMapper.INSTANCE.mapSubjectCourseDTOToSubjectCourseEntity(subjectCourseDTO));
        }
        return null;
    }

    public SubjectCourseEntity updateSubjectCourse(SubjectCourseDTO subjectCourseDTO) {
        Optional<SubjectCourseEntity> subjectCourseEntity = subjectCourseDAO.findById(subjectCourseDTO.getId());

        if(subjectCourseEntity.isPresent()) {
            return subjectCourseDAO.save(SubjectCourseMapper.INSTANCE.mapSubjectCourseDTOToSubjectCourseEntity(subjectCourseDTO));
        }
        return null;
    }

    public boolean deleteSubjectCourseById(int id) {
        Optional<SubjectCourseEntity> subjectCourseEntity = subjectCourseDAO.findById(id);

        if(subjectCourseEntity.isPresent()) {
            subjectCourseDAO.deleteById(id);
            return true;
        }
        return false;
    }
}
