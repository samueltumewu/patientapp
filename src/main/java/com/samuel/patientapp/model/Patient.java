package com.samuel.patientapp.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Table(name = "patient")
@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pid")
    private Long pid;

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "birth_date")
    private Date birthDate;
    @Column(name = "gender")
    private String gender;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "address")
    private String address;
    @Column(name = "suburb")
    private String suburb;
    @Column(name = "state")
    private String state;
    @Column(name = "post_code")
    private String postcode;
}