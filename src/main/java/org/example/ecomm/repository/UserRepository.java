package org.example.ecomm.repository;

import org.example.ecomm.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface UserRepository extends JpaRepository<User, String> {
    List<User> findByUsernameContaining(String username);
    @Procedure(name = "AddCustomer")
    void AddCustomer(@Param("@username") String username,
                 @Param("@passhash") String passwordHash,
                 @Param("@fullname") String fullName,
                 @Param("@isbuyer") boolean isBuyer,
                 @Param("@isseller") boolean isSeller
    );

}
