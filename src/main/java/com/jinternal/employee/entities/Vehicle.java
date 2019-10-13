package com.jinternal.employee.entities;


import com.jinternal.employee.enums.enums.Size;
import com.jinternal.employee.enums.enums.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class Vehicle {
    private final String licensePlate;
    private final VehicleType type;
    private final boolean hasHandicapParkingPermit;


}
