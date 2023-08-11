package com.legalmatch.technicalexam.service.employee;


import com.legalmatch.technicalexam.model.Contact;
import com.legalmatch.technicalexam.model.Employee;
import com.legalmatch.technicalexam.model.dto.EmployeeTableDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.legalmatch.technicalexam.repository.EmployeeRepository;

import java.util.List;
import java.util.Locale;


@Service
public interface EmployeeService {

    Employee create(Employee e);

    List<Employee> findAll();

    Employee findById(Long id);

    Employee updateById(Employee e, Long id);

    String deleteById(Long id);

    EmployeeTableDTO employeeTable(Employee e);
}
