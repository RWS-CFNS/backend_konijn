package nl.cfns.simulate;

import java.sql.Timestamp;

import org.springframework.stereotype.Service;

import com.github.javafaker.Faker;

import dto.MeasurementDto;
import dto.MeasuringboxDto;
import dto.WeatherMeasurementDto;
import nl.cfns.entity.Celltower;

//this class contains functions to generate valid data values. 
//the faker libary is used to generate values and string that are defined within a range
//the faker library will generate strings that are words instead of random letters
@Service
public class DataSimulator {	

	
	//generate random values for weather measurements
	public static WeatherMeasurementDto generateRandomWeatherMeasurement() {
	    Faker faker = new Faker();		
		WeatherMeasurementDto weatherMeasurementDto = new WeatherMeasurementDto();
	    
		// weatherMeasurementDto.generateNewId();
		weatherMeasurementDto.setTime(new Timestamp(System.currentTimeMillis()));
		weatherMeasurementDto.setTemp((float) faker.number().randomDouble(1, 0, 100));
		weatherMeasurementDto.setHumid((float) faker.number().randomDouble(1, 0, 100));
		weatherMeasurementDto.setWindDirection(faker.number().numberBetween(0, 100));
		weatherMeasurementDto.setWindspeed((float) faker.number().randomDouble(1, 0, 100));
		weatherMeasurementDto.setDauw((float) faker.number().randomDouble(1, 0, 100));
		weatherMeasurementDto.setPressure((float) faker.number().randomDouble(1, 900, 1100));
		weatherMeasurementDto.setSimulated(true);
	    
		return weatherMeasurementDto;
	}
	
	//function for generating random measuringbox values
	public static MeasuringboxDto generateRandomMeasuringbox() {
	    Faker faker = new Faker();
		MeasuringboxDto measuringboxDto = new MeasuringboxDto();
	    
		// measuringboxDto.generateNewId();
		measuringboxDto.setMnc(faker.number().numberBetween(0, 10));
		measuringboxDto.setMcc(faker.number().numberBetween(0, 10));
		measuringboxDto.setLac(faker.number().numberBetween(0, 10));
		measuringboxDto.setLongitude(faker.number().randomDouble(2, 0, 100));
		measuringboxDto.setLatitude(faker.number().randomDouble(2, 0, 90));
		measuringboxDto.setStatus(MeasuringboxDto.MeasuringboxStatus.values()[faker.number().numberBetween(0, 4)]);
		measuringboxDto.setSimulated(true);
	    
		return measuringboxDto;
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
	public static MeasurementDto generateRandomMeasurement() {
	    Faker faker = new Faker();
		MeasurementDto measurementDto = new MeasurementDto();
	    
		// measurement.generateNewId();
	    //measurement.setMeasuringbox(SimulatorConfig.simulatorMeasuringbox);
		measurementDto.setTime(new Timestamp(System.currentTimeMillis()));
		measurementDto.setLatency(faker.number().numberBetween(0, 100));
		measurementDto.setUpload((float) faker.number().randomDouble(2, 0, 100));
		measurementDto.setDownload((float) faker.number().randomDouble(2, 0, 100));
		measurementDto.setRSSI(faker.number().numberBetween(-100, 0));
		measurementDto.setRSRQ(faker.number().numberBetween(0, 20));
		measurementDto.setRSRP(faker.number().numberBetween(-100, 0));
		measurementDto.setSINR(faker.number().numberBetween(5, 100));
		measurementDto.setMnoString(faker.lorem().word());
		measurementDto.setLongitude(faker.number().randomDouble(2, 0, 100));
		measurementDto.setLatitude(faker.number().randomDouble(2, 0, 100));
		measurementDto.setSimulated(true);
	  
	    
		return measurementDto;
	}
	
	//generate random measurements, but they are based on locations of cell towers. 
	//the values are determined by the distance to the cell towers
	public static MeasurementDto generateRandomMeasurementAdjusted(Iterable<Celltower> celltowerIterable) {
	    Faker faker = new Faker();
		MeasurementDto measurementDto = new MeasurementDto();
	    
	    // Generate random latitude and longitude for the measurement point
	    double latitude = faker.number().randomDouble(6, 0, 5); //range of latitudes
	    double longitude = faker.number().randomDouble(6, 0, 5); //range of longitudes

		// measurementDto.generateNewId();
		measurementDto.setTime(new Timestamp(System.currentTimeMillis()));
		measurementDto.setLatitude(latitude);
		measurementDto.setLongitude(longitude);

	    //Calculate distance to each tower
	    double distanceToTower = calculateMinimalDistancetoTower(celltowerIterable, latitude, longitude);

	    //Adjust RSSI values based on distance
	    int rssiTower1 = (int) (1 - distanceToTower); // Example: Inverse relation with distance

	    //Set RSSI values based on distance
		measurementDto.setRSSI(rssiTower1); // Combining signals from both towers
		measurementDto.setRSRQ(0); // Set a constant value for RSRQ
		measurementDto.setRSRP(0); // Set a constant value for RSRP
		measurementDto.setSINR(0);
	    
	    //check in terminal if values make sense
		// System.out.println( "the following measurement:" +
		// measurementDto.toString());
		// System.out.println( "the following distance to a tower was used: " +
		// distanceToTower);

		measurementDto.setLatency(faker.number().numberBetween(0, 100));
		measurementDto.setUpload((float) faker.number().randomDouble(2, 0, 100));
		measurementDto.setDownload((float) faker.number().randomDouble(2, 0, 100));
		measurementDto.setMnoString(faker.lorem().word());
	    
		return measurementDto;
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
