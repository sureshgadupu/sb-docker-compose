package dev.fullstackcode.eis.sb;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.fullstackcode.eis.sb.controller.EmployeeController;
import dev.fullstackcode.eis.sb.entity.Department;
import dev.fullstackcode.eis.sb.entity.Employee;
import dev.fullstackcode.eis.sb.entity.Gender;
import dev.fullstackcode.eis.sb.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = EmployeeController.class)
public class EmployeeControllerTest  {

    Logger log = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private EmployeeService employeeService;

    @Test
    public void testGetEmployeeById() throws Exception {

        int empId = 1;
        Department dept = new Department();
        dept.setId(100);

        Employee emp = new Employee();

        emp.setId(empId);
        emp.setFirst_name("abc");
        emp.setLast_name("xyz");
        emp.setDepartment(dept);
        emp.setBirth_date(LocalDate.of(1980,11,11));
        emp.setHire_date(LocalDate.of(2020,01,01));
        emp.setGender(Gender.F);

        when(employeeService.getEmployeeById(empId)).thenReturn(emp);

        this.mockMvc.perform(get("/employee/{id}", empId))
                .andExpect(status().isOk()) // check HTTP status
                .andExpect(jsonPath("$.first_name").value("abc")) // check "content"
                .andExpect(jsonPath("$.last_name").value("xyz")) // check "title"
                .andExpect(jsonPath("$.id", is(empId))); // check "id"
        verify(employeeService).getEmployeeById(empId);

    }

    @Test
    public void testCreateEmployee() throws Exception {

        int empId = 1;
        Department dept = new Department();
        dept.setId(100);

        Employee emp = new Employee();

        emp.setId(empId);
        emp.setFirst_name("abc");
        emp.setLast_name("xyz");
        emp.setDepartment(dept);
        emp.setBirth_date(LocalDate.of(1980,11,11));
        emp.setHire_date(LocalDate.of(2020,01,01));
        emp.setGender(Gender.F);

        when(employeeService.createEmployee(any(Employee.class))).thenReturn(emp);

        this.mockMvc.perform(post("/employee").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(emp)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.first_name").value("abc")) // check "content"
                .andExpect(jsonPath("$.last_name").value("xyz")) // check "title"
                .andExpect(jsonPath("$.id", is(1))); // check "id"
        verify(employeeService).createEmployee(any(Employee.class));

    }


}
