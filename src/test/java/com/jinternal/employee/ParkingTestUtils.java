package com.jinternal.employee;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.jinternal.employee.configuration.ParkingConfiguration;
import com.jinternal.employee.entities.Employee;
import com.jinternal.employee.entities.Gender;
import com.jinternal.employee.enums.enums.Size;
import com.jinternal.employee.enums.enums.VehicleType;

import java.io.IOException;
import java.time.LocalDate;

import static com.jinternal.employee.entities.ParkingBuilder.employee;
import static java.time.LocalDate.now;
import static java.time.format.DateTimeFormatter.ofPattern;

public class ParkingTestUtils {
    public static Employee aVehicle(String firstName, String lastName) {
        return employee()
                .withFirstName(firstName)
                .withLastName(lastName)
                .withGender(Gender.MALE)
                .withDepartment("Some-department")
                .withDateOfBirth(now().minusYears(29))
                .withSize(Size.MEDIUM)
                .withType(VehicleType.CAR)
                .withLicensePlate("MH12MX-0734")
                .build();
    }

    public static Employee aVehicle(Long id, String firstName, String lastName) {
        Employee employee = aVehicle(firstName, lastName);
        employee.setId(id);
        return employee;
    }

    public static String toJson(Object obj) throws IOException {
        ObjectMapper objectMapper = objectMapper();
        //objectMapper.registerModule(new MicroTypeModule());

        ObjectWriter writer = objectMapper.writer().withDefaultPrettyPrinter();
        return writer.writeValueAsString(obj);
    }

    public static ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(LocalDate.class, new LocalDateDeserializer(ofPattern(ParkingConfiguration.DATE_FORMAT)));
        module.addSerializer(LocalDate.class, new LocalDateSerializer(ofPattern(ParkingConfiguration.DATE_FORMAT)));
        mapper.registerModule(module);
        return mapper;
    }
}
