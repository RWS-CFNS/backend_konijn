package com.example.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;




@Entity
@Getter	@Setter //generate getters and setters for all variables
@NoArgsConstructor	@AllArgsConstructor //generator constructors with and without variables
@Table(name = "MTABLE")
public class Measuringbox {	
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "BOX_NAME", length = 50, nullable = false, unique = false)
	private String name;

	@Transient
	private Integer value1;

	@Temporal(TemporalType.DATE)
	private Integer tempValue1;

//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public Integer getValue1() {
//		return value1;
//	}
//
//	public void setValue1(Integer value1) {
//		this.value1 = value1;
//	}
//
//	public Integer getTempValue1() {
//		return tempValue1;
//	}
//
//	public void setTempValue1(Integer tempValue1) {
//		this.tempValue1 = tempValue1;
//	}


	// @Enumerated(EnumType.STRING)
	// private boolean measuringboxGender;

	// other fields, getters and setters
}
