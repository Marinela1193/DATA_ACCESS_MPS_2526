package org.example.vtschool_mps_2526.models.dao;

import org.example.vtschool_mps_2526.models.entities.ScoreEntity;
import org.springframework.data.repository.CrudRepository;

public interface ScoreDAO extends CrudRepository<ScoreEntity, Integer> {
}
