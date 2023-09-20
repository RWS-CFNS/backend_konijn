package com.example.rabbit;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "Table")
public class Measuringbox {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "STUDENT_NAME", length = 50, nullable = false, unique = false)
	private String name;

	@Transient
	private Integer value1;

	@Temporal(TemporalType.DATE)
	private Date tempValue1;

	@Enumerated(EnumType.STRING)
	private boolean measuringboxGender;

	// other fields, getters and setters
}
