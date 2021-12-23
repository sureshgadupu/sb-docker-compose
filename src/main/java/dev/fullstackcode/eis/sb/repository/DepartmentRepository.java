package dev.fullstackcode.eis.sb.repository;


import dev.fullstackcode.eis.sb.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DepartmentRepository extends JpaRepository<Department,Integer> {
}
