package com.jinternal.employee.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jinternal.employee.enums.enums.Size;
import com.jinternal.employee.enums.enums.VehicleType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

import static javax.persistence.EnumType.STRING;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown=true)
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @Enumerated(STRING)
    private Gender gender;
    private String company;
    private LocalDate parkingDate;

    @Enumerated(STRING)
    private Size size;
    private boolean isForHandicap;

    // Vehicle Info
    private String licensePlate;
    @Enumerated(STRING)
    private VehicleType type;
    //private boolean hasHandicapParkingPermit;


    public Employee(String firstName, String lastName, Gender gender, String company, LocalDate parkingDate, Size size, boolean isForHandicap, String licensePlate, VehicleType type) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.company = company;
        this.parkingDate = parkingDate;
        this.size = size;
       this.isForHandicap = isForHandicap;
        this.licensePlate = licensePlate;
        this.type = type;
    }
}
