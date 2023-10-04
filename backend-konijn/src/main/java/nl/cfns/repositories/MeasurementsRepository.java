package nl.cfns.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import nl.cfns.entities.Measurement;

@Repository
public interface MeasurementsRepository  extends CrudRepository<Measurement,Long> {

}
