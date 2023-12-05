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


//TODO add foreign keys to entities
@Entity
@Data
@Accessors(chain = true)
@NoArgsConstructor	@AllArgsConstructor //generator constructors with and without variables
@Table(name = "MEASUREMENTS_TABLE")
public class Measurement implements VersionedEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "measurement_id")
	private UUID id;
	
	//version numbers for detecting concurrent changes to entries. Also may prevent issues with id generation
	@Column(name = "version_nr")
	@Version 
	private Integer version;
	
	//foreign key for keeping track which measuringbox this measurement belongs to
//    @ManyToOne
//    @JoinColumn(name = "measuringbox_id", nullable = true)
//    private Measuringbox measuringbox;
	
    @Column(nullable = true)
	private Timestamp time;
	
	@Column(nullable = true)
	@Min(value = 0, message = "Value should be greater then, or equal to 0")
	@Max(value = 10000, message = "Value should be less then, or equal to 10000")
	private Integer latency;
	
	@Column(nullable = true)
	@Min(value = 0, message = "Value should be greater then, or equal to 0")
	@Max(value = 10000, message = "Value should be less then, or equal to 10000")
	private Float upload;
	
	@Column(nullable = true)
	@Min(value = 0, message = "Value should be greater then, or equal to 0")
	@Max(value = 10000, message = "Value should be less then, or equal to 10000")
	private Float download;
	
	@Column(nullable = true)
	@Min(value = -120, message = "Value should be greater then, or equal to -120")
	@Max(value = 0, message = "Value should be less then, or equal to 0")
	private Integer RSSI;
	
	@Column(nullable = true)
	@Min(value = -20, message = "Value should be greater then, or equal to -20")
	@Max(value = 20, message = "Value should be less then, or equal to 20")
	private Integer RSRQ;
	
	@Column(nullable = true)
	@Min(value = -140, message = "Value should be greater then, or equal to -140")
	@Max(value = 0, message = "Value should be less then, or equal to 0")
	private Integer RSRP;
	
	@Column(nullable = true)
	@Min(value = 5, message = "Value should be greater then, or equal to 5")
	@Max(value = 100, message = "Value should be less then, or equal to 100")
	private Integer SINR;
//	
	@Column(name = "Mobile Network Operator", length = 50, nullable = true, unique = false)
	private String mnoString;

	@Column(nullable = true)
	@Min(value = -90, message = "Value should be greater then, or equal to -90")
	@Max(value = 90, message = "Value should be less then, or equal to 90")
	private Double latitude;

	@Column(nullable = true)
	@Min(value = 0, message = "Value should be greater then, or equal to -180")
	@Max(value = 100, message = "Value should be less then, or equal to 180")
	private Double longitude;

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
