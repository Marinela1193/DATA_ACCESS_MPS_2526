package org.example.vtschool_mps_2526.models.dto;

import java.util.Set;

public class StudentsDTO {
    private int  idcard;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private Set<EnrollmentDTO> enrollments;

    public StudentsDTO() {
    }


    public StudentsDTO(int idcard, String firstName, String lastName, String phone, String email) {
        this.idcard = idcard;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
    }

    public int getIdcard() {
        return idcard;
    }

    public void setIdcard(int idcard) {
        this.idcard = idcard;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<EnrollmentDTO> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(Set<EnrollmentDTO> enrollments) {
        this.enrollments = enrollments;
    }

}
