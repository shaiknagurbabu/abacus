package com.abacus.portel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abacus.portel.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
