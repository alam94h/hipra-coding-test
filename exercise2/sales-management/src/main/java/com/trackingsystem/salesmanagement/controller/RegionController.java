package com.trackingsystem.salesmanagement.controller;

import com.trackingsystem.salesmanagement.model.Region;
import com.trackingsystem.salesmanagement.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RegionController {

    @Autowired
    RegionRepository regionRepository;

    // Obté totes les regions, opcionalment filtrades per nom
    @GetMapping("/regions")
    public ResponseEntity<List<Region>> getRegions(@RequestParam(required = false) String name) {
        try {
            List<Region> regions = new ArrayList<Region>();

            // Obté totes les regions si no hi ha cap filtre de nom, sinó obté les regions pel nom que contingui la cadena proporcionada
            if (name == null)
                regions.addAll(regionRepository.findAll());
            else
                regions.addAll(regionRepository.findByNameContaining(name));

            // Retorna una resposta amb les regions o sense contingut si la llista és buida
            if (regions.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(regions, HttpStatus.OK);
        } catch (Exception e) {
            // Retorna un error intern del servidor si es produeix una excepció
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Endpoint per cercar regions per nom amb paginació
    @GetMapping("/regionsPaginated")
    public ResponseEntity<Page<Region>> getRegionsPaginated(
            @RequestParam(required = false, defaultValue = "") String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        try {
            Pageable paging = PageRequest.of(page, size);
            Page<Region> regions;

            if (name.isEmpty()) {
                // Si el nom està buit, retorna totes les regions paginades
                regions = regionRepository.findAll(paging);
            } else {
                // Si el nom no està buit, retorna les regions per nom paginades
                regions = regionRepository.findByName(name, paging);
            }

            if (regions.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(regions, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
