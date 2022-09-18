package com.mindtree.hospital_management.model;

import com.mindtree.hospital_management.model.dto.PatientDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Patient")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String dateOfVisit;
    private int age;
    @ManyToOne
    private Doctor doctor;

    public static Patient from(PatientDto patientDto){
        Patient patient = new Patient();
        patient.setId(patientDto.getId());
        patient.setName(patientDto.getName());
        patient.setDateOfVisit(patientDto.getDateOfVisit());
        patient.setAge(patientDto.getAge());
        return patient;
    }

}
