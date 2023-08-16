package com.legalmatch.technicalexam.service.user;

import com.legalmatch.technicalexam.model.User;
import com.legalmatch.technicalexam.model.dto.UserDetailsDTO;
import com.legalmatch.technicalexam.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class UserDetailsSecurityService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = repository.findByUsername(username);
        return user.map(UserDetailsDTO::new).orElseThrow(() -> new UsernameNotFoundException("User not found!"));
    }
}
