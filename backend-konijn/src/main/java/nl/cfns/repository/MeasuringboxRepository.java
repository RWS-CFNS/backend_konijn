package nl.cfns.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import nl.cfns.entity.Measuringbox;

@Repository("MeasuringboxRepository")
public interface MeasuringboxRepository  extends CrudRepository<Measuringbox,UUID>{
	List<Measuringbox> findBoxByid(UUID id);
}
