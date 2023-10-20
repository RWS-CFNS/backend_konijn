package nl.cfns.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import nl.cfns.entity.Testbox;

//repository for measuringbox for interacting with the database
@Repository("MeasuringboxRepository")
public interface MeasuringboxRepository extends CrudRepository<Testbox,Long> {
    List<Testbox> findBoxByid(Long id);
}