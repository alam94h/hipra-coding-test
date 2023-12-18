package com.trackingsystem.salesmanagement.repository;

import com.trackingsystem.salesmanagement.model.Employee;
import com.trackingsystem.salesmanagement.model.Region;
import com.trackingsystem.salesmanagement.model.Sale;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    // Cerca les vendes per quantitat amb paginació
    Page<Sale> findByAmount(Integer amount, Pageable pageable);

    // Cerca les vendes per quantitat sense paginació
    List<Sale> findByAmount(Integer amount);

    // Consulta personalitzada per sumar l'import total de les vendes per un empleat
    @Query("SELECT SUM(s.amount) FROM Sale s WHERE s.employees = :employee")
    Integer sumSalesAmountByEmployee(@Param("employee") Employee employee);

}