package com.samuel.patientapp.service;

import com.samuel.patientapp.model.Patient;
import com.samuel.patientapp.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class PatientService {
    @Autowired
    PatientRepository patientRepository;

    public Page<Patient> getAllPatients(Pageable pageable) {
        return patientRepository.findAll(pageable);
    }

    public Page<Patient> getAllPatientsByFirstNameContainingOrLastNameContaining(String firstName, String lastName, Pageable pageable) {
        if (firstName == null) firstName = "all";
        if (lastName == null) lastName = "all";
        System.out.printf("%s and %s", firstName, lastName);
        return patientRepository.findByFirstNameContainingOrLastNameContaining(firstName,lastName,pageable);
    }

    public Optional<Patient> getAllPatientsById(long pid){
        return patientRepository.findById(pid);
    }


}
