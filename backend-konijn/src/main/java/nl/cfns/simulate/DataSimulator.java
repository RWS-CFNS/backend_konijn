package nl.cfns.simulate;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.javafaker.Faker;

import nl.cfns.entity.Celltower;
import nl.cfns.entity.Measurement;
import nl.cfns.entity.Measuringbox;
import nl.cfns.entity.WeatherMeasurement;
import nl.cfns.repository.CelltowerRepository;
import nl.cfns.repository.MeasuringboxRepository;
import nl.cfns.repository.TestboxRepository;
import nl.cfns.simulate.SimulatorConfig;

//this class contains functions to generate valid data values. 
//the faker libary is used to generate values and string that are defined within a range
//the faker library will generate strings that are words instead of random letters
@Service
public class DataSimulator {	

	
	//generate random values for weather measurements
	public static WeatherMeasurement generateRandomWeatherMeasurement() {
	    Faker faker = new Faker();		
	    WeatherMeasurement measurement = new WeatherMeasurement();
	    
	    measurement.generateNewId();
	    measurement.setTime(new Timestamp(System.currentTimeMillis())); 
	    measurement.setTemp((float) faker.number().randomDouble(1, 0, 100)); 
	    measurement.setHumid((float) faker.number().randomDouble(1, 0, 100)); 
	    measurement.setWindDirection(faker.number().numberBetween(0, 100)); 
	    measurement.setWindspeed((float) faker.number().randomDouble(1, 0, 100)); 
	    measurement.setDauw((float) faker.number().randomDouble(1, 0, 100)); 
	    measurement.setPressure((float) faker.number().randomDouble(1, 900, 1100)); 
	    measurement.setSimulated(true);
	    
	    return measurement;
	}
	
	//function for generating random measuringbox values
	public static Measuringbox generateRandomMeasuringbox() {
	    Faker faker = new Faker();
	    Measuringbox measuringbox = new Measuringbox();
	    
	    measuringbox.generateNewId();
	    measuringbox.setMnc(faker.number().numberBetween(0, 10));
	    measuringbox.setMcc(faker.number().numberBetween(0, 10)); 
	    measuringbox.setLac(faker.number().numberBetween(0, 10)); 
	    measuringbox.setLongitude(faker.number().randomDouble(2, 0, 100)); 
	    measuringbox.setLatitude(faker.number().randomDouble(2, 0, 90)); 
	    measuringbox.setStatus(Measuringbox.MeasuringboxStatus.values()[faker.number().numberBetween(0, 4)]); 
	    measuringbox.setSimulated(true);
	    
	    return measuringbox;
	}
	
	//function for generating random measuringbox values
	public static Celltower generateRandomCelltower() {
	    Faker faker = new Faker();
	    Celltower tower = new Celltower();
	    
	    tower.generateNewId();
	    tower.setMnc(faker.number().digits(3)); 
	    tower.setMcc(faker.number().digits(3)); 
	    tower.setLac(faker.number().digits(5)); 
	    tower.setLongitude(faker.number().randomDouble(2, 0, 5)); 
	    tower.setLatitude(faker.number().randomDouble(2, 0, 5)); 
	    tower.setSimulated(true);
	    
	    return tower;
	}
	
	//generate random values for measurement
	public static Measurement generateRandomMeasurement() {
	    Faker faker = new Faker();
	    Measurement measurement = new Measurement();
	    
	    measurement.generateNewId();
	    //measurement.setMeasuringbox(SimulatorConfig.simulatorMeasuringbox);
	    measurement.setTime(new Timestamp(System.currentTimeMillis())); 
	    measurement.setLatency(faker.number().numberBetween(0, 100)); 
	    measurement.setUpload((float) faker.number().randomDouble(2, 0, 100)); 
	    measurement.setDownload((float) faker.number().randomDouble(2, 0, 100)); 
	    measurement.setRSSI(faker.number().numberBetween(-100, 0)); 
	    measurement.setRSRQ(faker.number().numberBetween(0, 20)); 
	    measurement.setRSRP(faker.number().numberBetween(-100, 0)); 
	    measurement.setSINR(faker.number().numberBetween(5, 100)); 
	    measurement.setMnoString(faker.lorem().word()); 
	    measurement.setLongitude(faker.number().randomDouble(2, 0, 100)); 
	    measurement.setLatitude(faker.number().randomDouble(2, 0, 100)); 
	    measurement.setSimulated(true);
	  
	    
	    return measurement;
	}
	
