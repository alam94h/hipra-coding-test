package com.trackingsystem.salesmanagement.repository;

import com.trackingsystem.salesmanagement.model.Employee;
import com.trackingsystem.salesmanagement.model.State;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // Cerca els empleats per nom amb paginació
    Page<Employee> findByName(String name, Pageable pageable);

    // Cerca els empleats per part del nom
    List<Employee> findByNameContaining(String name);
}
