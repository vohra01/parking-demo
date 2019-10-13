package com.jinternal.employee.controllers;

import com.jinternal.employee.ParkingTestUtils;
import com.jinternal.employee.configuration.ParkingConfiguration;
import com.jinternal.employee.dto.EmployeeRequestDto;
import com.jinternal.employee.entities.Employee;
import com.jinternal.employee.services.ParkingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static com.jinternal.employee.ParkingTestUtils.aVehicle;
import static com.jinternal.employee.ParkingTestUtils.toJson;
import static com.jinternal.employee.dto.EmployeeRequestDto.to;
import static java.util.Arrays.asList;
import static org.mockito.Mockito.when;
import static org.springframework.data.domain.PageRequest.of;
import static org.springframework.data.domain.Sort.Direction.ASC;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ParkingController.class)
@Import(ParkingConfiguration.class)
@AutoConfigureRestDocs(outputDir = "target/snippets")
@EnableSpringDataWebSupport
public class ParkingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ParkingController parkingController;

    @MockBean
    private ParkingService parkingService;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableHandlerMethodArgumentResolver;

    @Test
    public void shouldRegisterEmployee() throws Exception {
        EmployeeRequestDto employee = to(ParkingTestUtils.aVehicle("Mradul", "Pandey"));

        when(parkingService.saveEmployee(Mockito.any())).thenReturn(aVehicle(1L, "Mradul", "Pandey"));

        String content = toJson(employee);

        mockMvc.perform(post("/api/employee")
                .contentType(APPLICATION_JSON_UTF8_VALUE)
                .content(content))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("save-employee", pathParameters(),
                        requestFields(
                                fieldWithPath("firstName").description("Employee first name"),
                                fieldWithPath("lastName").description("Employee last name"),
                                fieldWithPath("gender").description("Employee gender"),
                                fieldWithPath("department").description("Employee department"),
                                fieldWithPath("dateOfBirth").description("Employee date of birth"),
                                fieldWithPath("size").description("Employee size"),
                                fieldWithPath("licensePlate").description("Employee licensePlate"),
                                fieldWithPath("type").description("Employee type"),
                                fieldWithPath("forHandicap").description("Employee forHandicap")
                        ),
                        responseFields(
                                fieldWithPath("id").description("Employee id"),
                                fieldWithPath("firstName").description("Employee first name"),
                                fieldWithPath("lastName").description("Employee last name"),
                                fieldWithPath("gender").description("Employee gender"),
                                fieldWithPath("department").description("Employee department"),
                                fieldWithPath("dateOfBirth").description("Employee date of birth"),
                                fieldWithPath("size").description("Employee size"),
                                fieldWithPath("licensePlate").description("Employee licensePlate"),
                                fieldWithPath("type").description("Employee type"),
                                fieldWithPath("forHandicap").description("Employee forHandicap")
                                )));
    }

    @Test
    public void shouldUpdateEmployee() throws Exception {
        EmployeeRequestDto employee = to(ParkingTestUtils.aVehicle("Mradul", "Pandey"));

        when(parkingService.updateEmployee(Mockito.any())).thenReturn(aVehicle(1L, "mradul", "pandey"));

        String content = toJson(employee);
        mockMvc.perform(put("/api/employee/{id}",1L)
                .contentType(APPLICATION_JSON_UTF8_VALUE)
                .content(content))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("update-employee", pathParameters(
                            parameterWithName("id").description("Employee id")
                        ),
                        requestFields(
                                fieldWithPath("firstName").description("Employee first name"),
                                fieldWithPath("lastName").description("Employee last name"),
                                fieldWithPath("gender").description("Employee gender"),
                                fieldWithPath("department").description("Employee department"),
                                fieldWithPath("dateOfBirth").description("Employee date of birth"),
                                fieldWithPath("size").description("Employee size"),
                                fieldWithPath("licensePlate").description("Employee licensePlate"),
                                fieldWithPath("type").description("Employee type"),
                                fieldWithPath("forHandicap").description("Employee forHandicap")

                        ),
                        responseFields(
                                fieldWithPath("id").description("Employee id"),
                                fieldWithPath("firstName").description("Employee first name"),
                                fieldWithPath("lastName").description("Employee last name"),
                                fieldWithPath("gender").description("Employee gender"),
                                fieldWithPath("department").description("Employee department"),
                                fieldWithPath("dateOfBirth").description("Employee date of birth"),
                                fieldWithPath("size").description("Employee size"),
                                fieldWithPath("licensePlate").description("Employee licensePlate"),
                                fieldWithPath("type").description("Employee type"),
                                fieldWithPath("forHandicap").description("Employee forHandicap")

                        )));
    }

    @Test
    public void shouldGetAllTheEmployee() throws Exception {
        EmployeeRequestDto employee = to(ParkingTestUtils.aVehicle("Mradul", "Pandey"));

        Page<Employee> employeePage = new PageImpl(asList(aVehicle(1L,"Mradul", "Pandey")), of(0,10, Sort.by(ASC,"id")), 1);

        when(parkingService.getAllEmployee(Mockito.any())).thenReturn(employeePage);

        mockMvc.perform(get("/api/employee?page=0&size=10&sort=id&id.dir=asc")
                .contentType(APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("get-all-employee", pathParameters(),
                        responseFields(
                                fieldWithPath("content.[0].id").description("Employee id"),
                                fieldWithPath("content.[0].firstName").description("Employee first name"),
                                fieldWithPath("content.[0].lastName").description("Employee last name"),
                                fieldWithPath("content.[0].gender").description("Employee gender"),
                                fieldWithPath("content.[0].department").description("Employee department"),
                                fieldWithPath("content.[0].dateOfBirth").description("Employee date of birth"),
                                fieldWithPath("content.[0].size").description("Employee licensePlate"),
                                fieldWithPath("content.[0].type").description("Employee type"),
                                fieldWithPath("content.[0].licensePlate").description("Employee size"),
                                fieldWithPath("content.[0].forHandicap").description("Employee forHandicap"),
                                fieldWithPath("totalElements").description("Total elements"),
                                fieldWithPath("numberOfElements").description("Number of elements in page"),
                                fieldWithPath("totalPages").description("Total pages"),
                                fieldWithPath("first").description("Is first page"),
                                fieldWithPath("last").description("Is last page"),
                                fieldWithPath("size").description("Size of page"),
                                fieldWithPath("number").description("current page number"),
                                fieldWithPath("sort.sorted").description("Is page sorted"),
                                fieldWithPath("sort.unsorted").description("Is page unsorted"),
                                fieldWithPath("pageable").description("Pageable Information"),
                                fieldWithPath("pageable.paged").description("Is paged"),
                                fieldWithPath("pageable.unpaged").description("Is unpaged"),
                                fieldWithPath("pageable.pageSize").description("PageSize"),
                                fieldWithPath("pageable.pageNumber").description("PageNumber"),
                                fieldWithPath("pageable.offset").description("Page offset"),
                                fieldWithPath("pageable.sort.sorted").description("Is page sorted"),
                                fieldWithPath("pageable.sort.unsorted").description("Is page unsorted")
                        )));
    }
}
