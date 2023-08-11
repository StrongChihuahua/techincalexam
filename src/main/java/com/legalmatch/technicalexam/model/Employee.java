package com.legalmatch.technicalexam.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="employees")
public class Employee {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "first_name", nullable = false)
        private String firstName;
        @Column(name = "middle_name", nullable = false)
        private String middleName;
        @Column(name = "last_name", nullable = false)
        private String lastName;

        @JsonFormat(pattern = "yyyy-MM-dd")
        @Column(name = "birth_date", nullable = false)
        private LocalDate birthdate;
        @Column(name = "gender", nullable = false)
        @Enumerated(EnumType.STRING)
        private GenderEnum gender;
        @Column(name = "marital_status", nullable = false)
        @Enumerated(EnumType.STRING)
        private MaritalStatusEnum maritalStatus;
        @Column(name = "position", nullable = false)
        private String position;

        @JsonFormat(pattern = "yyyy-MM-dd")
        @Column(name = "date_hired", nullable = false)
        private LocalDate dateHired;

        @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        @JoinColumn(name = "employee_id", referencedColumnName = "id")
        private List<Contact> contacts = new ArrayList<>();

        @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        @JoinColumn(name = "employee_id", referencedColumnName = "id")
        private List<Address> addresses = new ArrayList<>();

        public Employee() {
        }

        public Employee(String firstName, String middleName, String lastName, LocalDate birthdate, GenderEnum gender, MaritalStatusEnum maritalStatus, String position, LocalDate dateHired, List<Contact> contacts, List<Address> addresses) {
                super();
                this.firstName = firstName;
                this.middleName = middleName;
                this.lastName = lastName;
                this.birthdate = birthdate;
                this.gender = gender;
                this.maritalStatus = maritalStatus;
                this.position = position;
                this.dateHired = dateHired;
                this.contacts = contacts;
                this.addresses = addresses;
        }

        public void setContacts(List<Contact> contacts) {
                this.contacts = contacts;
        }

        public List<Contact> getContacts() {
                return contacts;
        }

        public void setAddresses(List<Address> addresses) {
                this.addresses = addresses;
        }

        public List<Address> getAddresses() {
                return addresses;
        }

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public void setFirstName(String firstName) {
                this.firstName = firstName;
        }

        public String getFirstName() {
                return firstName;
        }

        public void setMaritalStatus(MaritalStatusEnum maritalStatus) {
                this.maritalStatus = maritalStatus;
        }

        public MaritalStatusEnum getMaritalStatus() {
                return maritalStatus;
        }

        public void setLastName(String lastName) {
                this.lastName = lastName;
        }

        public String getLastName() {
                return lastName;
        }

        public void setBirthdate(LocalDate birthdate) {
                this.birthdate = birthdate;
        }

        public LocalDate getBirthdate() {
                return birthdate;
        }
        public void setGender(GenderEnum gender) {
                this.gender = gender;
        }

        public GenderEnum getGender() {
                return gender;
        }

        public void setDateHired(LocalDate dateHired) {
                this.dateHired = dateHired;
        }

        public LocalDate getDateHired() {
                return dateHired;
        }

        public void setMiddleName(String middleName) {
                this.middleName = middleName;
        }

        public String getMiddleName() {
                return middleName;
        }

        public void setPosition(String position) {
                this.position = position;
        }

        public String getPosition() {
                return position;
        }

//        @Transient
//        public String customGetterFname() {
//                return firstName;
//        }
}
