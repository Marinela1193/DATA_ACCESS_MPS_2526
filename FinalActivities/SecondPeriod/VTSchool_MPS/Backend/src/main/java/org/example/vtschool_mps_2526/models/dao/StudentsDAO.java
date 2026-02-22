package org.example.vtschool_mps_2526.models.dao;

import org.example.vtschool_mps_2526.models.entities.StudentEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface StudentsDAO extends CrudRepository<StudentEntity, Integer> {


    Optional<StudentEntity> findByFirstname(String firstname);

}
