package org.example;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "dept")
public class Dept {
    @Id
    @Column(name = "deptno", nullable = false)
    private Integer id;

    @Column(name = "dname", length = 14)
    private String dname;

    @Column(name = "loc", length = 13)
    private String loc;

    @OneToMany(mappedBy = "deptno")
    private Set<Employee> employees = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

}