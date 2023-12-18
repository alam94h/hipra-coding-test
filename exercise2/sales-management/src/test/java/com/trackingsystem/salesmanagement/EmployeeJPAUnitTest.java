package com.trackingsystem.salesmanagement;

import com.trackingsystem.salesmanagement.model.Employee;
import com.trackingsystem.salesmanagement.model.State;
import com.trackingsystem.salesmanagement.repository.EmployeeRepository;
import com.trackingsystem.salesmanagement.repository.StateRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class EmployeeJPAUnitTest {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    StateRepository stateRepository;

    @Test
    public void should_store_a_employee() {
        // Arrange
        State state = stateRepository.findById(1L).get();

        // Act
        Employee employee = employeeRepository.save(new Employee(6L, "Juan", state));

        // Assert
        assertThat(employee).hasFieldOrPropertyWithValue("name", "Juan");
    }

    @Test
    public void should_find_employee_by_id() {
        // Arrange
        State state = stateRepository.findById(1L).get();
        Employee employee = employeeRepository.save(new Employee(6L, "Juan", state));

        // Act
        Employee foundEmployee = employeeRepository.findById(employee.getId()).get();

        // Assert
        assertThat(foundEmployee).isEqualTo(employee);
    }

    @Test
    public void should_update_employee_by_id() {
        // Arrange
        State state = stateRepository.findById(1L).get();
        Employee employee = employeeRepository.save(new Employee(5L, "Marc", state));

        // Act
        Employee updatedEmployee = new Employee("Pau", state);
        Employee employeeToBeUpdated = employeeRepository.findById(employee.getId()).get();
        employeeToBeUpdated.setName(updatedEmployee.getName());
        employeeRepository.save(employeeToBeUpdated);

        // Assert
        Employee checkEmployee = employeeRepository.findById(employee.getId()).get();
        assertThat(checkEmployee.getId()).isEqualTo(employee.getId());
        assertThat(checkEmployee.getName()).isEqualTo(updatedEmployee.getName());
    }

    @Test
    public void should_delete_employee_by_id() {
        // Arrange
        State state = stateRepository.findById(1L).get();
        Employee employee = employeeRepository.save(new Employee("Carles", state));

        // Act
        employeeRepository.deleteById(employee.getId());

        // Assert
        assertThat(employeeRepository.findById(employee.getId())).isEmpty();
    }
}
