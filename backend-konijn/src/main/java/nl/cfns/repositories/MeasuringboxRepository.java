package nl.cfns.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import nl.cfns.entities.Measuringbox;

//repository for measuringbox for interacting with the database
@Repository("MeasuringboxRepository")
public interface MeasuringboxRepository extends CrudRepository<Measuringbox,Long> {
    List<Measuringbox> findBoxByid(Long id);
}