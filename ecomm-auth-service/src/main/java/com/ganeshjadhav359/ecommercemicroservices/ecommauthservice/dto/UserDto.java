package com.ganeshjadhav359.ecommercemicroservices.ecommauthservice.dto;

import com.ganeshjadhav359.ecommercemicroservices.ecommauthservice.validators.AlphaNumericChars;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

public class UserDto {

    @NotNull
    @NotEmpty(message ="Only alpha numeric characters")
    @AlphaNumericChars(message = "user name only contains alpha numeric values")
    private String userName;

    @NotNull
    @NotEmpty(message ="Only alpha numeric characters")
    private String password;
    private boolean enabled;
    private List<Integer> roleIds ;
}
