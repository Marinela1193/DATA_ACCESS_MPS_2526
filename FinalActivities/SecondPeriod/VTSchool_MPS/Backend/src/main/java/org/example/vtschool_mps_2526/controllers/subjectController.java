package org.example.vtschool_mps_2526.controllers;

import org.example.vtschool_mps_2526.models.dto.SubjectDTO;
import org.example.vtschool_mps_2526.models.entities.SubjectEntity;
import org.example.vtschool_mps_2526.service.serviceSubject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/subjects")
class subjectController {
    @Autowired
    private serviceSubject serviceSubject;

    @GetMapping("/")
    public ResponseEntity<?> getSubjects() {
        return ResponseEntity.ok(serviceSubject.getSubjects());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSubjectById(@Validated @PathVariable int id) {

        SubjectDTO subjectDTO = serviceSubject.getSubjectById(id);

        return subjectDTO != null ? ResponseEntity.ok(subjectDTO) : ResponseEntity.notFound().build();
    }

    @PostMapping("/add")
    public ResponseEntity<?> createSubject(@Validated @RequestBody SubjectDTO subjectDto) {

        SubjectEntity subject = serviceSubject.saveSubject(subjectDto);
        if(subject != null){
            return ResponseEntity.badRequest().build();
        }
        serviceSubject.saveSubject(subjectDto);
        return ResponseEntity.ok().build();

    }

    @PutMapping("/update")
    public ResponseEntity<?> updateSubject(@Validated @RequestBody SubjectDTO subjectDto) {
        SubjectEntity subject = serviceSubject.updateSubject(subjectDto);
        if(subject != null){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteSubject(@Validated @PathVariable Integer id) {

        if(serviceSubject.deleteSubjectById(id)){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
