package org.example.employees2.models.dao;

import org.example.employees2.models.entities.DeptEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DeptEntityDAO extends CrudRepository<DeptEntity, Integer> {

}
