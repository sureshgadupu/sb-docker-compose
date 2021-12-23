package dev.fullstackcode.eis.sb.repository;


import dev.fullstackcode.eis.sb.entity.Employee;
import dev.fullstackcode.eis.sb.entity.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface  EmployeeRepository extends JpaRepository<Employee,Integer> {

    public  List<Employee> findByGender(Gender gender);

    @Query("select e from Employee e where e.gender = 'M'")
//    @Query("select e from Employee e where e.gender =  dev.fullstackcode.eis.springbootnativeimage.entity.Gender.M ")
    public  List<Employee> searchByGender(Gender gender);
}
