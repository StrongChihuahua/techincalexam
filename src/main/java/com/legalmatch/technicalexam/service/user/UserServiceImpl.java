package com.legalmatch.technicalexam.service.user;

import com.legalmatch.technicalexam.model.RoleEnum;
import com.legalmatch.technicalexam.model.User;
import com.legalmatch.technicalexam.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository repository;

    @PostConstruct
    private void init() {
//        TEST
//        System.out.println("HEYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYY!!!!");

//        List<User> users = repository.findAll();
//
//        if(users.isEmpty()) {
//            User admin = new User("admin", "admin", RoleEnum.ADMIN);
//            User user = new User("user", "user", RoleEnum.USER);
//
//            repository.save(admin);
//            repository.save(user);
//        }
//
//

    }
}
