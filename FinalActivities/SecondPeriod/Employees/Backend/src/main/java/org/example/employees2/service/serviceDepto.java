package org.example.employees2.service;

import org.example.employees2.models.dao.DeptEntityDAO;
import org.example.employees2.models.entities.DeptEntity;
import org.example.employees2.models.entities.EmployeeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class serviceDepto {
    @Autowired
    private DeptEntityDAO  deptEntityDAO;


    public List<DeptEntity> findAll() {
        return (List<DeptEntity>) deptEntityDAO.findAll();
    }

    public DeptEntity findDeptoById(Integer id) {
        Optional<DeptEntity> depto = deptEntityDAO.findById(id);

        return depto.isPresent() ? deptEntityDAO.findById(id).get() : null;
    }

    public DeptEntity saveDept(DeptEntity deptEntity) {
        if(!deptEntityDAO.existsById(deptEntity.getId())) {
            return deptEntityDAO.save(deptEntity);
        }
        return deptEntity;
    }

    public void updateDept(DeptEntity deptEntity) {
        if(deptEntityDAO.existsById(deptEntity.getId())) {
            DeptEntity deptEntity1 = new DeptEntity();
            deptEntity1.setId(deptEntity.getId());
            deptEntity1.setDname(deptEntity.getDname());
            deptEntity1.setLoc(deptEntity.getLoc());

            deptEntityDAO.save(deptEntity1);
        }
    }

    public void deleteDept(Integer id) {
        if(deptEntityDAO.existsById(id)) {
            deptEntityDAO.deleteById(id);
        }
    }
}
