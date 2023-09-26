package com.example.rabbit;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
//TODO add enum status to this entity
//TODO generate getters, setters and constructors when finished setting up entities
//TODO possibly add geometry datatype with extra dependency

@Entity
@Getter	@Setter //generate getters and setters for all variables
@NoArgsConstructor	@AllArgsConstructor //generator constructors with and without variables
@Table(name = "MBOX_TABLE")
public class Measuringbox2 {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "MNC", length = 50, nullable = false, unique = false)
	private String mnc;

	@Column(name = "MCC", length = 50, nullable = false, unique = false)
	private String mcc;	

	@Column(name = "LAC", length = 50, nullable = false, unique = false)
	private String lac;	
	
	@Column 
	private Integer longitude;
	
	//@Column 
	//private Geometry location;
	
	@Column
	private Integer Latitude;
	
	// @Enumerated(EnumType.STRING)
	// private boolean measuringboxGender;

	// other fields, getters and setters
}
