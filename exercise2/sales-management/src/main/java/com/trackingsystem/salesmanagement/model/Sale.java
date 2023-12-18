package com.trackingsystem.salesmanagement.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;


@Entity
@Table(name = "sales")
public class Sale {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "amount")
	private Integer amount;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "employee_id", nullable = false)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@OnDelete(action = OnDeleteAction.CASCADE)
    private Employee employees;

	public Sale() {
		super();
	}

	public Sale(Long id, Integer amount) {
		super();
		this.id = id;
		this.amount = amount;
	}

	public Sale(Long id, Integer amount, Employee employees) {
		this.id = id;
		this.amount = amount;
		this.employees = employees;
	}

	public Sale(Integer amount, Employee employees) {
		this.amount = amount;
		this.employees = employees;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Employee getEmployees() {
		return employees;
	}

	public void setEmployees(Employee employees) {
		this.employees = employees;
	}

	@Override
	public String toString() {
		return "Sale{" +
				"id=" + id +
				", amount=" + amount +
				", employees=" + employees +
				'}';
	}
}