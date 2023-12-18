package com.trackingsystem.salesmanagement.model;

import static jakarta.persistence.GenerationType.IDENTITY;
import jakarta.persistence.*;


@Entity
@Table(name="regions")
public class Region {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;

	public Region() {
		super();
	}

	public Region(String name) {
		this.name = name;
	}

	public Region(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return String.format("Region [id=%s, name=%s]", id, name);
	}

}