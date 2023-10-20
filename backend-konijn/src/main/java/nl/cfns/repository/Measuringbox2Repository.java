package nl.cfns.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import nl.cfns.entity.Measuringbox;

@Repository("Measuringbox2Repository")
public interface Measuringbox2Repository  extends CrudRepository<Measuringbox,Long>{
	List<Measuringbox> findBoxByid(UUID id);
}
