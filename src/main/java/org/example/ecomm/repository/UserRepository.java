package org.example.ecomm.repository;

import org.example.ecomm.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByUsernameContaining(String username);

}
