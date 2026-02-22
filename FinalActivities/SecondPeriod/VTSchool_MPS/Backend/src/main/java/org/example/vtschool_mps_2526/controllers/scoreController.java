package org.example.vtschool_mps_2526.controllers;

import org.example.vtschool_mps_2526.models.dao.ScoreDAO;
import org.example.vtschool_mps_2526.models.dto.ScoreDTO;
import org.example.vtschool_mps_2526.models.entities.ScoreEntity;
import org.example.vtschool_mps_2526.service.serviceScore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/scores")
class scoreController {
    @Autowired
    serviceScore serviceScore;

    @Autowired
    private ScoreDAO scoreDAO;

    @GetMapping("/")
    public ResponseEntity<?> getScores() {
        return ResponseEntity.ok(serviceScore.getScores());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getScore(@Validated @PathVariable Integer id) {

        ScoreDTO score = serviceScore.getScoresById(id);

        if(score == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(score);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addScore(@Validated @RequestBody ScoreDTO score) {

        ScoreEntity scoreEntity = serviceScore.saveScore(score);
        if (scoreEntity != null) {
            return ResponseEntity.badRequest().build();
        }
        serviceScore.saveScore(score);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/update")
    public ResponseEntity<?> updateScore(@Validated @RequestBody ScoreDTO score) {

        ScoreEntity scoreEntity = serviceScore.updateScore(score);

        if (scoreEntity != null) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteScore(@Validated @PathVariable int id) {
        if (serviceScore.deleteScoreById(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
