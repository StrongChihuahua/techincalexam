package com.legalmatch.technicalexam.model.dto;

import com.legalmatch.technicalexam.model.Address;
import com.legalmatch.technicalexam.model.Contact;

public class EmployeeTableDTO {

    private Long id;
    private String name;
    private Integer age;
    private Address primaryAddress;

    private Contact primaryContact;
    private String yearsInTheCompany;

    public EmployeeTableDTO() {}

    public EmployeeTableDTO(Long id, String name, Integer age, Address primaryAddress, Contact primaryContact, String yearsInTheCompany) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.primaryAddress = primaryAddress;
        this.primaryContact = primaryContact;
        this.yearsInTheCompany = yearsInTheCompany;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Address getPrimaryAddress() {
        return primaryAddress;
    }

    public void setPrimaryAddress(Address primaryAddress) {
        this.primaryAddress = primaryAddress;
    }

    public Contact getPrimaryContact() {
        return primaryContact;
    }

    public void setPrimaryContact(Contact primaryContact) {
        this.primaryContact = primaryContact;
    }

    public String getYearsInTheCompany() {
        return yearsInTheCompany;
    }

    public void setYearsInTheCompany(String yearsInTheCompany) {
        this.yearsInTheCompany = yearsInTheCompany;
    }
}
