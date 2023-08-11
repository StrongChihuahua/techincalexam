package com.legalmatch.technicalexam.model;

import jakarta.persistence.*;

@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "address_1", nullable = false)
    private String addressOne;
    @Column(name = "address_2", nullable = false)
    private String addressTwo;

    @Column(name = "is_primary", nullable = false)
    private boolean isPrimary;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="employee_id")
    private Employee employee;



    public Address() {}

    public Address(String addressOne,  String addressTwo, boolean isPrimary) {
        super();
        this.addressOne = addressOne;
        this.addressTwo = addressTwo;
        this.isPrimary = isPrimary;
    }


    public void setAddressOne(String addressOne) {
        this.addressOne = addressOne;
    }

    public void setAddressTwo(String addressTwo) {
        this.addressTwo = addressTwo;
    }

    public void setIsPrimary(boolean isPrimary) {
        this.isPrimary = isPrimary;
    }

    public String getAddressOne() {
        return addressOne;
    }

    public String getAddressTwo() {
        return addressTwo;
    }

    public boolean getIsPrimary() {
        return isPrimary;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
