package dev.fullstackcode.eis.sb.it;

import dev.fullstackcode.eis.sb.entity.Department;
import dev.fullstackcode.eis.sb.entity.Employee;
import dev.fullstackcode.eis.sb.entity.Gender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class EmployeeControllerIntegration extends BaseIT {

    @Value("${spring.datasource.url}") private static String url;
    @Test
    @Sql({ "/import.sql" })
    public void saveUser() {

        System.out.println("url :" + url);
        Department dept = new Department();
        dept.setId(100);

        Employee emp = new Employee();

        emp.setFirst_name("abc");
        emp.setLast_name("xyz");
        emp.setDepartment(dept);
        emp.setBirth_date(LocalDate.of(1980,11,11));
        emp.setHire_date(LocalDate.of(2020,01,01));
        emp.setGender(Gender.F);

        ResponseEntity<Employee> response = testRestTemplate.postForEntity( "/employee", emp, Employee.class);

        Employee employee =  response.getBody();

        assertNotNull(employee.getId());
        assertEquals("abc", employee.getFirst_name());

    }


}
