package com.samuel.patientapp.service;

import com.samuel.patientapp.dto.PatientDTO;
import com.samuel.patientapp.model.Patient;
import com.samuel.patientapp.repository.PatientRepository;
import com.samuel.patientapp.util.MyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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

    public Patient updateExistingPatient(Long pid, PatientDTO patientToUpdate) {
        Patient foundPatient = getPatientByPID(pid);
        if (foundPatient != null) {
            MyMapper.reflectionMapperDtoToEntity(patientToUpdate, foundPatient);
            return patientRepository.save(foundPatient);
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
