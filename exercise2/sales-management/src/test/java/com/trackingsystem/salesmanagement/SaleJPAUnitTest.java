package com.trackingsystem.salesmanagement;

import com.trackingsystem.salesmanagement.model.Employee;
import com.trackingsystem.salesmanagement.model.Sale;
import com.trackingsystem.salesmanagement.model.State;
import com.trackingsystem.salesmanagement.repository.SaleRepository;
import com.trackingsystem.salesmanagement.repository.EmployeeRepository;
import com.trackingsystem.salesmanagement.repository.StateRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class SaleJPAUnitTest {

    @Autowired
    SaleRepository saleRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Test
    public void should_store_a_sale() {
        // Arrange
        Employee employee = employeeRepository.findById(1L).get();

        // Act
        Sale sale = saleRepository.save(new Sale(1000, employee));

        // Assert
        assertThat(sale).hasFieldOrPropertyWithValue("amount", 1000);
    }

    @Test
    public void should_find_sale_by_id() {
        // Arrange
        Employee employee = employeeRepository.findById(1L).get();
        Sale sale = saleRepository.save(new Sale(1000, employee));

        // Act
        Sale foundSale = saleRepository.findById(sale.getId()).get();

        // Assert
        assertThat(foundSale).isEqualTo(sale);
    }

    @Test
    public void should_update_sale_by_id() {
        // Arrange
        Employee employee = employeeRepository.findById(1L).get();
        Sale sale = saleRepository.save(new Sale(1000, employee));

        // Act
        Sale updatedSale = new Sale(1200, employee);
        Sale Sale2 = saleRepository.findById(sale.getId()).get();
        Sale2.setAmount(updatedSale.getAmount());
        saleRepository.save(Sale2);

        // Assert
        Sale checkSale = saleRepository.findById(sale.getId()).get();
        assertThat(checkSale.getId()).isEqualTo(sale.getId());
        assertThat(checkSale.getAmount()).isEqualTo(updatedSale.getAmount());
    }

    @Test
    public void should_delete_sale_by_id() {
        // Arrange
        Employee employee = employeeRepository.findById(1L).get();
        Sale sale = saleRepository.save(new Sale(1000, employee));

        // Act
        saleRepository.deleteById(sale.getId());

        // Assert
        assertThat(saleRepository.findById(sale.getId())).isEmpty();
    }
}
