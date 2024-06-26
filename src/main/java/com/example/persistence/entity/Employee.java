package com.example.persistence.entity;
// Generated Apr 6, 2024, 1:11:40 AM by Hibernate Tools 6.0.2.Final


import com.example.persistence.enums.Gender;
import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.IDENTITY;

import java.sql.Date;
import java.util.Set;

/**
 * Employee generated by hbm2java
 */
@Entity
@Table(name="employee"
    ,catalog="hrapi"
)
public class Employee  implements java.io.Serializable {


     private Integer employeeId;
     private Department department;
     private String firstName;
     private String lastName;
     private Date dateOfBirth;
     private Date HireDate;
     private Gender gender;
     private String email;
     private String jobTitle;
     private String phoneNumber;
     private Set<LeaveManagement> leaveManagements ;
     private Set<PerformanceReview> performanceReviews ;

     private Salary salary;

    public Employee() {
    }

    public Employee(Department department, String firstName, String lastName, Date dateOfBirth, Gender gender, String email, String phoneNumber,Set<LeaveManagement> leavemanagements,Set <PerformanceReview> performanceReviews,  Salary salary , String jobTitle){
       this.department = department;
       this.firstName = firstName;
       this.lastName = lastName;
       this.dateOfBirth = dateOfBirth;
       this.gender = gender;
       this.email = email;
       this.phoneNumber = phoneNumber;
       this.leaveManagements = leavemanagements;
       this.performanceReviews = performanceReviews;
       this.salary = salary;
       this.jobTitle = jobTitle;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="EmployeeID", unique=true, nullable=false)
    public Integer getEmployeeId() {
        return this.employeeId;
    }
    
    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    @ManyToOne(fetch=FetchType.EAGER , cascade = {CascadeType.MERGE , CascadeType.REFRESH})
    @JoinColumn(name="DepartmentID")
    public Department getDepartment() {
        return this.department;
    }
    
    public void setDepartment(Department department) {
        this.department = department;
    }

    
    @Column(name="FirstName", length=50)
    public String getFirstName() {
        return this.firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    
    @Column(name="LastName", length=50)
    public String getLastName() {
        return this.lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name="JobTitle" , length = 45)
    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="DateOfBirth", length=10)
    public Date getDateOfBirth() {
        return this.dateOfBirth;
    }
    
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Column(name="Gender")
    public String getGenderString() {
        return this.gender == null ? null : this.gender.name();
    }

    public void setGenderString(String gender) {
        this.gender = gender == null ? null : Gender.valueOf(gender);
    }

    @Transient
    public Gender getGender() {
        return this.gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
    

    
    @Column(name="Email", length=100)
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    
    @Column(name="PhoneNumber", length=20)
    public String getPhoneNumber() {
        return this.phoneNumber;
    }
    
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @OneToMany(fetch=FetchType.EAGER, mappedBy="employee" , cascade = {CascadeType.REMOVE , CascadeType.MERGE })
    public Set<LeaveManagement> getLeaveManagements() {
        return this.leaveManagements;
    }

    public void setLeaveManagements(Set<LeaveManagement> leavemanagements) {
        this.leaveManagements = leavemanagements;
    }

//    @OneToOne(fetch=FetchType.EAGER, mappedBy="employee" , cascade = {CascadeType.REMOVE , CascadeType.MERGE })

    @OneToMany(fetch=FetchType.EAGER, mappedBy="employee" , cascade = {CascadeType.REMOVE , CascadeType.MERGE })
    public Set<PerformanceReview> getPerformanceReviews() {
        return this.performanceReviews;
    }

    public void setPerformanceReviews(Set<PerformanceReview> performanceReviews) {
        this.performanceReviews = performanceReviews;
    }


    @OneToOne( mappedBy="employee" , cascade = {CascadeType.REMOVE ,CascadeType.MERGE})
    public Salary getSalary() {
        return this.salary;
    }
    
    public void setSalary(Salary salaries) {
        this.salary = salaries;
    }


    @Temporal(TemporalType.DATE)
    @Column(name="HireDate", length=10)
    public Date getHireDate() {
        return HireDate;
    }

    public void setHireDate(Date hireDate) {
        HireDate = hireDate;
    }

    public void copyIdToSalary(Salary salary) {
        if (salary != null && salary.getSalaryId() == null) {
            salary.setSalaryId(this.getEmployeeId());
        }
    }




    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", department=" + department.toString() +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", HireDate=" + HireDate +
                ", gender=" + gender +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", leaveManagements=" + leaveManagements +
                ", performanceReviews=" + performanceReviews +
                ", salary=" + salary+
                '}';
    }
}


