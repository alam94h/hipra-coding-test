package com.trackingsystem.salesmanagement.controller;

import com.trackingsystem.salesmanagement.model.Employee;
import com.trackingsystem.salesmanagement.model.Sale;
import com.trackingsystem.salesmanagement.repository.EmployeeRepository;
import com.trackingsystem.salesmanagement.repository.SaleRepository;
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
public class SaleController {

    @Autowired
    SaleRepository saleRepository;

    // Obté totes les vendes, opcionalment filtrades per quantitat
    @GetMapping("/sales")
    public ResponseEntity<List<Sale>> getSales(@RequestParam(required = false) Integer amount) {
        try {
            List<Sale> sales = new ArrayList<Sale>();

            // Obté totes les vendes si no hi ha cap filtre de quantitat, sinó obté les vendes per la quantitat proporcionada
            if (amount == null)
                sales.addAll(saleRepository.findAll());
            else
                sales.addAll(saleRepository.findByAmount(amount));

            // Retorna una resposta amb les vendes o sense contingut si la llista és buida
            if (sales.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(sales, HttpStatus.OK);
        } catch (Exception e) {
            // Retorna un error intern del servidor si es produeix una excepció
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Endpoint per cercar vendes per import amb paginació
    @GetMapping("/salesPaginated")
    public ResponseEntity<Page<Sale>> getSalesPaginated(
            @RequestParam(required = false) Integer amount,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        try {
            Pageable paging = PageRequest.of(page, size);
            Page<Sale> sales;

            if (amount == null) {
                // Si l'import no està present, retorna totes les vendes paginades
                sales = saleRepository.findAll(paging);
            } else {
                // Si l'import està present, retorna les vendes per import paginades
                sales = saleRepository.findByAmount(amount, paging);
            }

            if (sales.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(sales, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
