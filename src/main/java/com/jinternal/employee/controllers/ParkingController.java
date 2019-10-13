package com.jinternal.employee.controllers;

import com.jinternal.employee.dto.EmployeeRequestDto;
import com.jinternal.employee.dto.ParkingResponseDto;
import com.jinternal.employee.entities.Employee;
import com.jinternal.employee.exception.RestException;
import com.jinternal.employee.exception.ServiceException;
import com.jinternal.employee.services.ParkingService;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.jinternal.employee.dto.EmployeeRequestDto.fromRequest;
import static com.jinternal.employee.dto.ParkingResponseDto.toResponse;

@RestController
@RequestMapping("/api")
public class ParkingController {

    private ParkingService parkingService;

    @Autowired
    public ParkingController(ParkingService parkingService) {
        this.parkingService = parkingService;
    }

    @PostMapping("/employee")
    @ResponseBody
    @ApiResponse(code = 400,message = "failure")
    public ParkingResponseDto registerEmployee(@RequestBody @Valid EmployeeRequestDto employeeRequestDto) {
        Employee employee = parkingService.saveMyCar(fromRequest(employeeRequestDto));
        return toResponse(employee);
    }

    @GetMapping("/employee")
    @ResponseBody
    public Page<ParkingResponseDto> getAllParkedVehicles(Pageable pageable) {
        Page<Employee> employeePage = parkingService.getAllEmployee(pageable);
        return toPageResponse(pageable, employeePage);
    }


    @PutMapping("/employee/{id}")
    @ResponseBody
    public ParkingResponseDto updateEmployee(@PathVariable("id") Long id,
                                             @RequestBody @Valid EmployeeRequestDto employeeRequestDto) throws RestException {
        try {
            Employee employee = fromRequest(employeeRequestDto);
            employee.setId(id);
            employee = parkingService.updateEmployee(employee);
            return toResponse(employee);
        } catch (ServiceException e) {
            throw new RestException(e);
        }

    }

    @DeleteMapping()
    public boolean deleteEmployee(@RequestParam("id") long id) throws ServiceException {
        System.out.println("Came To Delete You");
        Employee employee = parkingService.getEmployee(id);
        //employee.setId(id);
        return parkingService.removeEmployee(employee);
    }



    @DeleteMapping(path = { "/{id}" })
    public Employee delete(@PathVariable("id") int id) throws ServiceException {
        System.out.println("Came To Delete You");
        Employee employee = parkingService.getEmployee(new Long(id));
        //employee.setId(id);
        parkingService.removeEmployee(employee);
        System.out.println("Deleted You");
        return new Employee();
    }


    @GetMapping("/employeeDelete/{id}")
    @ResponseBody
    public boolean upda1teEmployee(@PathVariable("id") Long id) throws RestException {
        try {
            Employee employee = parkingService.getEmployee(id);
            //employee.setId(id);
            parkingService.removeEmployee(employee);
            return true;
        } catch (ServiceException e) {
            throw new RestException(e);
        }

    }

    @PostMapping("/employee1")
    @ResponseBody
    public ParkingResponseDto registerEmployee1(@RequestBody @Valid EmployeeRequestDto employeeRequestDto) {
        Employee employee = parkingService.saveEmployee(fromRequest(employeeRequestDto));
        return toResponse(employee);
    }



    private PageImpl<ParkingResponseDto> toPageResponse(Pageable pageable, Page<Employee> employee) {
        return new PageImpl(toResponse(employee.getContent()), pageable, employee.getTotalElements());
    }
}
