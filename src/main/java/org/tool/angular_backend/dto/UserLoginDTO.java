package org.tool.angular_backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public class UserLoginDTO {
    @JsonProperty("phone_number")
    @NotBlank(message = "Phone number is required")
    private String phoneNumber;
    @NotBlank(message = "Password cannot be blank")
    @JsonProperty("password")
    private String password;

    public UserLoginDTO() {
    }

    public UserLoginDTO(String phoneNumber, String password) {
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
