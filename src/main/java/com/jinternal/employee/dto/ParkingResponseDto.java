package com.jinternal.employee.dto;

import com.jinternal.employee.entities.Employee;
import com.jinternal.employee.entities.ParkingBuilder;
import com.jinternal.employee.entities.Gender;
import com.jinternal.employee.enums.enums.Size;
import com.jinternal.employee.enums.enums.VehicleType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Getter
@Setter
public class ParkingResponseDto {
    private Long id;

    private String firstName;

    private String lastName;

    private String gender;

    private String department;

    private LocalDate dateOfBirth;

    private boolean isForHandicap;

    private Size size;

    private String licensePlate;

    private VehicleType type;


    public static Employee from(ParkingResponseDto parkingResponseDto) {

        return ParkingBuilder
                .employee()
                .withFirstName(parkingResponseDto.firstName)
                .withLastName(parkingResponseDto.lastName)
                .withGender(Gender.valueOf(parkingResponseDto.gender))
                .withDepartment(parkingResponseDto.department)
                .withSize(parkingResponseDto.size)
                .withForHandicap(parkingResponseDto.isForHandicap)
                .withLicensePlate(parkingResponseDto.licensePlate)
                .withType(parkingResponseDto.type)
                .build();

    }

    public static ParkingResponseDto toResponse(Employee employee) {

        ParkingResponseDto parkingResponseDto = new ParkingResponseDto();
        parkingResponseDto.setId(employee.getId());
        parkingResponseDto.setFirstName(employee.getFirstName());
        parkingResponseDto.setLastName(employee.getLastName());
        parkingResponseDto.setDepartment(employee.getDepartment());
        parkingResponseDto.setDateOfBirth(employee.getDateOfBirth());
        parkingResponseDto.setGender(employee.getGender().toString());
        parkingResponseDto.setSize(employee.getSize());
        parkingResponseDto.setType(employee.getType());
        parkingResponseDto.setLicensePlate(employee.getLicensePlate());

        return parkingResponseDto;
    }

    public static List<ParkingResponseDto> toResponse(List<Employee> employee) {
        return employee
                .stream()
                .map(emp -> toResponse(emp))
                .collect(toList());

    }
}
