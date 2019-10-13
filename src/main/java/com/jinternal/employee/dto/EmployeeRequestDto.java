package com.jinternal.employee.dto;

import com.jinternal.employee.entities.Employee;
import com.jinternal.employee.entities.ParkingBuilder;
import com.jinternal.employee.validators.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import static com.jinternal.employee.configuration.ParkingConfiguration.DATE_FORMATTER;
import static java.time.LocalDate.parse;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequestDto {

    @NotNull
    private String size;

    @NotNull
    private boolean isForHandicap;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @Gender
    private String gender;

    @NotEmpty
    private String department;

    @NotNull
    private String dateOfBirth;

    // Vehicle Info

    @NotNull
    private String licensePlate;

    @NotNull
    private String type;


    public static Employee fromRequest(EmployeeRequestDto requestDto) {

        return ParkingBuilder
                .employee()
                .withFirstName(requestDto.firstName)
                .withLastName(requestDto.lastName)
                .withGender(com.jinternal.employee.entities.Gender.valueOf(requestDto.gender))
                .withDepartment(requestDto.department)
                .withDateOfBirth(parse(requestDto.dateOfBirth, DATE_FORMATTER))
                .withSize(com.jinternal.employee.enums.enums.Size.valueOf(requestDto.size))
                .withForHandicap(requestDto.isForHandicap)
                .withLicensePlate(requestDto.licensePlate)
                .withType(com.jinternal.employee.enums.enums.VehicleType.valueOf(requestDto.type))
                .build();

    }

    public static EmployeeRequestDto to(Employee employee) {

        EmployeeRequestDto employeeRequestDto = new EmployeeRequestDto();
        employeeRequestDto.setFirstName(employee.getFirstName());
        employeeRequestDto.setLastName(employee.getLastName());
        employeeRequestDto.setDepartment(employee.getDepartment());
        employeeRequestDto.setDateOfBirth(employee.getDateOfBirth().format(DATE_FORMATTER));
        employeeRequestDto.setGender(employee.getGender().toString());
        employeeRequestDto.setSize(employee.getSize().name());
        employeeRequestDto.setType(employee.getType().name());
        employeeRequestDto.setLicensePlate(employee.getLicensePlate());
        employeeRequestDto.setForHandicap(true);

        return employeeRequestDto;
    }
}
