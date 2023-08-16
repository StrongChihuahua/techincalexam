package com.legalmatch.technicalexam.service.employee;

import com.legalmatch.technicalexam.model.*;
import com.legalmatch.technicalexam.model.dto.EmployeeTableDTO;
import com.legalmatch.technicalexam.repository.EmployeeRepository;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class EmployeeServiceImplTest {

    private final EmployeeRepository employeeRepository;



    @Autowired
    public EmployeeServiceImplTest(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Test
    void Create_Employee_Method() {
        Contact contact = new Contact();
        contact.setValue("example@yahoo.com");
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

        Employee savedEmployee = employeeRepository.save(employee);

        assertNotNull(savedEmployee);
        assertTrue(savedEmployee.getId() > 0);

    }

    @Test
    void Find_All_Employees_Method() {
        List<Employee> employees = employeeRepository.findAll();

        assertFalse(employees.isEmpty());
        assertNotNull(employees);
    }

    @Test
    void Find_Employee_By_Id_Method() {
        // Input
        List<Employee> employees = employeeRepository.findAll();
        Employee employee = employees.get(0);

        Optional<Employee> singleEmployee = employeeRepository.findById(employee.getId());

        assertFalse(singleEmployee.isEmpty());
        assertNotNull(singleEmployee.get());
        assertTrue(singleEmployee.get().getId() > 0);
    }

    @Test
    void Update_Employee_By_Id_Method() {
        // Input
        List<Employee> employees = employeeRepository.findAll();
        Employee oldEmployee = employees.get(0);

        Optional<Employee> employee = employeeRepository.findById(oldEmployee.getId());

        Employee existingEmployee = employee.get();


        // New Properties
        Employee newUpdated = new Employee();
        newUpdated.setFirstName("Patty");
        newUpdated.setMiddleName("Smith");
        newUpdated.setLastName("Doe");
        newUpdated.setGender(oldEmployee.getGender());
        newUpdated.setBirthdate(oldEmployee.getBirthdate());
        newUpdated.setPosition("Graphic Designer");
        newUpdated.setMaritalStatus(MaritalStatusEnum.WIDOWED);
        newUpdated.setDateHired(LocalDate.parse("2023-10-10"));
        newUpdated.setAddresses(oldEmployee.getAddresses());

        List<Contact> oldEmployeeContacts = oldEmployee.getContacts();

        Contact newContactToAdd = new Contact();
        newContactToAdd.setValue("patty@yahoo.com");
        newContactToAdd.setIsPrimary(false);

        oldEmployeeContacts.add(newContactToAdd);

        // set updated contacts
        newUpdated.setContacts(oldEmployeeContacts);


        BeanUtils.copyProperties(newUpdated, existingEmployee, "id");
        System.out.println(newUpdated.getAddresses().get(0).getId());
        Employee updated = employeeRepository.save(existingEmployee);

        assertFalse(employee.isEmpty());
        assertTrue(updated.getId() > 0);
        assertTrue(employee.get().getId() > 0);
        assertNotNull(existingEmployee);
    }

    @Test
    void Delete_Employee_By_Id_Method() {

        List<Employee> employees = employeeRepository.findAll();

        if(!employees.isEmpty()) {
            Employee oldEmployee = employees.get(0);

            Optional<Employee> employee = employeeRepository.findById(oldEmployee.getId());

            employeeRepository.deleteById(oldEmployee.getId());

            Optional<Employee> deletedEmployee = employeeRepository.findById(oldEmployee.getId());

            assertTrue(deletedEmployee.isEmpty());
        }
        assertFalse(employees.isEmpty());
    }

    @Test
    void Get_Employee_Table_DTO_Method() {
        // Input
        List<Employee> employees = employeeRepository.findAll();
        Employee e = employees.get(0);


        EmployeeTableDTO empDto = new EmployeeTableDTO();

        Period periodBd = Period.between(e.getBirthdate(), LocalDate.now());
        Period periodYc = Period.between(e.getDateHired(), LocalDate.now());

        String yearsInTheCompany = getFormattedDateYc(periodYc);
        empDto.setId(e.getId());
        empDto.setName(e.getFirstName() + " " + e.getLastName());
        empDto.setAge(periodBd.getYears());
        empDto.setYearsInTheCompany(yearsInTheCompany);

        Optional<Contact> primaryContact = e.getContacts().stream()
                .filter(Contact::getIsPrimary)
                .findFirst();


        // check if has primaryContact
        assertTrue(primaryContact.isPresent());

        Contact primaryCon = primaryContact.get();
        empDto.setPrimaryContact(primaryCon.getValue());

        Optional<Address> primaryAddress = e.getAddresses().stream()
                .filter(Address::getIsPrimary)
                .findFirst();

        // check if has primaryAddress
        assertTrue(primaryAddress.isPresent());

        Address primaryAdd = primaryAddress.get();
        empDto.setPrimaryAddress(primaryAdd.getAddressOne() + (primaryAdd.getAddressTwo() != null && !primaryAdd.getAddressTwo().isEmpty() ? ", " + primaryAdd.getAddressTwo() : ""));

        assertFalse(empDto.getName().isBlank());
        assertTrue(empDto.getAge() >= 0);
        assertFalse(empDto.getPrimaryAddress().isBlank());
        assertFalse(empDto.getPrimaryContact().isBlank());
        assertFalse(empDto.getYearsInTheCompany().isBlank());
    }

    @Test
    void Year_Formatter_Method () {
        Period periodYc = Period.between(LocalDate.parse("2000-10-10"), LocalDate.now());

        int yearsYc = periodYc.getYears();
        int monthsYc = periodYc.getMonths();
        String yearsInTheCompany;

        if (yearsYc > 0 && monthsYc > 0) {
            yearsInTheCompany = String.format("%dy %dm", yearsYc, monthsYc);
        } else if (yearsYc > 0) {
            yearsInTheCompany = String.format("%dy", yearsYc);
        } else if (monthsYc > 0) {
            yearsInTheCompany = String.format("%dm", monthsYc);
        } else {
            yearsInTheCompany = "0m";
        }
        assertTrue(yearsInTheCompany.contains("m"));
    }

    @NotNull
    private static Employee generateNewEmployee(List<Contact> contactList, List<Address> addressList) {
        Employee employee = new Employee();
        employee.setFirstName("John");
        employee.setMiddleName("Smith");
        employee.setLastName("Doe");
        employee.setGender(GenderEnum.MALE);
        employee.setMaritalStatus(MaritalStatusEnum.SINGLE);
        employee.setBirthdate(LocalDate.parse("1999-12-12"));
        employee.setDateHired(LocalDate.parse("2020-10-10"));
        employee.setPosition("IT Staff");
        employee.setContacts(contactList);
        employee.setAddresses(addressList);
        return employee;
    }

    @NotNull
    private static String getFormattedDateYc(Period periodYc) {
        int yearsYc = periodYc.getYears();
        int monthsYc = periodYc.getMonths();
        String yearsInTheCompany;

        if (yearsYc > 0 && monthsYc > 0) {
            yearsInTheCompany = String.format("%dy %dm", yearsYc, monthsYc);
        } else if (yearsYc > 0) {
            yearsInTheCompany = String.format("%dy", yearsYc);
        } else if (monthsYc > 0) {
            yearsInTheCompany = String.format("%dm", monthsYc);
        } else {
            yearsInTheCompany = "0m";
        }
        return yearsInTheCompany;
    }

}