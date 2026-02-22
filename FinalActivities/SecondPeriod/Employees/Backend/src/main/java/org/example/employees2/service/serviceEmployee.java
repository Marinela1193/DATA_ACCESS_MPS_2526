package org.example.employees2.service;

import org.example.employees2.models.dao.EmployeeEntityDAO;
import org.example.employees2.models.entities.EmployeeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class serviceEmployee {
    @Autowired
    private EmployeeEntityDAO employeeEntityDAO;

    public List<EmployeeEntity> getEmployees() {
        return (List<EmployeeEntity>) employeeEntityDAO.findAll();
    }

    public EmployeeEntity getEmployeeById(int id) {
        Optional<EmployeeEntity> employee = employeeEntityDAO.findById(id);

        return employee.isPresent() ? employee.get() : null;
    }

    public EmployeeEntity saveEmployee(EmployeeEntity employeeEntity) {
        if(!employeeEntityDAO.existsById(employeeEntity.getId())){
            return employeeEntityDAO.save(employeeEntity);
        }
        return employeeEntity;
    }

    public void deleteById(int id) {
        if(employeeEntityDAO.existsById(id)){
            employeeEntityDAO.deleteById(id);
        }
    }

    public void updateEmployee(EmployeeEntity employeeEntity) {
        if(employeeEntityDAO.existsById(employeeEntity.getId())){
            EmployeeEntity employee = new EmployeeEntity();
            employee.setEname(employeeEntity.getEname());
            employee.setDeptno(employeeEntity.getDeptno());
            employee.setJob(employeeEntity.getJob());
            employee.setDeptno(employeeEntity.getDeptno());

            employeeEntityDAO.save(employee);
        }
    }


    public EmployeeEntity getEmployeeByDeptNo(int deptNo) {
        List<EmployeeEntity> employees = (List<EmployeeEntity>) employeeEntityDAO.findAll();

        for (EmployeeEntity employee : employees) {
            if (employee.getDeptno().equals(deptNo)) {
                EmployeeEntity emp = new EmployeeEntity();
                emp.setEname(employee.getEname());
                emp.setDeptno(employee.getDeptno());
                emp.setJob(employee.getJob());
                emp.setId(employee.getId());

                return emp;
            }
        }
        return null;
    }

}
