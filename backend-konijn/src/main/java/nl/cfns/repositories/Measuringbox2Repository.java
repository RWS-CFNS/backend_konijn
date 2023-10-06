package nl.cfns.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import nl.cfns.entities.Measuringbox2;

@Repository("Measuringbox2Repository")
public interface Measuringbox2Repository  extends CrudRepository<Measuringbox2,Long>{
	List<Measuringbox2> findBoxByid(Long id);
}
