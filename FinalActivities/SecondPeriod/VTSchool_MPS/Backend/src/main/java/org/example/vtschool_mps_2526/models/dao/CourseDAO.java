package org.example.vtschool_mps_2526.models.dao;

import org.example.vtschool_mps_2526.models.entities.CourseEntity;
import org.springframework.data.repository.CrudRepository;

public interface CourseDAO extends CrudRepository<CourseEntity, Integer> {
}
