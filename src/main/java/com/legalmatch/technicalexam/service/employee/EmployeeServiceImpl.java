package com.legalmatch.technicalexam.service.employee;

import com.legalmatch.technicalexam.model.Address;
import com.legalmatch.technicalexam.model.Contact;
import com.legalmatch.technicalexam.model.Employee;
import com.legalmatch.technicalexam.model.dto.EmployeeTableDTO;
import com.legalmatch.technicalexam.repository.EmployeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import java.time.LocalDate;
import java.time.Period;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    @Override
    public Employee create(Employee e) {
        return repository.save(e);
    }

    @Override
    public List<Employee> findAll() {
        return repository.findAll();
    }

    @Override
    public Employee findById(Long id) {

        Optional<Employee> employee = repository.findById(id);

        if(employee.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Content Not Found!");
        }

        return employee.get();
    }
    @Override
    public Employee updateById(Employee e, Long id){

        Optional<Employee> employee = repository.findById(id);

        if(employee.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Content Not Found!");
        }

        Employee existingEmployee = employee.get();

        BeanUtils.copyProperties(e, existingEmployee, "id");
        return repository.save(existingEmployee);
    }

    @Override
    public String deleteById(Long id) {
        Optional<Employee> employee = repository.findById(id);

        if(employee.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee Not Found!");
        }

        repository.deleteById(id);
        return "Deleted " + employee.get().getId();
    }

    @Override
    public EmployeeTableDTO employeeTable(Employee e) {

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

        if (primaryContact.isPresent()) {
            Contact primary = primaryContact.get();
            empDto.setPrimaryContact(primary.getValue());
        }

        Optional<Address> primaryAddress = e.getAddresses().stream()
                .filter(Address::getIsPrimary)
                .findFirst();

        if (primaryAddress.isPresent()) {
            Address primary = primaryAddress.get();
            empDto.setPrimaryAddress(primary.getAddressOne() + (primary.getAddressTwo() != null && !primary.getAddressTwo().isEmpty() ? ", " + primary.getAddressTwo() : ""));
        }

        return empDto;
    }

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
