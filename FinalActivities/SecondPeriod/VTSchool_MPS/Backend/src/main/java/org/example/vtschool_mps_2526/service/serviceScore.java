package org.example.vtschool_mps_2526.service;

import org.example.vtschool_mps_2526.models.dao.ScoreDAO;
import org.example.vtschool_mps_2526.models.dto.ScoreDTO;
import org.example.vtschool_mps_2526.models.entities.ScoreEntity;
import org.example.vtschool_mps_2526.models.utils.ScoreMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class serviceScore {

    @Autowired
    private ScoreDAO scoreDAO;

    public List<ScoreDTO> getScores() {
        List<ScoreDTO> scoreList = new ArrayList<>();

        for(ScoreEntity scoreEntity : scoreDAO.findAll()) {
            scoreList.add(ScoreMapper.INSTANCE.mapScoreEntityToScoreDTO(scoreEntity));
        }
        return scoreList;
    }

    public ScoreDTO getScoresById(int id) {
        Optional<ScoreEntity> score = scoreDAO.findById(id);

        return score.isPresent() ?ScoreMapper.INSTANCE.mapScoreEntityToScoreDTO(score.get()):null;
    }


    public ScoreEntity saveScore(ScoreDTO score) {

        Optional<ScoreEntity> scoreEntity = scoreDAO.findById(score.getId());
        if(!scoreEntity.isPresent()) {
            return scoreDAO.save(ScoreMapper.INSTANCE.mapScoreDTOToScoreEntity(score));
        }

        return null;
    }


    public ScoreEntity updateScore(ScoreDTO score) {

        Optional<ScoreEntity> scoreEntity = scoreDAO.findById(score.getId());
        if(scoreEntity.isPresent()) {
            return scoreDAO.save(ScoreMapper.INSTANCE.mapScoreDTOToScoreEntity(score));
        }
        return null;
    }

    public boolean deleteScoreById(int id) {
        Optional<ScoreEntity> scoreEntity = scoreDAO.findById(id);

        if (scoreEntity.isPresent()) {
            scoreDAO.deleteById(id);
            return true;
        }
        return false;
    }


}
