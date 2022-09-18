package com.mindtree.hospital_management.model.dto;

import com.mindtree.hospital_management.model.Doctor;
import lombok.Data;

@Data
public class PlainDto {
    private Long id;
    private String name;

    public static PlainDto from(Doctor doctor){
        PlainDto plainDto = new PlainDto();
        plainDto.setId(doctor.getId());
        plainDto.setName(doctor.getName());
        return plainDto;
    }
}
