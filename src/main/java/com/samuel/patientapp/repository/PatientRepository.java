package com.samuel.patientapp.repository;

import com.samuel.patientapp.model.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository  extends JpaRepository<Patient, Long> {
    public Page<Patient> findByFirstNameIgnoreCaseContainingOrLastNameIgnoreCaseContaining(String firstname, String lastname,
                                                   Pageable pageable);
}
