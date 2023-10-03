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
@Table(name = "MBOX_TABLE")
public class Measuringbox2 {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "BOX_NAME", length = 50, nullable = false, unique = false)
	private String name;

	@Transient
	private Integer value1;



	// @Enumerated(EnumType.STRING)
	// private boolean measuringboxGender;

	// other fields, getters and setters
}
