package nl.cfns.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import nl.cfns.entities.WeatherMeasurement;

@Repository
public interface WeatherMeasurementRepository extends CrudRepository<WeatherMeasurement,Long> {

}
