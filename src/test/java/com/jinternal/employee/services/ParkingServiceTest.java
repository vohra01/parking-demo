package com.jinternal.employee.services;

import com.jinternal.employee.ParkingTestUtils;
import com.jinternal.employee.entities.Employee;
import com.jinternal.employee.exception.ServiceException;
import com.jinternal.employee.repositories.ParkingRepository;
import com.jinternal.employee.services.impl.ParkingServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static com.jinternal.employee.ParkingTestUtils.aVehicle;
import static java.util.Optional.of;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ParkingServiceTest {

    @Mock
    ParkingRepository parkingRepository;
    private ParkingService parkingService;

    @Before
    public void setUp() throws Exception {
        parkingService = new ParkingServiceImpl(parkingRepository);
    }

    @Test
    public void shouldSaveEmployee() {
        when(parkingRepository.save(Mockito.any())).thenReturn(ParkingTestUtils.aVehicle("Kunal", "Vohra"));
        parkingService.saveEmployee(ParkingTestUtils.aVehicle("Kunal", "Vohra"));
        Mockito.verify(parkingRepository).save(ParkingTestUtils.aVehicle("Kunal", "Vohra"));
    }

    @Test
    public void shouldGetEmployeeId() {
        when(parkingRepository.findById(1L)).thenReturn(of(aVehicle(1L, "Kunal", "Vohra")));
        Employee employee = parkingService.getEmployee(1L);

        Assertions.assertThat(employee).isNotNull();
        Assertions.assertThat(employee.getFirstName()).isEqualTo("Kunal");
        Assertions.assertThat(employee.getLastName()).isEqualTo("Vohra");

    }

    @Test(expected = ServiceException.class)
    public void shouldThrowServiceExceptionOnDeleteEmployee() throws ServiceException {
        parkingService.removeEmployee(ParkingTestUtils.aVehicle(1l, "Kunal", "Vohra"));
        Mockito.verify(parkingRepository).delete(ParkingTestUtils.aVehicle(1l, "Kunal", "Vohra"));
    }


    @Test
    public void shouldDeleteEmployee() throws ServiceException {
        when(parkingRepository.findById(1L)).thenReturn(of(aVehicle(1L, "Kunal", "Vohra")));
        parkingService.removeEmployee(ParkingTestUtils.aVehicle(1l, "Kunal", "Vohra"));
        Mockito.verify(parkingRepository).delete(ParkingTestUtils.aVehicle(1l, "Kunal", "Vohra"));
    }

    @Test
    public void shouldDeleteAllRecords() throws ServiceException {
        parkingService.resetParking();
        Mockito.verify(parkingRepository).deleteAll();
    }
}
