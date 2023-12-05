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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Data
@NoArgsConstructor	@AllArgsConstructor //generator constructors with and without variables. not included in @data annotation!
@Table(name = "REQUEST_TABLE")
public class Request {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "request_id")
	private UUID id;
	
	//version numbers for detecting concurrent changes to entries. Also may prevent issues with id generation
	@Version 
	private Integer version;
	
	@Column(name = "measuringboxID")
	private UUID measuringboxID;
	
	public enum MeasuringboxStatus {INACTIVE, CONNECTING, RECEIVING, ACTIVE, ERROR, RESETTING}; //enum for keeping track of box status
	
	@Column
	 @Enumerated(EnumType.STRING)
	 private MeasuringboxStatus measuringboxStatus;	
	
	public enum RequestType {NONE, ISALIVE, LIVEDATA, STATUS, RESET};

	@Column
	 @Enumerated(EnumType.STRING)
	 private RequestType requestType;	
	
	@Column(nullable = false)
	private boolean isSimulated;  
	
    public void generateNewId() {
        this.id = null; // Set the current ID to null and generate a new one
    }
}
