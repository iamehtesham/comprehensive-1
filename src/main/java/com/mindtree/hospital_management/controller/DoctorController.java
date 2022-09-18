package com.mindtree.hospital_management.controller;

import com.mindtree.hospital_management.model.Doctor;
import com.mindtree.hospital_management.model.dto.DoctorDto;
import com.mindtree.hospital_management.model.dto.PatientDto;
import com.mindtree.hospital_management.repository.DoctorRepository;
import com.mindtree.hospital_management.service.DoctorService;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/doctors")
//@CrossOrigin(origins = "http://localhost:4200")
public class DoctorController {

    private final DoctorService doctorService;
    @Autowired
    private DoctorController(DoctorService doctorService){
        this.doctorService = doctorService;
    }

    @PostMapping
    public ResponseEntity<DoctorDto> addDoctor(@RequestBody final DoctorDto doctorDto){
        Doctor doctor = doctorService.addDoctor(Doctor.from(doctorDto));
        return new ResponseEntity<>(DoctorDto.from(doctor),HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<DoctorDto>> getDoctors(){
        List<Doctor> doctors = doctorService.getDoctors();
        List<DoctorDto> doctorsDto = doctors.stream().map(DoctorDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(doctorsDto, HttpStatus.OK);
    }

    @PostMapping(value = "{doctorId}/patients/{patientId}/add")
    public ResponseEntity<DoctorDto> addPatientToDoctor(@PathVariable final Long doctorId, @PathVariable final Long patientId){
        Doctor doctor = doctorService.addPatientToDoctor(doctorId,patientId);
        return new ResponseEntity<>(DoctorDto.from(doctor),HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<DoctorDto> getDoctor(@PathVariable final Long id){
        Doctor doctor = doctorService.getDoctor(id);
        return new ResponseEntity<>(DoctorDto.from(doctor),HttpStatus.OK);
    }




}
