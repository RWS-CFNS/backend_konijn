package nl.cfns.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class WeatherMeasurementDto {
	private Timestamp time;
	private Float temp;
	private Float humid;
	private Integer windDirection;
	private Float windspeed;
	private Float dauw;
	private Float pressure;

	private boolean isSimulated;
}
