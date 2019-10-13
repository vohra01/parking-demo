package com.jinternal.employee.repositories;

import com.jinternal.employee.entities.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingRepository extends PagingAndSortingRepository<Employee,Long> {

    @Query("from Employee where id = ?1")
    public Employee findOne(Long id);
}
