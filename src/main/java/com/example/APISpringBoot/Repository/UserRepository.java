package com.example.APISpringBoot.Repository;

import com.example.APISpringBoot.Entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@EnableJpaRepositories
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
  Optional<UserEntity> findOneByEmailAndPassword(String email, String password);

  UserEntity findByEmail(String email);
}
