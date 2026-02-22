package org.example.vtschool_mps_2526.models.entities;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "students", schema = "_da_vtschool_2526")
public class StudentEntity {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcard", nullable = false, length = 8)
    private String idcard;

    @Column(name = "firstname", nullable = false, length = 50)
    private String firstname;

    @Column(name = "lastname", nullable = false, length = 100)
    private String lastname;

    @Column(name = "phone", length = 12)
    private String phone;

    @Column(name = "email", length = 100)
    private String email;

    @OneToMany(mappedBy = "studentEntity")
    private Set<EnrollmentEntity> enrollmentEntities = new LinkedHashSet<>();

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
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

    public Set<EnrollmentEntity> getEnrollments() {
        return enrollmentEntities;
    }

    public void setEnrollments(Set<EnrollmentEntity> enrollmentEntities) {
        this.enrollmentEntities = enrollmentEntities;
    }

}