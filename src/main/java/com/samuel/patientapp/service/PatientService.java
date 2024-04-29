package com.samuel.patientapp.service;

import com.samuel.patientapp.model.Patient;
import com.samuel.patientapp.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class PatientService {
    @Autowired
    PatientRepository patientRepository;

    protected Page<Patient> getAllPatientsByFirstNameContainingOrLastNameContaining(String firstName, String lastName, Pageable pageable) {
        return patientRepository.findByFirstNameIgnoreCaseContainingOrLastNameIgnoreCaseContaining(firstName,lastName,pageable);
    }

    public Page<Patient> getAllPatients(String firstName, String lastName, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("pid"));
        return (firstName == null && lastName == null) ?
                patientRepository.findAll(pageable):
                getAllPatientsByFirstNameContainingOrLastNameContaining(firstName, lastName, pageable);
    }


    public Patient getPatientByPID(long pid){
        Optional<Patient> optionalPatient = patientRepository.findById(pid);
        return optionalPatient.orElse(null);
    }

    public Patient savePatient(Patient newPatient) {
        return patientRepository.save(newPatient);
    }

    public Patient updateExistingPatient(Long pid, Patient patientToUpdate) {
        Patient foundPatient = getPatientByPID(pid);
        if (foundPatient != null) {
            patientToUpdate.setPid(pid);
            patientRepository.save(patientToUpdate);
            return patientToUpdate;
        } else {
            return null;
        }
    }

    public Boolean deletePatientByPid(Long pid) {
        Patient foundPatient = getPatientByPID(pid);
        if (foundPatient != null) {
            patientRepository.deleteById(pid);
            return true;
        } else {
            return false;
        }
    }
}
