package com.samuel.patientapp;

import com.samuel.patientapp.controller.PatientController;
import com.samuel.patientapp.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(PatientController.class)
public class PatientAppControllerTests {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    PatientService patientService;



}
