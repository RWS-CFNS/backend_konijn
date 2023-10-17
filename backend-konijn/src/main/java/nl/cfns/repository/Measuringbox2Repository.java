package nl.cfns.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import nl.cfns.entity.Measuringbox2;

@Repository("Measuringbox2Repository")
public interface Measuringbox2Repository  extends CrudRepository<Measuringbox2,Long>{
	List<Measuringbox2> findBoxByid(UUID id);
}
