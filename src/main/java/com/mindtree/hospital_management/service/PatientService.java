package com.mindtree.hospital_management.service;

import com.mindtree.hospital_management.model.Patient;
import com.mindtree.hospital_management.model.exception.PatientNotFoundException;
import com.mindtree.hospital_management.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PatientService {
    private final PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository){
        this.patientRepository = patientRepository;
    }

    public Patient addPatient(Patient patient){
        return patientRepository.save(patient);
    }
    public List<Patient> getPatients(){
        return StreamSupport.stream(patientRepository.findAll().spliterator(),false).collect(Collectors.toList());
    }
    public Patient getPatient(Long id){
        return patientRepository.findById(id).orElseThrow(()->new PatientNotFoundException(id));
    }
    public Patient deletePatient(Long id){
        Patient patient = getPatient(id);
        patientRepository.delete(patient);
        return patient;
    }


}
