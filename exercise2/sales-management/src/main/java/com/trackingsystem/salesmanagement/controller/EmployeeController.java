package com.trackingsystem.salesmanagement.controller;

import com.trackingsystem.salesmanagement.model.Employee;
import com.trackingsystem.salesmanagement.model.Sale;
import com.trackingsystem.salesmanagement.model.State;
import com.trackingsystem.salesmanagement.repository.EmployeeRepository;
import com.trackingsystem.salesmanagement.repository.SaleRepository;
import com.trackingsystem.salesmanagement.repository.StateRepository;
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
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    private SaleRepository saleRepository;

    // Obté tots els empleats, opcionalment filtrats per nom
    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getEmployees(@RequestParam(required = false) String name) {
        try {
            List<Employee> employees = new ArrayList<Employee>();

            // Obté tots els empleats si no hi ha cap filtre de nom, sinó obté els empleats pel nom que contingui la cadena proporcionada
            if (name == null)
                employees.addAll(employeeRepository.findAll());
            else
                employees.addAll(employeeRepository.findByNameContaining(name));

            // Retorna una resposta amb els empleats o sense contingut si la llista és buida
            if (employees.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(employees, HttpStatus.OK);
        } catch (Exception e) {
            // Retorna un error intern del servidor si es produeix una excepció
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Endpoint per cercar empleats per nom amb paginació
    @GetMapping("/employeesPaginated")
    public ResponseEntity<Page<Employee>> getEmployeesPaginated(
            @RequestParam(required = false, defaultValue = "") String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        try {
            Pageable paging = PageRequest.of(page, size);
            Page<Employee> employees;

            if (name.isEmpty()) {
                // Si el nom està buit, retorna tots els empleats paginats
                employees = employeeRepository.findAll(paging);
            } else {
                // Si el nom no està buit, retorna els empleats per nom paginats
                employees = employeeRepository.findByName(name, paging);
            }

            if (employees.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(employees, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Obté l'import total de vendes per a un empleat específic
    @GetMapping("/employees/{employeeId}/salesAmount")
    public Integer getSalesAmountForEmployee(@PathVariable Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Empleat no trobat amb id: " + employeeId));

        // Calcula i retorna l'import total de vendes per a l'empleat
        Integer salesAmount = saleRepository.sumSalesAmountByEmployee(employee);
        return salesAmount != null ? salesAmount : 0;
    }

    // Afegeix una nova venda per a un empleat específic
    @PostMapping("employees/{employeeId}/addSale/{amount}")
    public ResponseEntity<String> addSaleForEmployee(
            @PathVariable Long employeeId,
            @PathVariable Integer amount) {

        // Troba l'empleat per ID o llença una excepció si no es troba
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Empleat no trobat amb id: " + employeeId));

        // Crea una nova venda amb l'import proporcionat i l'empleat associat
        Sale sale = new Sale(amount, employee);

        // Desa la venda i retorna una resposta amb l'ID de la venda creada
        Sale savedSale = saleRepository.save(sale);
        return new ResponseEntity<>("Venda creada amb ID: " + savedSale.getId(), HttpStatus.CREATED);
    }
}
