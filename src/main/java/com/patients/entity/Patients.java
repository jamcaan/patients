package com.patients.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name ="patients")
public class Patients {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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
    private String xmlFile;
}
