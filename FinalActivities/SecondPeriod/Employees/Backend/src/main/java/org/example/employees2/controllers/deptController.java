package org.example.employees2.controllers;

import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.example.employees2.models.dao.DeptEntityDAO;
import org.example.employees2.models.dto.DeptDTO;
import org.example.employees2.models.entities.DeptEntity;
import org.example.employees2.service.serviceDepto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/")
class deptController {
    @Autowired
    serviceDepto serviceDepto;
    @Autowired
    private DeptEntityDAO deptDAO;

    @GetMapping("/dto/")
    public ResponseEntity <List<?>> findAllDept() {
        return ResponseEntity.ok(serviceDepto.findAll());
    }

    @GetMapping("/dto/{id}")
    public ResponseEntity <?> findDeptById(@Validated @PathVariable(value = "id") Integer id) {
        DeptEntity depto = serviceDepto.findDeptoById(id);

        return ResponseEntity.ok().body(depto);
    }

    @PostMapping("/dto/")
    public ResponseEntity <?> saveDept(@Valid @RequestBody DeptDTO deptDTO) {
        Optional<DeptEntity> optional =  deptDAO.findById(deptDTO.getDeptno());

        if (optional.isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        DeptEntity deptEntity = new DeptEntity();
        deptEntity.setId(deptDTO.getDeptno());
        deptEntity.setDname(deptDTO.getEname());
        deptEntity.setLoc(deptDTO.getDloc());

        serviceDepto.saveDept(deptEntity);
        return ResponseEntity.ok().body(deptEntity);
    }

    @PutMapping("/dto/{id}")
    public ResponseEntity<?> updateDept(@Validated @RequestBody DeptDTO deptDTO, @PathVariable(value = "id") Integer id){
        Optional<DeptEntity> optional =  deptDAO.findById(deptDTO.getDeptno());
        if (optional.isPresent()) {
            DeptEntity deptEntity = new DeptEntity();
            deptEntity.setId(deptDTO.getDeptno());
            deptEntity.setDname(deptDTO.getEname());
            deptEntity.setLoc(deptDTO.getDloc());

            serviceDepto.updateDept(deptEntity);
            return ResponseEntity.ok().body(deptEntity);
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/dto/{id}")
    public ResponseEntity<?> deleteDept(@Validated @PathVariable(value = "id") Integer id){
        Optional<DeptEntity> optional =  deptDAO.findById(id);
        if(optional.isPresent()) {
            serviceDepto.deleteDept(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }



}
