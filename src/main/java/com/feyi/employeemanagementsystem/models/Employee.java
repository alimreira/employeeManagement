package com.feyi.employeemanagementsystem.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotEmpty
    @NotBlank
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotNull
    @NotEmpty
    @Column(name = "last_name")
    private String lastName;

    @NotEmpty
    @Column(unique = true)
    private String email;

    @NotNull
    @NotEmpty
    private String password;

    @NotNull
    @NotEmpty
    private String gender;

    @NotNull
    @NotEmpty
    private String address;

    @NotNull
    @NotEmpty
    private String department;

    private String role;

    @NotNull
    @NotEmpty
    private String phoneNumber;

    @OneToMany(mappedBy = "employee")
    List<Attendance> attendanceList = new ArrayList<>();

}
