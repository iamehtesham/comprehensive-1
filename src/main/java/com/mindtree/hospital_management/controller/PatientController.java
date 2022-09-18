package com.mindtree.hospital_management.controller;

import com.mindtree.hospital_management.model.Doctor;
import com.mindtree.hospital_management.model.Patient;
import com.mindtree.hospital_management.model.dto.PatientDto;
import com.mindtree.hospital_management.repository.PatientRepository;
import com.mindtree.hospital_management.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/patients")
//@CrossOrigin(origins = "http://localhost:4200")
public class PatientController {
    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping
    public ResponseEntity<PatientDto> addPatient(@RequestBody final PatientDto patientDto){
        Patient patient = patientService.addPatient(Patient.from(patientDto));
        return new ResponseEntity<>(PatientDto.from(patient), HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<PatientDto>> getPatients(){
        List<Patient> patients = patientService.getPatients();
        List<PatientDto> patientsDto = patients.stream().map(PatientDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(patientsDto,HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<PatientDto> getPatient(@PathVariable final Long id){
        Patient patient = patientService.getPatient(id);
        return new ResponseEntity<>(PatientDto.from(patient),HttpStatus.OK);
    }

}
