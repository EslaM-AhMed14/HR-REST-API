package com.example.persistence.entity;
// Generated Apr 6, 2024, 1:11:40 AM by Hibernate Tools 6.0.2.Final


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import static jakarta.persistence.GenerationType.IDENTITY;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.HashSet;
import java.util.Set;

/**
 * Department generated by hbm2java
 */
@Entity
@Table(name="department"
    ,catalog="hrapi"
)
public class Department  implements java.io.Serializable {


     private Integer departmentId;
     private Employee employee;
     private String departmentName;
     private Boolean isHead;

     private Set<Job> jobs = new HashSet<>(0);
     private Set<Employee> employees = new HashSet<>(0);

    public Department() {
    }

    public Department(Employee employee, String departmentName, Boolean isHead, Set<Job> jobs, Set<Employee> employees) {
       this.employee = employee;
       this.departmentName = departmentName;
       this.isHead = isHead;
       this.jobs = jobs;
       this.employees = employees;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)
    @Column(name="DepartmentID", unique=true, nullable=false)
    public Integer getDepartmentId() {
        return this.departmentId;
    }
    
    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

@ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="ManagerID")
    public Employee getEmployee() {
        return this.employee;
    }
    
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    
    @Column(name="DepartmentName", length=100)
    public String getDepartmentName() {
        return this.departmentName;
    }
    
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    
    @Column(name="IsHead")
    public Boolean getIsHead() {
        return this.isHead;
    }
    
    public void setIsHead(Boolean isHead) {
        this.isHead = isHead;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="department")
    public Set<Job> getJobopenings() {
        return this.jobs;
    }
    
    public void setJobopenings(Set<Job> jobs) {
        this.jobs = jobs;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="department")
    public Set<Employee> getEmployees() {
        return this.employees;
    }
    
    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "Department{" +
                "departmentId=" + departmentId +
                ", departmentName='" + departmentName + '\'' +
                '}';
    }
}

