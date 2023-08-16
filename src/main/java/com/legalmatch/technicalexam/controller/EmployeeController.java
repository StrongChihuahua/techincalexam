package com.legalmatch.technicalexam.controller;


import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.legalmatch.technicalexam.model.*;
import com.legalmatch.technicalexam.model.dto.EmployeeTableDTO;
import com.legalmatch.technicalexam.service.employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;

import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class EmployeeController implements GraphQLQueryResolver, GraphQLMutationResolver {

    private final EmployeeService service;

    @Autowired
    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @QueryMapping
    public List<Employee> employees() {
       return service.findAll();
    }

    @QueryMapping
    public Employee employee(@Argument("id") Long id) {
        return service.findById(id);
    }

    @QueryMapping
    public List<EmployeeTableDTO> employeeTable() {
        List<Employee> employees = service.findAll();

        List<EmployeeTableDTO> employeeDTOs = new ArrayList<>();

        for (Employee employee : employees) {
            employeeDTOs.add(service.employeeTable(employee));
        }

        return employeeDTOs;
    }

    @MutationMapping
    public Employee employee(@Argument("request") Employee e) {
        return service.create(e);
    }

    @MutationMapping
    public Employee updateEmployee(@Argument("id") Long id, @Argument("request") Employee e) {
        return service.updateById(e, id);

    }

    @MutationMapping
    public String deleteEmployee(@Argument("id") Long id) {
        return service.deleteById(id);
    }

}
