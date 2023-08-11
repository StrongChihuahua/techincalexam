package com.legalmatch.technicalexam.repository;
import com.legalmatch.technicalexam.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

//    @Query(value = "SELECT e.first_name as firstName, e.middle_name as middleName, e.last_name as lastName, e.birth_date as birthdate, e.date_hired as dateHired, " +
//            "a.*, " +
//            "c.* " +
//            "FROM Employees e " +
//            "LEFT JOIN Address a ON e.id = a.employee_id " +
//            "LEFT JOIN Contacts c ON e.id = c.employee_id", nativeQuery = true)
//    List<EmployeeCustomDTO> findAllTest();

}
