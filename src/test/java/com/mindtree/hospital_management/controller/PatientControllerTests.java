package com.mindtree.hospital_management.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mindtree.hospital_management.model.Doctor;
import com.mindtree.hospital_management.model.Patient;
import com.mindtree.hospital_management.model.dto.PatientDto;
import com.mindtree.hospital_management.service.PatientService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
public class PatientControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PatientService patientService;

    @MockBean
    private DoctorController doctorController;

    @Autowired
    private ObjectMapper objectMapper;

     //Junit test for creating patient object
     @DisplayName("Junit test for creating patient object")
     @Test
     public void givenPatientObject_whenCreatePatient_thenReturnSavedPatient() throws Exception{
         // given - precondition or setup
         PatientDto patientDto = new PatientDto();

       patientDto.setId(2L);
       patientDto.setName("Ayush Bag");
       patientDto.setAge(24);
       patientDto.setDateOfVisit("22-07-2021");

           given(patientService.addPatient(ArgumentMatchers.any(Patient.class))).willAnswer((invocation)->invocation.getArgument(0));

         //when - action or the behaviour that we are going test
         ResultActions response = mockMvc.perform(post("/api/patients").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(Patient.from(patientDto))));

         //then - verify the output
         response.andDo(print())
                 .andExpect(status().isOk())
                 .andExpect(jsonPath("$.name",is(patientDto.getName())))
                 .andExpect(jsonPath("$.age",is(patientDto.getAge())))
                 .andExpect(jsonPath("$.dateOfVisit",is(patientDto.getDateOfVisit())));
     }

      //Junit test for finding all patients
      @DisplayName("Junit test for finding all patients")
      @Test
      public void givenListOfPatient_whenGetAllPatients_thenCheckSize() throws Exception{
          // given - precondition or setup
          PatientDto patientDto = new PatientDto();
          patientDto.setId(1L);
          patientDto.setName("Ayush Bag");
          patientDto.setAge(24);
          patientDto.setDateOfVisit("22-07-2021");

          PatientDto patientDto1 = new PatientDto();
          patientDto.setId(2L);
          patientDto.setName("Vivek Roy");
          patientDto.setAge(23);
          patientDto.setDateOfVisit("22-05-2021");

          List<Patient> patientList = new ArrayList<>();
          patientList.add(Patient.from(patientDto));
          patientList.add(Patient.from(patientDto1));

          given(patientService.getPatients()).willReturn(patientList);

          //when - action or the behaviour that we are going test
          ResultActions response = mockMvc.perform(get("/api/patients"));

          //then - verify the output
          response.andExpect(status().isOk())
                  .andDo(print())
                  .andExpect(jsonPath("$.size()",is(2)));
      }

       //Junit test for finding a patient
       @DisplayName("Junit test for finding a patient")
       @Test
       public void givenPatientId_whenGetPatientObject_thenReturnPatientObject() throws Exception {
           // given - precondition or setup
           long patientId = 1L;
           PatientDto patientDto = new PatientDto();
           patientDto.setId(1L);
           patientDto.setName("Ayush Bag");
           patientDto.setAge(24);
           patientDto.setDateOfVisit("22-07-2021");
           Patient patient = Patient.from(patientDto);

           given(patientService.getPatient(patientId)).willReturn(patient);

           //when - action or the behaviour that we are going test
           ResultActions response = mockMvc.perform(get("/api/patients/{id}",patientId));

           //then - verify the output
           response.andExpect(status().isOk())
                   .andDo(print());
       }
}
