package com.samuel.patientapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.samuel.patientapp.controller.PatientController;
import com.samuel.patientapp.dto.PatientDTO;
import com.samuel.patientapp.model.Patient;
import com.samuel.patientapp.service.PatientService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = PatientController.class)
@ExtendWith(MockitoExtension.class)
public class PatientAppControllerTests {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    PatientService patientService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    ObjectMapper objectMapper;

    static final String URI_UPDATE_PATIENT = "/api/patient/pid/{pid}";

    @Test
    void testUpdatePatient() throws Exception {
        //Given
        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setFirstName("john");
        patientDTO.setLastName(null);

        Patient expectedPatient = new Patient();
        expectedPatient.setPid(-9L);
        expectedPatient.setFirstName("john");
        expectedPatient.setLastName("doe");
        expectedPatient.setAddress("jakarta");

        when(patientService.updateExistingPatient(anyLong(), any(PatientDTO.class)))
                .thenReturn(expectedPatient);
        mockMvc.perform
                (MockMvcRequestBuilders
                .put(URI_UPDATE_PATIENT,-9L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(patientDTO))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.pid").value(-9L))
                .andExpect(jsonPath("$.data.firstName").value("john"))
                .andExpect(jsonPath("$.data.lastName").value("doe"))
                .andExpect(jsonPath("$.data.address").value("jakarta"));

        verify(patientService, times(1))
                .updateExistingPatient(anyLong(), any(PatientDTO.class));
    }

}
