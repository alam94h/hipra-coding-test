package com.trackingsystem.salesmanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.trackingsystem.salesmanagement.model.Region;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;


@Entity
@Table(name = "states")
public class State {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "region_id", nullable = false)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@OnDelete(action = OnDeleteAction.CASCADE)
    private Region region;

	public State() {
		super();
	}

	public State(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public State(String name, Region region) {
		this.name = name;
		this.region = region;
	}

	public State(Long id, String name, Region region) {
		this.id = id;
		this.name = name;
		this.region = region;
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

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	@Override
	public String toString() {
		return "State{" +
				"id=" + id +
				", name='" + name + '\'' +
				", region=" + region +
				'}';
	}
}