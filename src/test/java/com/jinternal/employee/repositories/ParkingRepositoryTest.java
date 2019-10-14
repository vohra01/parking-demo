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
        Employee employee = ParkingTestUtils.aVehicle("Kunal","Vohra");
        entityManager.persist(employee);
        entityManager.flush();

        // when
        Employee found = parkingRepository.findById(employee.getId()).get();

        // then

        assertThat(found).isNotNull();

        assertThat(found.getFirstName()).isEqualTo(employee.getFirstName());
        assertThat(found.getLastName()).isEqualTo(employee.getLastName());
        assertThat(found.getGender()).isEqualTo(employee.getGender());
        assertThat(found.getCompany()).isEqualTo(employee.getCompany());
        assertThat(found.getParkingDate()).isEqualTo(employee.getParkingDate());
    }

    @Test
    public void shouldFindAllTheEmployeeInPagedForm() {
        // given
        Employee employee1 = ParkingTestUtils.aVehicle("Kunal","Vohra");
        Employee employee2 = ParkingTestUtils.aVehicle("mayank","pandey");
        entityManager.persist(employee1);
        entityManager.persist(employee2);
        entityManager.flush();

        // when
        Page<Employee> found = parkingRepository.findAll(of(0, 10, Sort.by("id")));

        // then

        assertThat(found).isNotNull();

        assertThat(found.getTotalElements()).isEqualTo(3);
        assertThat(found.getTotalPages()).isEqualTo(1);
        assertThat(found.getNumber()).isEqualTo(0);
        assertThat(found.getContent().size()).isEqualTo(3);
    }


    @Test
    public void shouldFindSavedEmployeeByIdCustomQuery() {
        // given
        Employee employee = ParkingTestUtils.aVehicle("Kunal","Vohra");
        entityManager.persist(employee);
        entityManager.flush();

        // when
        Employee found = parkingRepository.findOne(employee.getId());

        // then

        assertThat(found).isNotNull();

        assertThat(found.getFirstName()).isEqualTo(employee.getFirstName());
        assertThat(found.getLastName()).isEqualTo(employee.getLastName());
        assertThat(found.getGender()).isEqualTo(employee.getGender());
        assertThat(found.getCompany()).isEqualTo(employee.getCompany());
        assertThat(found.getParkingDate()).isEqualTo(employee.getParkingDate());
    }

    @Test
    public void shouldNotFindSavedEmployeeByIdWhenInputNull() {
        assertThat(parkingRepository.findOne(null)).isNull();
    }

    @Test
    public void shouldDeleteEmployeeSuccessFullyUsingID() {
        // given
        Employee employee = ParkingTestUtils.aVehicle("Kunal","Vohra");
        entityManager.persist(employee);
        entityManager.flush();

        // when
        parkingRepository.deleteById(employee.getId());

        // then
        Employee found = parkingRepository.findOne(employee.getId());

        assertThat(found).isNull();
    }

    @Test
    public void shouldDeleteEmployeeSuccessFully() {
        // given
        Employee employee = ParkingTestUtils.aVehicle("Kunal","Vohra");
        entityManager.persist(employee);
        entityManager.flush();

        // when
        parkingRepository.delete(employee);

        // then
        Employee found = parkingRepository.findOne(employee.getId());

        assertThat(found).isNull();
    }
}
