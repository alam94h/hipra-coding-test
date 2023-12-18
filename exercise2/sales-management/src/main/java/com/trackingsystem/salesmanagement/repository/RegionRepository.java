package com.trackingsystem.salesmanagement.repository;

import com.trackingsystem.salesmanagement.model.Region;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegionRepository extends JpaRepository<Region, Long> {

    // Cerca les regions per nom amb paginació
    Page<Region> findByName(String name, Pageable pageable);

    // Cerca les regions per part del nom
    List<Region> findByNameContaining(String name);
}