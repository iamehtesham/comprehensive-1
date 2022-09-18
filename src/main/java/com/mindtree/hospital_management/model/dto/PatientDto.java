package com.mindtree.hospital_management.model.dto;

import com.mindtree.hospital_management.model.Doctor;
import com.mindtree.hospital_management.model.Patient;
import lombok.Data;

import java.util.Objects;

@Data
public class PatientDto {
    private Long id;

    private String name;
    private String dateOfVisit;
    private int age;
    private PlainDto plainDto;

    public static PatientDto from(Patient patient){
        PatientDto patientDto = new PatientDto();
        patientDto.setId(patient.getId());
        patientDto.setName(patient.getName());
        patientDto.setDateOfVisit(patient.getDateOfVisit());
        patientDto.setAge(patient.getAge());
        if(Objects.nonNull(patient.getDoctor())){
            patientDto.setPlainDto(PlainDto.from(patient.getDoctor()));
        }
        return  patientDto;
    }
}
