package nl.cfns.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//TODO possibly add geometry datatype with extra dependency

@Entity
@Data
@NoArgsConstructor	@AllArgsConstructor //generator constructors with and without variables
@Table(name = "MBOX_TABLE")
public class Measuringbox2 {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "MNC", length = 50, nullable = false, unique = false)
	private String mnc;

	@Column(name = "MCC", length = 50, nullable = false, unique = false)
	private String mcc;	

	@Column(name = "LAC", length = 50, nullable = false, unique = false)
	private String lac;	
	
	@Column 
	private Integer longitude;
	
	//@Column 
	//private Geometry location;
	
	@Column
	private Integer Latitude;
	
	public enum MeasuringboxStatus {INACTIVE, CONNECTING, RECEIVING, ACTIVE, ERROR}; //enum for keeping track of box status
	
	@Column
	 @Enumerated(EnumType.STRING)
	 private MeasuringboxStatus status;
	 
	// private boolean measuringboxGender;

	// other fields, getters and setters
}
