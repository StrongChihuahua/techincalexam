package com.legalmatch.technicalexam.controller;


import com.legalmatch.technicalexam.service.employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {


    private final EmployeeService employeeServiceervice;

    @Autowired
    public UserController(EmployeeService employeeServiceervice) {
        this.employeeServiceervice = employeeServiceervice;
    }







}
