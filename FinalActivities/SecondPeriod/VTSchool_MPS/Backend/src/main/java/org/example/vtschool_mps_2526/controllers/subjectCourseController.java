package org.example.vtschool_mps_2526.controllers;

import org.example.vtschool_mps_2526.models.dto.SubjectCourseDTO;
import org.example.vtschool_mps_2526.models.entities.CourseEntity;
import org.example.vtschool_mps_2526.models.entities.SubjectCourseEntity;
import org.example.vtschool_mps_2526.models.entities.SubjectEntity;
import org.example.vtschool_mps_2526.service.serviceSubjectCourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/subjectCourses")
class subjectCourseController {
    @Autowired
    private serviceSubjectCourse serviceSubjectCourse;

    @GetMapping("/")
    public ResponseEntity<?> getAllSubjectCourses() {
        return ResponseEntity.ok(serviceSubjectCourse.getAllSubjectCourses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSubjectCourseById(@Validated @PathVariable int id) {

        SubjectCourseDTO subjectCourseDTO = serviceSubjectCourse.getSubjectCourseById(id);

        return subjectCourseDTO != null ? ResponseEntity.ok(subjectCourseDTO) : ResponseEntity.notFound().build();
    }

    @PostMapping("/add")
    public ResponseEntity<?> addSubjectCourse(@Validated @RequestBody SubjectCourseDTO subjectCourseDTO) {

        SubjectCourseEntity entity = serviceSubjectCourse.saveSubjectCourse(subjectCourseDTO);
       if(entity != null) {
           return ResponseEntity.badRequest().build();
       }
       serviceSubjectCourse.saveSubjectCourse(subjectCourseDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateSubjectCourse(@Validated @RequestBody SubjectCourseDTO subjectCourseDTO) {
        SubjectCourseEntity entity = serviceSubjectCourse.updateSubjectCourse(subjectCourseDTO);
        if(entity != null) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteSubjectCourse(@Validated @PathVariable int id) {
        if(serviceSubjectCourse.deleteSubjectCourseById(id)){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}