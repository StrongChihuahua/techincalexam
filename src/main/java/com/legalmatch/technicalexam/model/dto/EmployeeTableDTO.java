package com.legalmatch.technicalexam.model.dto;

public class EmployeeTableDTO {

    private Long id;
    private String name;
    private Integer age;
    private String primaryAddress;

    private String primaryContact;
    private String yearsInTheCompany;

    public EmployeeTableDTO() {}

    public EmployeeTableDTO(Long id, String name, Integer age, String primaryAddress, String primaryContact, String yearsInTheCompany) {
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

    public String getPrimaryAddress() {
        return primaryAddress;
    }

    public void setPrimaryAddress(String primaryAddress) {
        this.primaryAddress = primaryAddress;
    }

    public String getPrimaryContact() {
        return primaryContact;
    }

    public void setPrimaryContact(String primaryContact) {
        this.primaryContact = primaryContact;
    }

    public String getYearsInTheCompany() {
        return yearsInTheCompany;
    }

    public void setYearsInTheCompany(String yearsInTheCompany) {
        this.yearsInTheCompany = yearsInTheCompany;
    }
}
