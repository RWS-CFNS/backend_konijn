package dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class MeasuringboxDto {
	private Integer mnc;
	private Integer mcc;
	private Integer lac;
	private Double longitude;
	private Double Latitude;

	public enum MeasuringboxStatus {
		INACTIVE, CONNECTING, RECEIVING, ACTIVE, ERROR
	}; // enum for keeping track of box status

	@Enumerated(EnumType.STRING)
	private MeasuringboxStatus status;
	private boolean isSimulated;

}
