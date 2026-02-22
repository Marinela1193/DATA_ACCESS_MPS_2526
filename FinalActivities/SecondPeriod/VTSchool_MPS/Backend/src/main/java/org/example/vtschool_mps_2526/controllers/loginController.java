package org.example.vtschool_mps_2526.controllers;

import org.example.vtschool_mps_2526.models.dto.UserDTO;
import org.example.vtschool_mps_2526.service.serviceLogin;
import org.example.vtschool_mps_2526.service.serviceStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/login")
class loginController {

    @Autowired
    private serviceLogin serviceLogin;
    @Autowired
    private serviceStudent serviceStudent;

    @PostMapping("/access")
    public ResponseEntity<?> loginUser(@Validated @RequestBody UserDTO userDTO) {
        if(serviceLogin.loginUser(userDTO)) {
            return ResponseEntity.ok().body("Access granted");
        }
        return ResponseEntity.badRequest().body("Access denied, user does not exist");
    }

}
