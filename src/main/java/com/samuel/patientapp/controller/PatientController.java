package com.samuel.patientapp.controller;

import com.samuel.patientapp.dto.ApiPaginatedResponse;
import com.samuel.patientapp.dto.ApiResponse;
import com.samuel.patientapp.dto.PaginatedDataDTO;
import com.samuel.patientapp.model.Patient;
import com.samuel.patientapp.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/patient")
public class PatientController {
    @Autowired
    PatientService patientService;


    @GetMapping
    public ResponseEntity<ApiResponse>
    getAllPatients(@RequestParam(required = false, name = "first_name") String firstName,
                   @RequestParam(required = false, name = "last_name") String lastName,
                   @RequestParam(defaultValue = "0", name = "page") int pageNumber,
                   @RequestParam(defaultValue = "5", name = "size") int pageSize)
            throws Exception
    {
        Page<Patient> pageResult = patientService.getAllPatients(firstName, lastName, pageNumber, pageSize);

        var paginatedData = new PaginatedDataDTO<Patient>(
                pageResult.getNumber(),
                pageResult.getTotalElements(),
                pageResult.getTotalPages(),
                pageResult.getContent()
        );

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiPaginatedResponse<Patient>(
                        "0000",
                        "success",
                        paginatedData
                ));
    }

    @GetMapping("/pid/{pid}")
    public ResponseEntity<ApiResponse>
    getByPid(@PathVariable long pid)
            throws Exception
    {
        Patient foundPatient = patientService.getPatientByPID(pid);
        return foundPatient != null ?
                ResponseEntity
                        .ok()
                        .body(new ApiResponse(
                                "0000",
                                "success",
                                foundPatient
                        )):
                ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ApiResponse> insertNewPatient(@RequestBody Patient newPatient) throws Exception {
        Patient patient = patientService.savePatient(newPatient);
        return ResponseEntity
                .ok()
                .body(new ApiResponse(
                        "0000",
                        "success",
                        patient
                ));
    }

    @PutMapping("/pid/{pid}")
    public ResponseEntity<ApiResponse> updateExistingPatient(@PathVariable("pid") long pid, @RequestBody Patient patient) throws Exception {
        Patient updatedPatient = patientService.updateExistingPatient(pid, patient);
        return updatedPatient != null ?
                ResponseEntity.ok().body(new ApiResponse(
                        "0000",
                        "success",
                        updatedPatient
                )) :
                ResponseEntity.notFound().build();
    }

    @DeleteMapping("/pid/{pid}")
    public ResponseEntity<ApiResponse> deletePatient(@PathVariable("pid") long pid) throws Exception{
        Boolean isDeleted = patientService.deletePatientByPid(pid);
        return isDeleted ?
                ResponseEntity.ok().body(new ApiResponse(
                        "0000",
                        "success",
                        null
                )) :
                ResponseEntity.notFound().build();
    }
}
