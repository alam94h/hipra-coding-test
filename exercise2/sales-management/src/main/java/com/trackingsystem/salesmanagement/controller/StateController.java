package com.trackingsystem.salesmanagement.controller;

import com.trackingsystem.salesmanagement.model.State;
import com.trackingsystem.salesmanagement.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StateController {

    @Autowired
    StateRepository stateRepository;

    // Obté tots els estats, opcionalment filtrats pel nom
    @GetMapping("/states")
    public ResponseEntity<List<State>> getStates(@RequestParam(required = false) String name) {
        try {
            List<State> states = new ArrayList<State>();

            // Obté tots els estats si no hi ha cap filtre de nom, sinó obté els estats pel nom proporcionat
            if (name == null)
                states.addAll(stateRepository.findAll());
            else
                states.addAll(stateRepository.findByNameContaining(name));

            // Retorna una resposta amb els estats o sense contingut si la llista és buida
            if (states.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(states, HttpStatus.OK);
        } catch (Exception e) {
            // Retorna un error intern del servidor si es produeix una excepció
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Endpoint per cercar estats per nom amb paginació
    @GetMapping("/statesPaginated")
    public ResponseEntity<Page<State>> getStatesPaginated(
            @RequestParam(required = false, defaultValue = "") String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        try {
            Pageable paging = PageRequest.of(page, size);
            Page<State> states;

            if (name.isEmpty()) {
                // Si el nom està buit, retorna tots els estats paginats
                states = stateRepository.findAll(paging);
            } else {
                // Si el nom no està buit, retorna els estats per nom paginats
                states = stateRepository.findByName(name, paging);
            }

            if (states.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(states, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
