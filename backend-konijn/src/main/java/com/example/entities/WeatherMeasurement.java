package com.example.entities;

import java.sql.Timestamp;

import com.example.entities.Measuringbox2.MeasuringboxStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter	@Setter //generate getters and setters for all variables
@NoArgsConstructor	@AllArgsConstructor //generator constructors with and without variables
@Table(name = "WEATHER_TABLE")
public class WeatherMeasurement {
	@Column
	@Id
	private Timestamp time;
	
	@Column
	private Float temp;
	
	@Column
	private Float humid;
	
	@Column
	private Integer windDirection;
	
	@Column
	private Float windspeed;
	
	@Column
	private Float dauw;
	
	@Column
	private Float pressure;
}