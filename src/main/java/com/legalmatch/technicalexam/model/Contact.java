package com.legalmatch.technicalexam.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name="contacts")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "value", nullable = false)
    private String value;


    @Column(name = "is_primary", nullable = false)
    private boolean isPrimary;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="employee_id")
    private Employee employee;

//    @ManyToOne()
//    @JoinColumn(name = "employee_id")
//    private Employee employee;
//    @Column(name = "primary", nullable = false)
//    private boolean primaryContact;


    public Contact() {}
    public Contact( String value, boolean isPrimary) {
        super();
        this.value = value;
        this.isPrimary = isPrimary;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setValue(String value) {
        this.value = value;
    }


    public String getValue() {
        return value;
    }

    public Long getId() {
        return id;
    }

    public void setIsPrimary(boolean isPrimary) {
        this.isPrimary = isPrimary;
    }

    public boolean getIsPrimary() {
        return isPrimary;
    }

    //    public Employee getEmployee() {
//        return employee;
//    }
}
