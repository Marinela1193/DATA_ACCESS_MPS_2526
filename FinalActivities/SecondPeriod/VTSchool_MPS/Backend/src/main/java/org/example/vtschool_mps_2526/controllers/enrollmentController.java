package org.example.vtschool_mps_2526.controllers;

import org.example.vtschool_mps_2526.models.dao.EnrollmentDAO;
import org.example.vtschool_mps_2526.models.dto.EnrollmentDTO;
import org.example.vtschool_mps_2526.models.entities.EnrollmentEntity;
import org.example.vtschool_mps_2526.service.serviceCourse;
import org.example.vtschool_mps_2526.service.serviceEnrollment;
import org.example.vtschool_mps_2526.service.serviceScore;
import org.example.vtschool_mps_2526.service.serviceStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/enrollments")
class enrollmentController {

    @Autowired
    serviceEnrollment serviceEnrollment;

    @Autowired
    serviceStudent serviceStudent;
    @Autowired
    serviceScore serviceScore;
    @Autowired
    serviceCourse serviceCourse;
    @Autowired
    EnrollmentDAO enrollmentDAO;

    @GetMapping("/")
    public ResponseEntity<?> getEnrollments() {
        return ResponseEntity.ok(serviceEnrollment.getEnrollments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEnrollmentById(@Validated @PathVariable("id") int id) {
        EnrollmentDTO enrollmentDTO = serviceEnrollment.getEnrollmentById(id);
        return enrollmentDTO != null ? ResponseEntity.ok(enrollmentDTO) : ResponseEntity.notFound().build();
    }

    @PostMapping("/add")
    public ResponseEntity<?> addEnrollment(@Validated @RequestBody EnrollmentDTO enrollmentDTO) {

       EnrollmentEntity enrollmentEntity = serviceEnrollment.saveEnrollment(enrollmentDTO);
       if(enrollmentEntity != null) {
           return ResponseEntity.badRequest().build();
       }
       serviceEnrollment.saveEnrollment(enrollmentDTO);
       return ResponseEntity.ok().build();
    }

    @PutMapping("/update")
    public ResponseEntity<?>  updateEnrollment(@Validated @RequestBody EnrollmentDTO enrollmentDTO) {
        EnrollmentEntity enrollmentEntity = serviceEnrollment.updateEnrollment(enrollmentDTO);
        if(enrollmentEntity != null) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteEnrollment(@Validated @RequestBody int id) {

        if(serviceEnrollment.deleteEnrollmentById(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }



}
