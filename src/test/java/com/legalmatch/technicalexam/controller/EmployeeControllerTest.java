package com.legalmatch.technicalexam.controller;

import com.legalmatch.technicalexam.model.*;
import com.legalmatch.technicalexam.model.dto.EmployeeTableDTO;
import com.legalmatch.technicalexam.service.employee.EmployeeService;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class EmployeeControllerTest {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeControllerTest(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @Test
    void Create_Employee() {

        Contact contact = new Contact();
        contact.setValue("test@gmail.com");
        contact.setIsPrimary(true);

        Address address = new Address();
        address.setAddressOne("Intramuros");
        address.setAddressTwo("Manila");
        address.setIsPrimary(true);

        List<Address> addressList = new ArrayList<>();
        List<Contact> contactList = new ArrayList<>();

        addressList.add(address);
        contactList.add(contact);


        Employee employee = generateNewEmployee(contactList, addressList);

        Employee savedEmployee = employeeService.create(employee);

        assertNotNull(savedEmployee);
        assertTrue(savedEmployee.getId() > 0);
    }



    @Test
    void Get_All_Employees() {
        List<Employee> employees = employeeService.findAll();


        assertFalse(employees.isEmpty());
        assertNotNull(employees);
    }
    @Test
    void Get_Single_Employee_By_ID() {
        // Input
        List<Employee> employees = employeeService.findAll();
        Employee employee = employees.get(0);

        Employee singleEmployee = employeeService.findById(employee.getId());


        assertNotNull(singleEmployee);
        assertTrue(singleEmployee.getId() > 0);
    }

    @Test
    void Get_Employee_Table() {
        List<Employee> employees = employeeService.findAll();

        List<EmployeeTableDTO> employeeDTOs = new ArrayList<>();

        for (Employee employee : employees) {
            employeeDTOs.add(employeeService.employeeTable(employee));
        }

        assertFalse(employeeDTOs.isEmpty());
        assertNotNull(employeeDTOs);
    }

    @Test
    void Update_Employee_By_ID() {
        // Input
        List<Employee> employees = employeeService.findAll();
        Employee oldEmployee = employees.get(0);

        Employee newUpdated = new Employee();

        newUpdated.setFirstName("Patrick");
        newUpdated.setMiddleName("Ball");
        newUpdated.setLastName("Evans");
        newUpdated.setGender(oldEmployee.getGender());
        newUpdated.setBirthdate(oldEmployee.getBirthdate());
        newUpdated.setPosition("Developer");
        newUpdated.setMaritalStatus(MaritalStatusEnum.WIDOWED);
        newUpdated.setDateHired(LocalDate.parse("2023-10-10"));
        newUpdated.setAddresses(oldEmployee.getAddresses());

        List<Contact> oldEmployeeContacts = oldEmployee.getContacts();
        
        Contact newContactToAdd = new Contact();
        newContactToAdd.setValue("evans@gmail.com");
        newContactToAdd.setIsPrimary(false);
        
        oldEmployeeContacts.add(newContactToAdd);

        // set updated contacts
        newUpdated.setContacts(oldEmployeeContacts);


        Employee updatedEmployee = employeeService.updateById(newUpdated, oldEmployee.getId());

        assertNotNull(updatedEmployee);
        assertSame(updatedEmployee.getId(), oldEmployee.getId());
    }

    @Test
    void Delete_Employee() {
        List<Employee> employees = employeeService.findAll();

        if(!employees.isEmpty()) {
            Employee oldEmployee = employees.get(0);



            String result = employeeService.deleteById(oldEmployee.getId());
            assertTrue(result.contains("Deleted"));
        }

        assertFalse(employees.isEmpty());
    }

    @NotNull
    private static Employee generateNewEmployee(List<Contact> contactList, List<Address> addressList) {
        Employee employee = new Employee();
        employee.setFirstName("Emily");
        employee.setMiddleName("Smith");
        employee.setLastName("Watson");
        employee.setGender(GenderEnum.FEMALE);
        employee.setMaritalStatus(MaritalStatusEnum.SINGLE);
        employee.setBirthdate(LocalDate.parse("1999-12-12"));
        employee.setDateHired(LocalDate.parse("2020-10-10"));
        employee.setPosition("UI/UX Designer");
        employee.setContacts(contactList);
        employee.setAddresses(addressList);
        return employee;
    }
}