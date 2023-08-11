package com.legalmatch.technicalexam.repository;

import com.legalmatch.technicalexam.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {}
