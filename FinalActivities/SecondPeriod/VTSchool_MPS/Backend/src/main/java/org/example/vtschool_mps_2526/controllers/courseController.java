package org.example.vtschool_mps_2526.controllers;

import org.example.vtschool_mps_2526.models.dao.CourseDAO;
import org.example.vtschool_mps_2526.models.dto.CourseDTO;
import org.example.vtschool_mps_2526.models.entities.CourseEntity;
import org.example.vtschool_mps_2526.service.serviceCourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courses")
class courseController {

    @Autowired
    private serviceCourse serviceCourse;

    @Autowired
    private CourseDAO courseDAO;

    @GetMapping("/")
    public ResponseEntity<?> getCourses() {
        return ResponseEntity.ok(serviceCourse.getCourses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCourseById(@Validated @PathVariable Integer id) {

        CourseDTO course = serviceCourse.getCourseById(id);

        if(course == null)
        {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(course);
    }

    @PostMapping("/add")
    public ResponseEntity<?> createCourse(@Validated @RequestBody CourseDTO course) {
        CourseEntity courseEntity = serviceCourse.saveCourse(course);

        if (courseEntity != null) {
            return ResponseEntity.badRequest().build();
        }
        serviceCourse.saveCourse(course);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateCourse(@Validated @RequestBody CourseDTO course) {

        CourseEntity courseEntity = serviceCourse.updateCourse(course);
        if (courseEntity != null) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCourse(@Validated @PathVariable int id) {
        if (serviceCourse.deleteCourseById(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
