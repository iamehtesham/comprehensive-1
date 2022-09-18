package com.mindtree.hospital_management.model.dto;

import com.mindtree.hospital_management.model.Doctor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class DoctorDto {
    private Long id;

    private String name;
    private Integer age;
    private String gender;
    private String specialist_fields;
    private List<PatientDto> patientDto = new ArrayList<>();

    public static DoctorDto from(Doctor doctor){
        DoctorDto doctorDto = new DoctorDto();
        doctorDto.setId(doctor.getId());
        doctorDto.setName(doctor.getName());
        doctorDto.setAge(doctor.getAge());
        doctorDto.setGender(doctor.getGender());
        doctorDto.setSpecialist_fields(doctor.getSpecialist_fields());
        doctorDto.setPatientDto(doctor.getPatients().stream().map(PatientDto::from).collect(Collectors.toList()));

        return doctorDto;
    }
}
