package nl.cfns.base;

import java.sql.Timestamp;

import org.springframework.stereotype.Service;

import com.github.javafaker.Faker;

import nl.cfns.entity.Measurement;
import nl.cfns.entity.Measuringbox2;
import nl.cfns.entity.WeatherMeasurement;

//this class contains functions to generate valid data values. 
@Service
public class DataSimulator {
	
	//generate random values for weather measurements
	public static WeatherMeasurement generateRandomWeatherMeasurement() {
	    Faker faker = new Faker();		
	    WeatherMeasurement measurement = new WeatherMeasurement();
	    
	    measurement.setId((long) 1);
	    measurement.setTime(new Timestamp(System.currentTimeMillis())); 
	    measurement.setTemp((float) faker.number().randomDouble(1, 0, 100)); 
	    measurement.setHumid((float) faker.number().randomDouble(1, 0, 100)); 
	    measurement.setWindDirection(faker.number().numberBetween(0, 100)); 
	    measurement.setWindspeed((float) faker.number().randomDouble(1, 0, 100)); 
	    measurement.setDauw((float) faker.number().randomDouble(1, 0, 100)); 
	    measurement.setPressure((float) faker.number().randomDouble(1, 0, 100)); 
	    
	    return measurement;
	}
	
	//function for generating random measuringbox values
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
	
	//generate random values for measurement
	public static Measurement generateRandomMeasurement() {
	    Faker faker = new Faker();
	    Measurement measurement = new Measurement();
	    
	    measurement.setId((long) 1);
	    measurement.setTime(new Timestamp(System.currentTimeMillis())); 
	    measurement.setLatency(faker.number().numberBetween(0, 100)); 
	    measurement.setUpload((float) faker.number().randomDouble(2, 0, 100)); 
	    measurement.setDownload((float) faker.number().randomDouble(2, 0, 100)); 
	    measurement.setRSSI(faker.number().numberBetween(0, 100)); 
	    measurement.setRSRQ(faker.number().numberBetween(0, 100)); 
	    measurement.setRSRP(faker.number().numberBetween(0, 100)); 
	    measurement.setSINR(faker.number().numberBetween(0, 100)); 
	    measurement.setMnoString(faker.lorem().word()); 
	    measurement.setLongitude(faker.number().randomDouble(2, 0, 100)); 
	    measurement.setLatitude(faker.number().randomDouble(2, 0, 100)); 
	    
	    
	    return measurement;
	}
	
	//generate random measurements, but they are based on locations of cell towers. 
	//the values are determined by the distance to the cell towers
	public static Measurement generateRandomMeasurement() {
	    Faker faker = new Faker();
	    Measurement measurement = new Measurement();
	    
	    // Generate random latitude and longitude for the measurement point
	    double latitude = faker.number().randomDouble(6, 0, 90); // Example: range of latitudes
	    double longitude = faker.number().randomDouble(6, 0, 180); // Example: range of longitudes

	    measurement.setId((long) 1);
	    measurement.setTime(new Timestamp(System.currentTimeMillis())); 
	    measurement.setLatitude(latitude);
	    measurement.setLongitude(longitude);

	    // Simulate tower locations (latitude and longitude)
	    double tower1Latitude = 45.0;
	    double tower1Longitude = 60.0;
	    double tower2Latitude = 30.0;
	    double tower2Longitude = 120.0;

	    // Calculate distance to each tower
	    double distanceToTower1 = calculateDistance(latitude, longitude, tower1Latitude, tower1Longitude);
	    double distanceToTower2 = calculateDistance(latitude, longitude, tower2Latitude, tower2Longitude);

	    // Adjust RSSI values based on distance
	    int rssiTower1 = (int) (100 - distanceToTower1); // Example: Inverse relation with distance
	    int rssiTower2 = (int) (100 - distanceToTower2);

	    // Set RSSI values based on distance
	    measurement.setRSSI(rssiTower1 + rssiTower2); // Combining signals from both towers
	    measurement.setRSRQ(0); // Set a constant value for RSRQ
	    measurement.setRSRP(0); // Set a constant value for RSRP
	    measurement.setSINR(measurement.getRSSI());

	    // Other values (latency, upload, download, MNO) can be set randomly or as needed

	    return measurement;
	}
	
	
}
