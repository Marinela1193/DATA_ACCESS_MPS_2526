package org.example.vtschool_mps_2526.models.dto;

public class CourseDTO {
    private Integer id;
    private String name;

    public CourseDTO() {
    }

    public CourseDTO(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
