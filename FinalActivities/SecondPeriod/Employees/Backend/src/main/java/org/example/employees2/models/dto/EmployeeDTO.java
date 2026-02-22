package org.example.employees2.models.dto;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class EmployeeDTO {

    private int empno;
    private String ename;
    private String job;
    private int deptno;

    public EmployeeDTO(int empno, String ename, String job, int deptno) {
        this.empno = empno;
        this.ename = ename;
        this.job = job;
        this.deptno = deptno;
    }

    public EmployeeDTO() {}

    @Basic
    @NotEmpty(message = "The employee number cannot be blank")
    @Size(min = 1, message = "Name size must be minimum 1")
    @Column(name = "empno", nullable = false)
    public int getEmpno() {
        return empno;
    }

    public void setEmpno(int empno) {
        this.empno = empno;
    }
    @Basic
    @Size(min = 2,max = 10, message = "Name size must be between 2 and 10")
    @Column(name = "ename", nullable = true, length = 10)
    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    @Basic
    @Size(min = 2,max = 9, message = "Name size must be between 2 and 9")
    @Column(name = "job", nullable = true, length = 9)
    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
    @Basic
    @NotEmpty(message = "The department number cannot be blank")
    @Size(min = 1, message = "Name size must be minimum 1")
    @Column(name = "deptno", nullable = true)
    public int getDeptno() {
        return deptno;
    }

    public void setDeptno(int deptno) {
        this.deptno = deptno;
    }
}
