package nl.cfns.entity;



import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Entity
@Data
@Accessors(chain = true)
@Builder(toBuilder = true, builderMethodName = "") //for only generating a copy constructor
@NoArgsConstructor	@AllArgsConstructor //generator constructors with and without variables
@Table(name = "MEASURINGBOX_TABLE")
public class Measuringbox  implements VersionedEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "measuringbox_id")
	private UUID id;
	
	//version numbers for detecting concurrent changes to entries. Also may prevent issues with id generation
	@Version 
	@Column(name = "version_nr")
	private Integer version;
	
	//foreign key relation to keep track of measurements corresponding to this measuringbox
//    @OneToMany(mappedBy = "measuringbox")
//    private Set<Measurement> measurements = new HashSet<>();

	@Column(nullable = false)
	@Min(value = 0, message = "Value should be greater then, or equal to 0")
	@Max(value = 999, message = "Value should be less then, or equal to 999")
	private Integer mnc;
	
	@Column(nullable = false)
	@Min(value = 0, message = "Value should be greater then, or equal to 0")
	@Max(value = 999, message = "Value should be less then, or equal to 999")
	private Integer mcc;
	
	@Column(nullable = false)
	@Min(value = 0, message = "Value should be greater then, or equal to 0")
	@Max(value = 65535, message = "Value should be less then, or equal to 65535")
	private Integer lac;	
	
	@Column(nullable = false)
	@Min(value = -90, message = "Value should be greater then, or equal to -90")
	@Max(value = 90, message = "Value should be less then, or equal to 90")
	private Double longitude;
	
	//@Column 
	//private Geometry location;
	@Column(nullable = false)
	@Min(value = -180, message = "Value should be greater then, or equal to -180")
	@Max(value = 180, message = "Value should be less then, or equal to 180")
	private Double Latitude;
	
	public enum MeasuringboxStatus {INACTIVE, CONNECTING, RECEIVING, ACTIVE, ERROR}; //enum for keeping track of box status
	
	@Column(nullable = false)
	 @Enumerated(EnumType.STRING)
	 private MeasuringboxStatus status;
	 
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
