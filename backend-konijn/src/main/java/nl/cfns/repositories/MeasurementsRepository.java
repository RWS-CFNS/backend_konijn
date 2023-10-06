package nl.cfns.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import nl.cfns.entities.Measurement;

@Repository("MeasurementsRepository")
public interface MeasurementsRepository  extends CrudRepository<Measurement,Long> {
	List<Measurement> findBoxByid(Long id);
}
