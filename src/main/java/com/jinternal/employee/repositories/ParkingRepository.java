package com.jinternal.employee.repositories;

import com.jinternal.employee.entities.Employee;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingRepository extends PagingAndSortingRepository<Employee,Long> {
}
