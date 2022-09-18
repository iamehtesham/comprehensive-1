package com.mindtree.hospital_management.service;

import com.mindtree.hospital_management.model.Doctor;
import com.mindtree.hospital_management.model.Patient;
import com.mindtree.hospital_management.repository.DoctorRepository;
import com.mindtree.hospital_management.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class DoctorService {
    private final DoctorRepository doctorRepository;
    private PatientService patientService;


    @Autowired
    public DoctorService(DoctorRepository doctorRepository, PatientService patientService){
        this.doctorRepository = doctorRepository;
        this.patientService = patientService;
    }

    public Doctor addDoctor(Doctor doctor){
        return doctorRepository.save(doctor);
    }
    public List<Doctor> getDoctors(){
        return StreamSupport.stream(doctorRepository.findAll().spliterator(),false).collect(Collectors.toList());
    }
    public Doctor getDoctor(Long id){
        return doctorRepository.findById(id).orElseThrow(()->new RuntimeException());
    }
    public  Doctor deleteDoctor(Long id){
        Doctor doctor = getDoctor(id);
        doctorRepository.delete(doctor);
        return doctor;
    }
    @Transactional
    public Doctor addPatientToDoctor(Long doctorId,Long patientId){

        Doctor doctor = getDoctor(doctorId);
        Patient patient = patientService.getPatient(patientId);


        doctor.addPatient(patient);
        patient.setDoctor(doctor);
        return doctor;
    }

}
