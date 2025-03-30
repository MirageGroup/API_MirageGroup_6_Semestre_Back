package com.example.APISpringBoot.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.APISpringBoot.DTO.UserDTO;
import com.example.APISpringBoot.DTO.LoginDTO;
import com.example.APISpringBoot.Entities.UserEntity;
import com.example.APISpringBoot.Repository.UserRepository;
import com.example.APISpringBoot.payload.response.LoginMessage;

@Service

public class UserImplementation implements UserService {
  
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  public String addUser(UserDTO userDTO) {

    UserEntity user = new UserEntity(
      userDTO.getId(),
      userDTO.getName(),
      userDTO.getEmail(),
      this.passwordEncoder.encode(userDTO.getPassword())
    );

    userRepository.save(user);

    return user.getName();
  }

  UserDTO userDTO;

  @Override
  public LoginMessage loginUser(LoginDTO loginDTO) {
    // String msg = "";
    UserEntity user1 = userRepository.findByEmail(loginDTO.getEmail());

    if (user1 != null) {
      String password = loginDTO.getPassword();
      String encodedPassword = user1.getPassword();
      Boolean isPasswordCorrect = this.passwordEncoder.matches(password, encodedPassword);
      if (isPasswordCorrect) {
        Optional<UserEntity> user = 
        userRepository.findOneByEmailAndPassword(loginDTO.getEmail(), encodedPassword);

        if (user.isPresent()) {
          return new LoginMessage("Logado com sucesso!", true);
        } else {
          return new LoginMessage("Login Falhou!", false);
        }
      } else {
        return new LoginMessage("senha invalidos!", false);
      }
    } else {
      return new LoginMessage("Email não existe!", false);
    }
  }
}
