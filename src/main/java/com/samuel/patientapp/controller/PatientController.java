package com.samuel.patientapp.controller;

import com.samuel.patientapp.model.Patient;
import com.samuel.patientapp.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/patient")
public class PatientController {
    @Autowired
    PatientService patientService;

    @GetMapping
    public ResponseEntity<Map<String, Object>>
    getAllPatients(@RequestParam(required = false, name = "first_name") String firstName,
                   @RequestParam(required = false, name = "last_name") String lastName,
                   @RequestParam(defaultValue = "0", name = "page") int pageNumber,
                   @RequestParam(defaultValue = "5", name = "size") int pageSize)
            throws Exception
    {
        Page<Patient> pageResult = patientService.getAllPatients(firstName, lastName, pageNumber, pageSize);

        Map<String, Object> respMap = new HashMap<>();
        respMap.put("content", pageResult.getContent());
        respMap.put("page", pageResult.getNumber());
        respMap.put("count", pageResult.getTotalElements());
        respMap.put("totalPage", pageResult.getTotalPages());

        return ResponseEntity.ok().body(respMap);
    }

    @GetMapping("/pid/{pid}")
    public ResponseEntity<Patient>
    getByPid(@PathVariable long pid)
            throws Exception
    {
        Patient foundPatient = patientService.getPatientByPID(pid);
        return foundPatient != null ?
                ResponseEntity.ok().body(foundPatient):
                ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Patient> insertNewPatient(@RequestBody Patient newPatient) throws Exception {
        Patient patient = patientService.savePatient(newPatient);
        return ResponseEntity.ok().body(patient);
    }

    @PutMapping("/pid/{pid}")
    public ResponseEntity<Patient> updateExistingPatient(@PathVariable("pid") long pid, @RequestBody Patient patient) throws Exception {
        Patient updatedPatient = patientService.updateExistingPatient(pid, patient);
        return updatedPatient != null ?
                ResponseEntity.ok().body(updatedPatient) :
                ResponseEntity.notFound().build();
    }

    @DeleteMapping("/pid/{pid}")
    public ResponseEntity<HttpStatus> deletePatient(@PathVariable("pid") long pid) throws Exception{
        Boolean isDeleted = patientService.deletePatientByPid(pid);
        return isDeleted ?
                ResponseEntity.ok().build() :
                ResponseEntity.notFound().build();
    }
}
