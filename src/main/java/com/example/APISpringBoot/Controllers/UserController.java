package com.example.APISpringBoot.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.APISpringBoot.DTO.LoginDTO;
import com.example.APISpringBoot.DTO.UserDTO;
import com.example.APISpringBoot.Service.UserService;
import com.example.APISpringBoot.payload.response.LoginMessage;

@RestController
@CrossOrigin
@RequestMapping("api/v1/user")
public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping(path = "/save")
  public String saveUser(@RequestBody UserDTO userDTO) {
    String id = userService.addUser(userDTO);
    return id;
  }

  @PostMapping(path = "/login")
  public ResponseEntity<?> loginUser(@RequestBody LoginDTO loginDTO) {
    LoginMessage loginMessage = userService.loginUser(loginDTO);
    return ResponseEntity.ok(loginMessage);
  }
}
