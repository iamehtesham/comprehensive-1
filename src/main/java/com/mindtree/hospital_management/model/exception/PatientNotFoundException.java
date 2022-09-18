package com.mindtree.hospital_management.model.exception;

import java.text.MessageFormat;

public class PatientNotFoundException extends RuntimeException{
    public PatientNotFoundException(final Long id){
        super(MessageFormat.format("Could not find patient with id:{0}",id));
    }
}
