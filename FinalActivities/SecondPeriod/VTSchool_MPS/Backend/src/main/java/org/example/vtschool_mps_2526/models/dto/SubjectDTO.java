package org.example.vtschool_mps_2526.models.dto;

public class SubjectDTO {
    private Integer id;
    private String name;
    private Integer year;
    private Integer hours;

    public SubjectDTO() {
    }

    public SubjectDTO(Integer id, String name, Integer year, Integer hours) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.hours = hours;
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

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }
}
