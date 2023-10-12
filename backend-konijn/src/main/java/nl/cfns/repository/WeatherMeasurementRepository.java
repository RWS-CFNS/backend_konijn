package nl.cfns.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import nl.cfns.entity.WeatherMeasurement;

@Repository("WeatherMeasurementRepository")
public interface WeatherMeasurementRepository extends CrudRepository<WeatherMeasurement,Long> {
	List<WeatherMeasurement> findBoxByid(Long id);
	
	//Filter weather measurements based on minimum and maximum values
    List<WeatherMeasurement> findByTempBetween(Float minTemp, Float maxTemp);
    List<WeatherMeasurement> findByHumidBetween(Float minHumid, Float maxHumid);
    List<WeatherMeasurement> findByWindDirection(Integer windDirection);
    List<WeatherMeasurement> findByWindspeedBetween(Float minWindspeed, Float maxWindspeed);
    List<WeatherMeasurement> findByDauwBetween(Float minDauw, Float maxDauw);
    List<WeatherMeasurement> findByPressureBetween(Float minPressure, Float maxPressure);
}
