package org.example.vtschool_mps_2526.service;

import org.example.vtschool_mps_2526.models.dao.StudentsDAO;
import org.example.vtschool_mps_2526.models.dto.StudentsDTO;
import org.example.vtschool_mps_2526.models.entities.StudentEntity;
import org.example.vtschool_mps_2526.models.utils.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class serviceStudent {

    @Autowired
    private StudentsDAO studentsDAO;
    @Autowired
    private StudentMapper studentMapper;

    public List<StudentsDTO> getStudents() {
         List<StudentsDTO> studentsList = new ArrayList<>();

         for(StudentEntity studentEntity : studentsDAO.findAll()){
             studentsList.add(StudentMapper.INSTANCE.mapStudentEntityToDTO(studentEntity));
         }
         return studentsList;
    }

    public StudentsDTO getStudentById(int idcard) {
       Optional<StudentEntity> studentEntity = studentsDAO.findById(idcard);

        return studentEntity.isPresent() ? StudentMapper.INSTANCE.mapStudentEntityToDTO(studentEntity.get()) : null;
    }

    public StudentEntity saveStudent(StudentsDTO student) {
        Optional<StudentEntity> optional = studentsDAO.findById((student.getIdcard()));

        if(!optional.isPresent()){
        return studentsDAO.save(StudentMapper.INSTANCE.mapStudentDTOToEntity(student));
        }
        return null;
    }

    public boolean deleteStudent(int idcard) {
        Optional<StudentEntity> optional = studentsDAO.findById(idcard);

        if(optional.isPresent()){
            studentsDAO.deleteById(Integer.valueOf(idcard));
            return true;
        }
        return false;
    }

    public StudentEntity updateStudent(StudentsDTO student) {

        Optional<StudentEntity> optional = studentsDAO.findById((student.getIdcard()));

        if(optional.isPresent()){

            return studentsDAO.save(StudentMapper.INSTANCE.mapStudentDTOToEntity(student));
        }
        return null;
    }

}
