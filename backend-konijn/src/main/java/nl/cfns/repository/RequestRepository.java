package nl.cfns.repository;

import org.springframework.data.repository.CrudRepository;

import nl.cfns.entity.Request;

public interface RequestRepository extends CrudRepository<Request,Long>  {

}
