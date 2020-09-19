package com.patients.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name ="patients")
public class Patients {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(name ="patient_id")
//    private String patientId;
    @Column (name ="first_name")
    private String firstName;
    @Column(name ="last_name")
    private String lastName;
    @Column(name ="city")
    private String city;
    @Column(name = "state")
    private String state;
    @Column(name ="zip_code")
    private String zipCode;
    @Column(name =  "phone_number")
    private String phoneNumber;
    @Column(name ="email")
    private String email;
}
