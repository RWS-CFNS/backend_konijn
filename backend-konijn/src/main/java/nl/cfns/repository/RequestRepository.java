package nl.cfns.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import nl.cfns.entity.Request;

public interface RequestRepository extends CrudRepository<Request,UUID>  {

}
