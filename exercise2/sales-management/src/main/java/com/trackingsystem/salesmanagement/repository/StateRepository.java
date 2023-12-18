package com.trackingsystem.salesmanagement.repository;

import com.trackingsystem.salesmanagement.model.Region;
import com.trackingsystem.salesmanagement.model.State;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StateRepository extends JpaRepository<State, Long> {

    // Cerca d'estats per nom amb paginació
    Page<State> findByName(String name, Pageable pageable);

    // Cerca d'estats per nom que contingui una subcadena sense paginació
    List<State> findByNameContaining(String name);

}