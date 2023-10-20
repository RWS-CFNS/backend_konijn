package nl.cfns.basicpojo;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class MeasurementPOJO {
	private Long measuringboxid;	
	private Timestamp time;
	private Integer latency;
	private Float upload;
	private Float download;
	private Integer RSSI;
	private Integer RSRQ;
	private Integer RSRP;
	private Integer SINR;
	private String mnoString;
	private Double latitude;
	private Double longitude;
	private boolean isSimulated;  
	

}
