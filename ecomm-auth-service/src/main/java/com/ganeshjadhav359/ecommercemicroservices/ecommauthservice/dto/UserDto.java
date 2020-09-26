package com.ganeshjadhav359.ecommercemicroservices.ecommauthservice.dto;

import com.ganeshjadhav359.ecommercemicroservices.ecommauthservice.validators.AlphaNumericChars;
import com.ganeshjadhav359.ecommercemicroservices.ecommauthservice.validators.ConfirmPassword;
import com.ganeshjadhav359.ecommercemicroservices.ecommauthservice.validators.ValidPassword;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@ConfirmPassword
public class UserDto {

    @NotNull
    @NotEmpty(message ="It should not be empty")
    @AlphaNumericChars(message = "user name only contains alpha numeric values")
    private String userName;

    @NotNull
    @NotEmpty(message ="It should not be empty")
    @ValidPassword
    private String password;

    @NotNull
    private String confirmPassword;

    private boolean enabled;
    
    private List<Integer> roleIds ;

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<Integer> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<Integer> roleIds) {
        this.roleIds = roleIds;
    }
}
