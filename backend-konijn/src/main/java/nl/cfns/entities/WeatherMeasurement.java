package nl.cfns.entities;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.cfns.entities.Measuringbox2.MeasuringboxStatus;

@Entity
@Data
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
