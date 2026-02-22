package org.example.vtschool_mps_2526.service;

import org.example.vtschool_mps_2526.models.dao.StudentsDAO;
import org.example.vtschool_mps_2526.models.dto.UserDTO;
import org.example.vtschool_mps_2526.models.entities.StudentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;


@Service
public class serviceLogin {
    @Autowired
    private StudentsDAO studentsDAO;

    public boolean loginUser(@Validated @RequestBody UserDTO userDTO) {

        Optional< StudentEntity> optional = studentsDAO.findById(Integer.valueOf(userDTO.getPassword()));

        return optional.isPresent();
    }
}
