package org.example.vtschool_mps_2526.controllers;

import org.example.vtschool_mps_2526.models.dao.EnrollmentDAO;
import org.example.vtschool_mps_2526.models.dao.StudentsDAO;
import org.example.vtschool_mps_2526.models.dto.StudentsDTO;
import org.example.vtschool_mps_2526.models.entities.StudentEntity;
import org.example.vtschool_mps_2526.service.serviceStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/students")
class studentsController {
    @Autowired
    serviceStudent serviceStudent;

    @Autowired
    private StudentsDAO studentsDAO;
    @Autowired
    private EnrollmentDAO enrollmentDAO;


    @GetMapping("/")
    public ResponseEntity<?> getStudents() {
        return ResponseEntity.ok(serviceStudent.getStudents());
    }

    @GetMapping("/{idcard}")
    public ResponseEntity<?> getStudent(@Validated @PathVariable("idcard") int idcard) {

        StudentsDTO student = serviceStudent.getStudentById(idcard);

        return student != null ? ResponseEntity.ok(student) : ResponseEntity.notFound().build();

    }

    @PostMapping("/add")
    public ResponseEntity<?> addStudent(@Validated @RequestBody StudentsDTO student) {

        StudentEntity optional = serviceStudent.saveStudent(student);
        if (optional != null) {
            return ResponseEntity.badRequest().build();
        }

        serviceStudent.saveStudent(student);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{idcard}")
    public ResponseEntity<?> updateStudent(@Validated @RequestBody StudentsDTO student, @PathVariable("idcard") int idcard) {

        StudentEntity stu = serviceStudent.updateStudent(student);
        if(stu != null) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();

    }

    @DeleteMapping("/{idcard}")
    public ResponseEntity<?> deleteStudent(@Validated @PathVariable("idcard") int idcard) {

        if(serviceStudent.deleteStudent(idcard)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
