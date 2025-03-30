package com.example.APISpringBoot.Entities;

import jakarta.persistence.*;

@Entity
@Table(name="user")
public class UserEntity {
  @Id
  @Column(name = "id", length = 45)
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "name", length = 255)
  private String name;

  @Column(name = "email", length =  255)
  private String email;

  @Column(name = "password", length = 255)
  private String password;

  public UserEntity() {
  }

  public UserEntity(Long id, String name, String email, String password) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.password = password;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public String toString() {
    return "User{ "+
           "id=" + id +
           ", name='" + name + '\'' +
           ", email='" + email + '\'' +
           ", password='" + password + '\'' +
           '}';
  }
}
