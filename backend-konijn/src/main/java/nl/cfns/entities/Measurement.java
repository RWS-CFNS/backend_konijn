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
@Table(name = "MEASUREMENTS_TABLE")
public class Measurement {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
	private Long id;
	
	@Column
	private Timestamp time;
	
	@Column
	@Min(value = 0, message = "Value should be greater then, or equal to 0")
	@Max(value = 100, message = "Value should be less then, or equal to 100")
	private Integer latency;
	
	@Column
	@Min(value = 0, message = "Value should be greater then, or equal to 0")
	@Max(value = 100, message = "Value should be less then, or equal to 100")
	private Float upload;
	
	@Column
	@Min(value = 0, message = "Value should be greater then, or equal to 0")
	@Max(value = 100, message = "Value should be less then, or equal to 100")
	private Float download;
	
	@Column
	@Min(value = 0, message = "Value should be greater then, or equal to 0")
	@Max(value = 100, message = "Value should be less then, or equal to 100")
	private Integer RSSI;
	
	@Column
	@Min(value = 0, message = "Value should be greater then, or equal to 0")
	@Max(value = 100, message = "Value should be less then, or equal to 100")
	private Integer RSRQ;
	
	@Column
	@Min(value = 0, message = "Value should be greater then, or equal to 0")
	@Max(value = 100, message = "Value should be less then, or equal to 100")
	private Integer RSRP;
	
	@Column
	@Min(value = 0, message = "Value should be greater then, or equal to 0")
	@Max(value = 100, message = "Value should be less then, or equal to 100")
	private Integer SINR;
	
	@Column(name = "Mobile Network Operator", length = 50, nullable = false, unique = false)
	private String mnoString;

	@Column(nullable = false)
	@Min(value = 0, message = "Value should be greater then, or equal to 0")
	@Max(value = 100, message = "Value should be less then, or equal to 100")
	private Double latitude;

	@Column(nullable = false)
	@Min(value = 0, message = "Value should be greater then, or equal to 0")
	@Max(value = 100, message = "Value should be less then, or equal to 100")
	private Double longitude;
	
    public void generateNewId() {
        this.id = null; // Set the current ID to null and generate a new one
    }
	
	//generate random values for testing purposes
	public static Measurement generateRandomMeasurement() {
	    Faker faker = new Faker();
	    Measurement measurement = new Measurement();
	    
	    measurement.setId((long) 1);
	    measurement.setTime(new Timestamp(System.currentTimeMillis())); 
	    measurement.setLatency(faker.number().numberBetween(0, 100)); 
	    measurement.setUpload((float) faker.number().randomDouble(2, 0, 100)); 
	    measurement.setDownload((float) faker.number().randomDouble(2, 0, 100)); 
	    measurement.setRSSI(faker.number().numberBetween(0, 100)); 
	    measurement.setRSRQ(faker.number().numberBetween(0, 100)); 
	    measurement.setRSRP(faker.number().numberBetween(0, 100)); 
	    measurement.setSINR(faker.number().numberBetween(0, 100)); 
	    measurement.setMnoString(faker.lorem().word()); 
	    measurement.setLongitude(faker.number().randomDouble(2, 0, 100)); 
	    measurement.setLatitude(faker.number().randomDouble(2, 0, 100)); 
	    
	    
	    return measurement;
	}
	


}
