package com.sobart.partstock.repository;

import com.sobart.partstock.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String userName);

    User findById(long id);

    User findByActivationCode(String code);
}
