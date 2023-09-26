package com.example.rabbit;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "MEASUREMENTS_TABLE")
public class Measurements {
	//@Column
	//private timestamp time;
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
}
