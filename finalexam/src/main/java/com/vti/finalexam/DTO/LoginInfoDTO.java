package com.vti.finalexam.DTO;

public class LoginInfoDTO {
    private int id;
    private String fullName;

    public int getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public LoginInfoDTO(int id, String fullName) {
        this.id = id;
        this.fullName = fullName;
    }
}
