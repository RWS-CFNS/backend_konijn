package nl.cfns.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import nl.cfns.entity.Measuringbox;

//repository for measuringbox for interacting with the database
@Repository("MeasuringboxRepository")
public interface MeasuringboxRepository extends CrudRepository<Measuringbox,Long> {
    List<Measuringbox> findBoxByid(Long id);
}