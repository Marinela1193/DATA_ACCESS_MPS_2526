package org.example.employees2.controllers;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.example.employees2.models.dao.DeptEntityDAO;
import org.example.employees2.models.dao.EmployeeEntityDAO;
import org.example.employees2.models.dto.DeptDTO;
import org.example.employees2.models.dto.EmployeeDTO;
import org.example.employees2.models.entities.DeptEntity;
import org.example.employees2.models.entities.EmployeeEntity;
import org.example.employees2.service.serviceEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class employeesController {
    @Autowired
    serviceEmployee serviceEmployee;
    @Autowired
    private EmployeeEntityDAO employeeEntityDAO;

    @Autowired
    private DeptEntityDAO deptEntityDAO;


    @GetMapping("/")
    public ResponseEntity <List<?>> findAllUsers() {

        return ResponseEntity.ok(serviceEmployee.getEmployees());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findUserById(@Validated @PathVariable(value = "id") int id) {
        EmployeeEntity employee = serviceEmployee.getEmployeeById(id);

        return ResponseEntity.ok(employee);
    }

    @GetMapping("/dept/{deptNo}")
    public ResponseEntity<?> findUserByDeptNo(@PathVariable(value = "deptNo") int deptNo) {

        EmployeeEntity employee = serviceEmployee.getEmployeeByDeptNo(deptNo);

        return  ResponseEntity.ok(employee);
    }

    @PostMapping("/")
    public ResponseEntity<?> saveUser(@Validated @RequestBody EmployeeDTO employeeDTO) {

        Optional<EmployeeEntity> optional = employeeEntityDAO.findById(employeeDTO.getEmpno());

        if (optional.isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        Optional<DeptEntity> dept = deptEntityDAO.findById(employeeDTO.getDeptno());
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setId(employeeDTO.getEmpno());
        employeeEntity.setEname(employeeDTO.getEname());
        employeeEntity.setJob(employeeDTO.getJob());
        employeeEntity.setDeptno(dept.get());

        serviceEmployee.saveEmployee(employeeEntity);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@Validated @RequestBody EmployeeDTO employee,@NotNull @Min(0) @PathVariable(value = "id") int id) {

        Optional<EmployeeEntity> optional = employeeEntityDAO.findById(id);
        if (optional.isPresent()) {
            Optional<DeptEntity> dept = deptEntityDAO.findById(employee.getDeptno());

            EmployeeEntity employeeEntity = optional.get();
            employeeEntity.setEname(employee.getEname());
            employeeEntity.setId(employee.getEmpno());
            employeeEntity.setJob(employee.getJob());
            employeeEntity.setDeptno(dept.get());
            serviceEmployee.updateEmployee(employeeEntity);
            return ResponseEntity.ok().build();
        }
        return  ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@Validated @PathVariable (value = "id") int id) {
        Optional<EmployeeEntity> optional = employeeEntityDAO.findById(id);
        if (optional.isPresent()) {
            serviceEmployee.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return  ResponseEntity.badRequest().build();
    }

}

