package com.example.APISpringBoot.Service;

import com.example.APISpringBoot.DTO.LoginDTO;
import com.example.APISpringBoot.DTO.UserDTO;
import com.example.APISpringBoot.payload.response.LoginMessage;

public interface UserService {
  String addUser(UserDTO userDTO);

  LoginMessage loginUser(LoginDTO loginDTO);
}
