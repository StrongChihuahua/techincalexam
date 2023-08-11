package com.legalmatch.technicalexam.controller;

import com.legalmatch.technicalexam.model.Employee;
import com.legalmatch.technicalexam.model.dto.EmployeeTableDTO;
import com.legalmatch.technicalexam.service.employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class ViewController {

    private final EmployeeService employeeService;

    @Autowired
    public ViewController(EmployeeService employeeService) {
        this.employeeService = employeeService;


    }

    @GetMapping("/employee")
    public ModelAndView employee (Map<String, Object> model) {
        List<Employee> employees = employeeService.findAll();

        List<EmployeeTableDTO> employeeDTOs = new ArrayList<>();

        for (Employee employee : employees) {
            employeeDTOs.add(employeeService.employeeTable(employee));
        }
        model.put("employees", employeeDTOs);
        return new ModelAndView("employee", model);
    }

    @GetMapping("/create-employee")
    public ModelAndView createEmployee (Map<String, Object> model) {
//        List<Employee> employees = service.findAll();
//
//        List<EmployeeTableDTO> employeeDTOs = new ArrayList<>();
//
//        for (Employee employee : employees) {
//            employeeDTOs.add(service.employeeTable(employee));
//        }
//        model.put("id", id);
        Employee employee = new Employee();
        model.put("employee", employee);
        return new ModelAndView("edit-employee", model);
    }

    @GetMapping("/edit-employee/{id}")
    public ModelAndView mutateEmployee (Map<String, Object> model, @PathVariable Long id) {
//        List<Employee> employees = service.findAll();
//
//        List<EmployeeTableDTO> employeeDTOs = new ArrayList<>();
//
//        for (Employee employee : employees) {
//            employeeDTOs.add(service.employeeTable(employee));
//        }

        Employee employee = employeeService.findById(id);
        System.out.println(employee.getGender());
        model.put("employee", employee);
//        model.put("maritalStatusDivorced", "f");
//        model.put("maritalStatusMarried ", "f");
//        model.put("maritalStatusSingle ", "f");
//        model.put("maritalStatusWidowed ", "f");
        return new ModelAndView("edit-employee", model);
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee (@PathVariable Long id) {
        employeeService.deleteById(id);

        return "redirect:/employee";
    }



}
