package org.example.employees2.models.dao;
import org.example.employees2.models.dto.EmployeeDTO;
import org.example.employees2.models.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeEntityDAO extends CrudRepository<EmployeeEntity, Integer> {

    boolean existsById(Integer id);

    List<EmployeeEntity> findByDeptno(Integer deptno);


    List<EmployeeEntity> findByJob(String job);
}
