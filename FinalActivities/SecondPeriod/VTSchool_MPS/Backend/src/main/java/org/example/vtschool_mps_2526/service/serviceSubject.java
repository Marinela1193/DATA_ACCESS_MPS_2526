package org.example.vtschool_mps_2526.service;

import org.example.vtschool_mps_2526.models.dao.SubjectDAO;
import org.example.vtschool_mps_2526.models.dto.SubjectDTO;
import org.example.vtschool_mps_2526.models.entities.SubjectEntity;
import org.example.vtschool_mps_2526.models.utils.SubjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class serviceSubject {
    @Autowired
    private SubjectDAO subjectDAO;

    public List<SubjectDTO> getSubjects() {
        List<SubjectDTO> subjectsList = new ArrayList<>();
        for (SubjectEntity subjectEntity : subjectDAO.findAll()) {
            subjectsList.add(SubjectMapper.INSTANCE.mapSubjectEntityToSubjectDTO(subjectEntity));
        }
        return subjectsList;
    }

    public SubjectDTO getSubjectById(int id) {
        Optional<SubjectEntity> subjectEntity = subjectDAO.findById(id);

        return subjectEntity.isPresent() ? SubjectMapper.INSTANCE.mapSubjectEntityToSubjectDTO(subjectEntity.get()) : null;
    }


    public SubjectEntity saveSubject(SubjectDTO subject) {
        Optional<SubjectEntity> subjectEntity = subjectDAO.findById(subject.getId());

        if(!subjectEntity.isPresent()) {
            return subjectDAO.save(SubjectMapper.INSTANCE.mapSubjectDTOToSubjectEntity(subject));
        }
        return null;
    }

    public SubjectEntity updateSubject(SubjectDTO subject) {
        Optional<SubjectEntity> subjectEntity = subjectDAO.findById(subject.getId());

        if(subjectEntity.isPresent()) {
            return subjectDAO.save(SubjectMapper.INSTANCE.mapSubjectDTOToSubjectEntity(subject));
        }
        return null;
    }

    public boolean deleteSubjectById(int id) {
        Optional<SubjectEntity> subjectEntity = subjectDAO.findById(id);

        if(subjectEntity.isPresent()) {
            subjectDAO.deleteById(id);
            return true;
        }
        return false;
    }

}
