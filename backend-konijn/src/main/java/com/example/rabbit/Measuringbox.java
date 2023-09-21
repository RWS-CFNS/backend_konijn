package com.example.rabbit;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;

@Entity
@Table(name = "MTABLE")
public class Measuringbox {
	
	
	public Measuringbox() { //empty constructor, needed for JSON parser
		super();
	}

	public Measuringbox(String name, Integer value1, Integer tempValue1) { //constructor with fields
		super();
		this.name = name;
		this.value1 = value1;
		this.tempValue1 = tempValue1;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "STUDENT_NAME", length = 50, nullable = false, unique = false)
	private String name;

	@Transient
	private Integer value1;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getValue1() {
		return value1;
	}

	public void setValue1(Integer value1) {
		this.value1 = value1;
	}

	public Integer getTempValue1() {
		return tempValue1;
	}

	public void setTempValue1(Integer tempValue1) {
		this.tempValue1 = tempValue1;
	}

	@Temporal(TemporalType.DATE)
	private Integer tempValue1;

	// @Enumerated(EnumType.STRING)
	// private boolean measuringboxGender;

	// other fields, getters and setters
}
