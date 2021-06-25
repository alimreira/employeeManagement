package com.feyi.employeemanagementsystem.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "salary")
public class Salary{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotEmpty
    @Column(name = "ratePerHour")
    int ratePerHour;

    @NotNull
    @NotEmpty
    @Column(name = "totalminutesperMonth")
    int totalminutesperMonth;

    @NotNull
    @NotEmpty
    @Column(name = "totalHoursPerMonth")
    int totalHoursPerMonth;

    @NotNull
    @NotEmpty
    @Column(name = "takehome")
    int takehome;

    @NotNull
    @NotEmpty
    @Column(name = "paid")
    boolean paid;

    @OneToOne
    public Employee employee;
}