	//generate random measurements, but they are based on locations of cell towers. 
	//the values are determined by the distance to the cell towers
	public static Measurement generateRandomMeasurementAdjusted(Iterable<Celltower> celltowerIterable) {
	    Faker faker = new Faker();
	    Measurement measurement = new Measurement();
	    
	    // Generate random latitude and longitude for the measurement point
	    double latitude = faker.number().randomDouble(6, 0, 5); //range of latitudes
	    double longitude = faker.number().randomDouble(6, 0, 5); //range of longitudes

	    measurement.generateNewId();
	    measurement.setTime(new Timestamp(System.currentTimeMillis())); 
	    measurement.setLatitude(latitude);
	    measurement.setLongitude(longitude);

	    //Calculate distance to each tower
	    double distanceToTower = calculateMinimalDistancetoTower(celltowerIterable, latitude, longitude);

	    //Adjust RSSI values based on distance
	    int rssiTower1 = (int) (1 - distanceToTower); // Example: Inverse relation with distance

	    //Set RSSI values based on distance
	    measurement.setRSSI(rssiTower1); // Combining signals from both towers
	    measurement.setRSRQ(0); // Set a constant value for RSRQ
	    measurement.setRSRP(0); // Set a constant value for RSRP
	    measurement.setSINR(0);
	    
	    //check in terminal if values make sense
	    System.out.println( "the following measurement:" + measurement.toString());
	    System.out.println( "the following distance to a tower was used: " + distanceToTower);

	    measurement.setLatency(faker.number().numberBetween(0, 100)); 
	    measurement.setUpload((float) faker.number().randomDouble(2, 0, 100)); 
	    measurement.setDownload((float) faker.number().randomDouble(2, 0, 100)); 
	    measurement.setMnoString(faker.lorem().word()); 
	    
	    return measurement;
	}
	
	//function to calculate shortest distance between two points
    public static double calculateDistanceHaversine(double lat1, double lon1,
            double lat2, double lon2)
		{
		// distance between latitudes and longitudes
		double dLat = Math.toRadians(lat2 - lat1);
		double dLon = Math.toRadians(lon2 - lon1);
		
		// convert to radians
		lat1 = Math.toRadians(lat1);
		lat2 = Math.toRadians(lat2);
		
		// apply formulae
		double a = Math.pow(Math.sin(dLat / 2), 2) + 
		   Math.pow(Math.sin(dLon / 2), 2) * 
		   Math.cos(lat1) * 
		   Math.cos(lat2);
		double rad = 6371;
		double c = 2 * Math.asin(Math.sqrt(a));
		return rad * c;
		}
    
	//calculates distance to closest tower. if no tower is found, 999999 is returned
	public static Double calculateMinimalDistancetoTower(Iterable<Celltower> celltowerIterable, double latitude, double longitude) {
	    double minimalDistanceToTower = 999999;
		
	    //iterate all celltowers
	    for (Celltower thisCelltower : celltowerIterable) {
	        Double distanceToTower = DataSimulator.calculateDistanceHaversine(
	        		thisCelltower.getLatitude(), thisCelltower.getLongitude(), latitude, longitude);
	        
	        //save temp distance if this distance is smaller the value currently saved
	        if (distanceToTower < minimalDistanceToTower) {
	            minimalDistanceToTower = distanceToTower;
	        }
	    }

	    return minimalDistanceToTower;
	}
    
}
