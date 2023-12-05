package nl.cfns.entity;

import java.sql.Timestamp;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Entity
@Data
@Accessors(chain = true)
@NoArgsConstructor	@AllArgsConstructor //generator constructors with and without variables
@Table(name = "WEATHER_TABLE")
public class WeatherMeasurement  implements VersionedEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "weather_id")
	private UUID id;

	//version numbers for detecting concurrent changes to entries. Also may prevent issues with id generation
	@Version 
	@Column(name = "version_nr")
	private Integer version;
	
	@Column(nullable = true)
	private Timestamp time;
	
	@Column(nullable = true)
	@Min(value = -50, message = "Value should be greater then, or equal to -50")
	@Max(value = 100, message = "Value should be less then, or equal to 100")
	private Float temp;
	
	@Column(nullable = true)
	@Min(value = 0, message = "Value should be greater then, or equal to 0")
	@Max(value = 100, message = "Value should be less then, or equal to 100")
	private Float humid;
	
	@Column(nullable = true)
	@Min(value = 0, message = "Value should be greater then, or equal to 0")
	@Max(value = 360, message = "Value should be less then, or equal to 360")
	private Integer windDirection;
	
	@Column(nullable = true)
	@Min(value = 0, message = "Value should be greater then, or equal to 0")
	@Max(value = 500, message = "Value should be less then, or equal to 500")
	private Float windspeed;
	
	@Column(nullable = true)
	@Min(value = -50, message = "Value should be greater then, or equal to -50")
	@Max(value = 100, message = "Value should be less then, or equal to 100")
	private Float dauw;
	
	@Column(nullable = true)
	@Min(value = 900, message = "Value should be greater then, or equal to 900")
	@Max(value = 1100, message = "Value should be less then, or equal to 1100")
	private Float pressure;
	
	@Column(nullable = false)
	private boolean isSimulated;  
	
    public void generateNewId() {
        this.id = null; // Set the current ID to null and generate a new one
    }
	

    @Override
    public void setVersion(Integer version) {
        this.version = version;
    }

}
