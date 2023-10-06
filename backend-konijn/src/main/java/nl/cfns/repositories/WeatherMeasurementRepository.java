package nl.cfns.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import nl.cfns.entities.WeatherMeasurement;

@Repository("WeatherMeasurementRepository")
public interface WeatherMeasurementRepository extends CrudRepository<WeatherMeasurement,Long> {
	List<WeatherMeasurement> findBoxByid(Long id);
}
