package com.samuel.patientapp.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatientDTO {
    private String firstName;

    private String lastName;

    @Past(message = "Must be in the past!")
    private LocalDate birthDate;

    private String gender;

    private String phoneNumber;

    private String address;

    private String suburb;

    private String state;

    private String postcode;
}
