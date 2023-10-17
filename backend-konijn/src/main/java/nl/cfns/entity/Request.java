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
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.cfns.entity.Measuringbox2.MeasuringboxStatus;


@Entity
@Data
@NoArgsConstructor	@AllArgsConstructor //generator constructors with and without variables. not included in @data annotation!
@Table(name = "REQUEST")
public class Request {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID")
	private UUID id;
	
	
	
	@Column(name = "measuringboxID")
	private UUID measuringboxID;
	
	public enum MeasuringboxStatus {INACTIVE, CONNECTING, RECEIVING, ACTIVE, ERROR}; //enum for keeping track of box status
	
	@Column
	 @Enumerated(EnumType.STRING)
	 private MeasuringboxStatus measuringboxStatus;	
	
	public enum RequestType {NONE, ISALIVE, LIVEDATA, STATUS};

	@Column
	 @Enumerated(EnumType.STRING)
	 private RequestType requestType;	
	
    public void generateNewId() {
        this.id = null; // Set the current ID to null and generate a new one
    }
}
