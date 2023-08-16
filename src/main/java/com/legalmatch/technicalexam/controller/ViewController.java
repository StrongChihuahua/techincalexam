package com.legalmatch.technicalexam.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.Map;


@RestController
public class ViewController {


    @GetMapping("/")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public ModelAndView index (Map<String, Object> model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated() && authentication.getPrincipal() instanceof UserDetails userDetails) {

            String[] rolesArray = userDetails.getAuthorities().stream()
                    .map(Object::toString)
                    .toArray(String[]::new);

            boolean isAdmin = Arrays.asList(rolesArray).contains("ROLE_ADMIN");
            model.put("is_admin", isAdmin);

        } else {
            model.put("is_admin", false);
        }
        return new ModelAndView("index", model);
    }
}
