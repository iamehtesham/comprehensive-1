package com.mindtree.hospital_management.model;

import com.mindtree.hospital_management.model.dto.DoctorDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "Doctor")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer age;
    private String gender;
    private String specialist_fields;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "doctor_id")
    private List<Patient> patients = new ArrayList<>();

    public void addPatient(Patient patient){
        patients.add(patient);
    }
    public void removePatient(Patient patient){
        patients.remove(patient);
    }
    public static Doctor from(DoctorDto doctorDto){
        Doctor doctor = new Doctor();
        doctor.setId(doctorDto.getId());
        doctor.setName(doctorDto.getName());
        doctor.setAge(doctorDto.getAge());
        doctor.setGender(doctorDto.getGender());
        doctor.setSpecialist_fields(doctorDto.getSpecialist_fields());
        return doctor;
    }
}
