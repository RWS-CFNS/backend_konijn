package nl.cfns.entities;

import java.sql.Timestamp;

import com.github.javafaker.Faker;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor	@AllArgsConstructor //generator constructors with and without variables
@Table(name = "WEATHER_TABLE")
public class WeatherMeasurement {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private Timestamp time;
	
	@Column
	@Min(value = 0, message = "Value should be greater then, or equal to 0")
	@Max(value = 100, message = "Value should be less then, or equal to 100")
	private Float temp;
	
	@Column
	@Min(value = 0, message = "Value should be greater then, or equal to 0")
	@Max(value = 100, message = "Value should be less then, or equal to 100")
	private Float humid;
	
	@Column
	@Min(value = 0, message = "Value should be greater then, or equal to 0")
	@Max(value = 100, message = "Value should be less then, or equal to 100")
	private Integer windDirection;
	
	@Column
	@Min(value = 0, message = "Value should be greater then, or equal to 0")
	@Max(value = 100, message = "Value should be less then, or equal to 100")
	private Float windspeed;
	
	@Column
	@Min(value = 0, message = "Value should be greater then, or equal to 0")
	@Max(value = 100, message = "Value should be less then, or equal to 100")
	private Float dauw;
	
	@Column
	@Min(value = 0, message = "Value should be greater then, or equal to 0")
	@Max(value = 100, message = "Value should be less then, or equal to 100")
	private Float pressure;
	
	//generate random values for testing purposes
	public static WeatherMeasurement generateRandomWeatherMeasurement() {
	    Faker faker = new Faker();
	    WeatherMeasurement measurement = new WeatherMeasurement();
	    
	    measurement.setId((long) 1);
	    measurement.setTime(new Timestamp(System.currentTimeMillis())); 
	    measurement.setTemp((float) faker.number().randomDouble(1, 0, 100)); 
	    measurement.setHumid((float) faker.number().randomDouble(1, 0, 100)); 
	    measurement.setWindDirection(faker.number().numberBetween(0, 100)); 
	    measurement.setWindspeed((float) faker.number().randomDouble(1, 0, 100)); 
	    measurement.setDauw((float) faker.number().randomDouble(1, 0, 100)); 
	    measurement.setPressure((float) faker.number().randomDouble(1, 0, 100)); 
	    
	    return measurement;
	}
}
