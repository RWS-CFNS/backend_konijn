package nl.cfns.entity;


import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
//TODO possibly add geometry datatype with extra dependency

@Entity
@Data
@Builder(toBuilder = true, builderMethodName = "") //for only generating a copy constructor
@NoArgsConstructor	@AllArgsConstructor //generator constructors with and without variables
@Table(name = "MEASURINGBOX_TABLE")
public class Measuringbox {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "measuringbox_id")
	private UUID id;
	
	//foreign key relation to keep track of measurements corresponding to this measuringbox
    @OneToMany(mappedBy = "measuringbox")
    private Set<Measurement> measurements = new HashSet<>();

	@Column(name = "Mobile Network Code", length = 50, nullable = false, unique = false)
	private String mnc;

	@Column(name = "Mobile Country Code", length = 50, nullable = false, unique = false)
	private String mcc;	

	@Column(name = "Location Area Code", length = 50, nullable = false, unique = false)
	private String lac;	
	
	@Column(nullable = false)
	@Min(value = 0, message = "Value should be greater then, or equal to 0")
	@Max(value = 100, message = "Value should be less then, or equal to 100")
	private Double longitude;
	
	//@Column 
	//private Geometry location;
	@Column(nullable = false)
	@Min(value = 0, message = "Value should be greater then, or equal to 0")
	@Max(value = 100, message = "Value should be less then, or equal to 100")
	private Double Latitude;
	
	public enum MeasuringboxStatus {INACTIVE, CONNECTING, RECEIVING, ACTIVE, ERROR}; //enum for keeping track of box status
	
	@Column
	 @Enumerated(EnumType.STRING)
	 private MeasuringboxStatus status;
	 
	@Column(nullable = false)
	private boolean isSimulated;  

    public void generateNewId() {
        this.id = null; // Set the current ID to null and generate a new one
    }

}
