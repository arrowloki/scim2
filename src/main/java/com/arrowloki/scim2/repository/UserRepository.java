package com.arrowloki.scim2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arrowloki.scim2.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
