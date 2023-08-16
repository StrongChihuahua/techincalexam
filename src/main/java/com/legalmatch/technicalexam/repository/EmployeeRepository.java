package com.legalmatch.technicalexam.repository;
import com.legalmatch.technicalexam.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
