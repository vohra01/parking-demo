package com.jinternal.employee.services;

import com.jinternal.employee.entities.Employee;
import com.jinternal.employee.exception.ServiceException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ParkingService {

    Employee saveEmployee(Employee employee);

    Employee getEmployee(Long id);

    Employee updateEmployee(Employee employee) throws ServiceException;

    boolean removeEmployee(Employee employee) throws ServiceException;

    public void removeEmployee(Long id) throws ServiceException ;

        void resetParking();

    Page<Employee> getAllEmployee(Pageable pageable);

    public String spotMyCar(Long id);

    public Employee saveMyCar(Employee employee);
}
