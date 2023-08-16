package com.legalmatch.technicalexam.service.user;

import com.legalmatch.technicalexam.model.User;
import com.legalmatch.technicalexam.repository.UserRepository;
import com.legalmatch.technicalexam.service.employee.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserDetailsSecurityServiceTest {
    private final UserRepository userRepository;

    @Autowired
    public UserDetailsSecurityServiceTest(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Test
    void loadUserByUsername() {
        Optional<User> user = userRepository.findByUsername("user");

        assertTrue(user.isPresent());
    }
}