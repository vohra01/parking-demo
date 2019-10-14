package com.jinternal.employee.entities;

import com.jinternal.employee.enums.enums.Size;
import com.jinternal.employee.enums.enums.VehicleType;

import java.time.LocalDate;


public class ParkingBuilder {
    private String firstName;
    private String lastName;
    private Gender gender;
    private String company;
    private LocalDate parkingDate;
    private Size size;
    private boolean isForHandicap;
    private String licensePlate;
    private VehicleType type;

    public ParkingBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public ParkingBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public ParkingBuilder withGender(Gender gender) {
        this.gender = gender;
        return this;
    }

    public ParkingBuilder withDepartment(String company) {
        this.company = company;
        return this;
    }

    public ParkingBuilder withParkingDate(LocalDate parkingDate) {
        this.parkingDate = parkingDate;
        return this;
    }

    public ParkingBuilder withSize(Size size) {
        this.size = size;
        return this;
    }

    public ParkingBuilder withType(VehicleType type) {
        this.type = type;
        return this;
    }

    public ParkingBuilder withForHandicap(boolean isForHandicap) {
        this.isForHandicap = isForHandicap;
        return this;
    }

    public ParkingBuilder withLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
        return this;
    }

    public Employee build() {
        return new Employee(firstName, lastName, gender, company, parkingDate, size, isForHandicap, licensePlate, type);
    }

    public static ParkingBuilder employee(){
        return new ParkingBuilder();
    }
}
