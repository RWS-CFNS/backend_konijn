package nl.cfns.entities;

import com.github.javafaker.Faker;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "MBOX_TABLE")
public class Measuringbox2 {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
	private Long id;

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
	 

    public void generateNewId() {
        this.id = null; // Set the current ID to null and generate a new one
    }
	
	
	public static Measuringbox2 generateRandomMeasuringbox() {
	    Faker faker = new Faker();
	    Measuringbox2 measuringbox = new Measuringbox2();
	    
	    measuringbox.setId((long) 1);
	    measuringbox.setMnc(faker.number().digits(3)); 
	    measuringbox.setMcc(faker.number().digits(3)); 
	    measuringbox.setLac(faker.number().digits(5)); 
	    measuringbox.setLongitude(faker.number().randomDouble(2, 0, 100)); 
	    measuringbox.setLatitude(faker.number().randomDouble(2, 0, 100)); 
	    measuringbox.setStatus(Measuringbox2.MeasuringboxStatus.values()[faker.number().numberBetween(0, 4)]); 
	    
	    return measuringbox;
	}
	// private boolean measuringboxGender;

	// other fields, getters and setters
}
