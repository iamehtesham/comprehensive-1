package com.mindtree.hospital_management.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mindtree.hospital_management.model.Doctor;
import com.mindtree.hospital_management.model.Patient;
import com.mindtree.hospital_management.model.dto.DoctorDto;
import com.mindtree.hospital_management.model.dto.PatientDto;
import com.mindtree.hospital_management.service.DoctorService;
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
import org.springframework.test.web.servlet.ResultMatcher;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
public class DoctorControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DoctorService doctorService;

    @MockBean
    private PatientService patientService;

    @Autowired
    private ObjectMapper objectMapper;

     //Junit test for creating doctor object
     @DisplayName("Junit test for creating doctor object")
     @Test
     public void givenDoctorObject_whenCreateDoctor_thenReturnSavedDoctor() throws Exception{
         // given - precondition or setup
         DoctorDto doctorDto = new DoctorDto();
         doctorDto.setName("Abir Roy");
         doctorDto.setGender("Male");
         doctorDto.setAge(30);
         doctorDto.setSpecialist_fields("Bone");
         //DoctorDto doctorDto = DoctorDto.from(Doctor.builder().name("Abir Roy").gender("Male").age(30).specialist_fields("Bone").build());
         given(doctorService.addDoctor(ArgumentMatchers.any(Doctor.class))).willAnswer((invocation)->invocation.getArgument(0));

         //when - action or the behaviour that we are going test
         ResultActions response = mockMvc.perform(post("/api/doctors").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(Doctor.from(doctorDto))));

         //then - verify the output
         response.andDo(print())
                 .andExpect(status().isCreated())
                 .andExpect(jsonPath("$.name",is(doctorDto.getName())))
                 .andExpect(jsonPath("$.gender",is(doctorDto.getGender())))
                 .andExpect(jsonPath("$.age",is(doctorDto.getAge())))
                 .andExpect(jsonPath("$.specialist_fields",is(doctorDto.getSpecialist_fields())));
     }

      //Junit test for Get All Doctors REST API
      @DisplayName("Junit test for Get All Doctors REST API")
      @Test
      public void givenListOfDoctors_whenGetAllDoctors_thenCheckSize() throws Exception{
          // given - precondition or setup

          DoctorDto doctorDto = new DoctorDto();
          doctorDto.setName("Abir Roy");
          doctorDto.setGender("Male");
          doctorDto.setAge(30);
          doctorDto.setSpecialist_fields("Bone");

          DoctorDto doctorDto1 = new DoctorDto();
          doctorDto1.setName("Abir Roy");
          doctorDto1.setGender("Male");
          doctorDto1.setAge(30);
          doctorDto1.setSpecialist_fields("Bone");

          List<Doctor> doctors = new ArrayList<>();
          doctors.add(Doctor.from(doctorDto));
          doctors.add(Doctor.from(doctorDto1));

          given(doctorService.getDoctors()).willReturn(doctors);

          //when - action or the behaviour that we are going test
          ResultActions response = mockMvc.perform(get("/api/doctors"));

          //then - verify the output
          response.andExpect(status().isOk())
                  .andDo(print())
                  .andExpect(jsonPath("$.size()",is(2)));
      }

       //Junit test for finding a doctor
       @DisplayName("Junit test for finding a doctor")
       @Test
       public void givenDoctorId_whenGetDoctorObject_thenReturnDoctorObject() throws Exception{
           // given - precondition or setup
           long doctorId = 1L;
           DoctorDto doctorDto = new DoctorDto();
           doctorDto.setId(1L);
           doctorDto.setName("Abir Roy");
           doctorDto.setGender("Male");
           doctorDto.setAge(30);
           doctorDto.setSpecialist_fields("Bone");
           Doctor doctor = Doctor.from(doctorDto);

           given(doctorService.getDoctor(doctorId)).willReturn(doctor);

           //when - action or the behaviour that we are going test
           ResultActions response = mockMvc.perform(get("/api/doctors/{id}",doctorId));

           //then - verify the output
           response.andExpect(status().isOk())
                   .andDo(print());
       }

//       //Junit test for adding a patient under a doctor
//       @Test
//       public void givenDoctorIdPatientId_whenAdding_thenReturnDoctorWithPatient() throws Exception{
//           // given - precondition or setup
//           long doctorId = 1L;
//           DoctorDto doctorDto = new DoctorDto();
//           doctorDto.setId(1L);
//           doctorDto.setName("Abir Roy");
//           doctorDto.setGender("Male");
//           doctorDto.setAge(30);
//           doctorDto.setSpecialist_fields("Bone");
//           Doctor doctor = Doctor.from(doctorDto);
//
//
//           long patientId = 2L;
//           PatientDto patientDto = new PatientDto();
//           patientDto.setId(2L);
//           patientDto.setName("Ayush Bag");
//           patientDto.setAge(24);
//           patientDto.setDateOfVisit("22-07-2021");
//           Patient patient = Patient.from(patientDto);
//
//
//           given(doctorService.getDoctor(doctorId)).willReturn(doctor);
//           //given(patientService.getPatient(patientId)).willReturn(patient);
//
//           //when - action or the behaviour that we are going test
//           ResultActions response = mockMvc.perform(post("/api/doctors/1/patients/2/add").contentType(MediaType.APPLICATION_JSON));
//
//           //then - verify the output
//           response.andExpect(status().isOk()).andDo(print());
//       }
}
