package nl.cfns.basicpojo;

import org.springframework.data.annotation.Id;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;

@Data
//this POJO is used to generate points on the heatmap
public class MeasurementPoint {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private double x;
	private double y;
	private double value;

	public MeasurementPoint(Integer latitude, Integer longitude, Integer RSSI) {
		this.id = null;
		this.x = latitude;
		this.y = longitude;
		this.value = RSSI;
	}
}
