package nl.cfns.entities;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@NoArgsConstructor	@AllArgsConstructor //generator constructors with and without variables
@Table(name = "MEASUREMENTS_TABLE")
public class Measurements {
	@Column
	@Id
	private Timestamp time;
	
	@Column
	private Integer latency;
	
	@Column
	private Float upload;
	
	@Column
	private Float download;
	
	@Column
	private Integer RSSI;
	
	@Column
	private Integer RSRQ;
	
	@Column
	private Integer RSRP;
	
	@Column
	private Integer SINR;
	
}
