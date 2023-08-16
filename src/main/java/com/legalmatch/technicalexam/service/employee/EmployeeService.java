package com.legalmatch.technicalexam.service.employee;
import com.legalmatch.technicalexam.model.Employee;
import com.legalmatch.technicalexam.model.dto.EmployeeTableDTO;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface EmployeeService {

    Employee create(Employee e);

    List<Employee> findAll();

    Employee findById(Long id);

    Employee updateById(Employee e, Long id);

    String deleteById(Long id);

    EmployeeTableDTO employeeTable(Employee e);
}
