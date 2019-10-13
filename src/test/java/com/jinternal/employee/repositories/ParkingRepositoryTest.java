package com.jinternal.employee.repositories;

import com.jinternal.employee.ParkingTestUtils;
import com.jinternal.employee.entities.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import static com.jinternal.employee.ParkingTestUtils.aVehicle;
import static java.time.LocalDate.now;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.data.domain.PageRequest.of;


@RunWith(SpringRunner.class)
@DataJpaTest
public class ParkingRepositoryTest {


    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ParkingRepository parkingRepository;

    @Test
    public void shouldFindSavedEmployeeById() {
        // given
        Employee employee = ParkingTestUtils.aVehicle("mradul","pandey");
        entityManager.persist(employee);
        entityManager.flush();

        // when
        Employee found = parkingRepository.findById(employee.getId()).get();

        // then

        assertThat(found).isNotNull();

        assertThat(found.getFirstName()).isEqualTo(employee.getFirstName());
        assertThat(found.getLastName()).isEqualTo(employee.getLastName());
        assertThat(found.getGender()).isEqualTo(employee.getGender());
        assertThat(found.getDepartment()).isEqualTo(employee.getDepartment());
        assertThat(found.getDateOfBirth()).isEqualTo(employee.getDateOfBirth());
    }

    @Test
    public void shouldFindAllTheEmployeeInPagedForm() {
        // given
        Employee employee1 = ParkingTestUtils.aVehicle("mradul","pandey");
        Employee employee2 = ParkingTestUtils.aVehicle("mayank","pandey");
        entityManager.persist(employee1);
        entityManager.persist(employee2);
        entityManager.flush();

        // when
        Page<Employee> found = parkingRepository.findAll(of(0, 10, Sort.by("id")));

        // then

        assertThat(found).isNotNull();

        assertThat(found.getTotalElements()).isEqualTo(2);
        assertThat(found.getTotalPages()).isEqualTo(1);
        assertThat(found.getNumber()).isEqualTo(0);
        assertThat(found.getContent().size()).isEqualTo(2);
    }



}
